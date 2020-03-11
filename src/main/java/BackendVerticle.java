import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import io.vertx.core.Future;
import io.vertx.core.json.*;
import io.vertx.core.Vertx;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;

public class BackendVerticle extends AbstractVerticle {
	private IChannelRepository channelRepository = new InMemoryChannelRepository();

	@Override
	public void start(Future<Void> startFuture) {
		// Create some default data.
		Channel defaultChannel = new Channel();
		defaultChannel.AddMember("test");
		defaultChannel.SendMessage(new Message("test", "Hello World!"));
		channelRepository.saveChannel(defaultChannel);

		// Set up the routes used by our application.
		Router router = Router.router(vertx);	
	    router.route("/api/channels*").handler(BodyHandler.create());
		// Set up a route to search for channels using query params.
		// Route example: /api/channels?member=test
	    Route channelsRoute = router.get("/api/channels");
	    channelsRoute.handler(this::getChannelsByParam);

	    // Set up a route to retrieve a specific channel by identifier.
	    // Route example: /api/channels/0
	    Route channelsIdentifierRoute = router.get("/api/channels/:identifier");
	    channelsIdentifierRoute.handler(this::getChannelsByIdentifier);

	    // Set up a route to add new channels.
	    // Route example: /api/channels (with body)
		router.post("/api/channels").handler(this::addChannel);

		// Set up a route to send messages.
	    // Route example: /api/channels/1/messages (with body)
	    Route sendMessageRoute = router.post("/api/channels/:identifier/messages");
	    sendMessageRoute.handler(this::addMessage);

	    vertx
	    	.createHttpServer()
	     	.requestHandler(router)
	      	.listen(8080, result -> {
          		if (result.succeeded()) {
            		startFuture.complete();
          		} else {
            		startFuture.fail(result.cause());
          		}
        	});
	}

	private void getChannelsByParam(RoutingContext routingContext) {
		String member = routingContext.request().getParam("member");
		Collection<Channel> channel = channelRepository.getChannelsByMember(member);
		routingContext.response()
      		.putHeader("content-type", "application/json; charset=utf-8")
      		.end(Json.encodePrettily(channel));
	}

	private void getChannelsByIdentifier(RoutingContext routingContext) {
		String identifier = routingContext.request().getParam("identifier");
		Channel channel = channelRepository.getChannelByIdentifier(identifier);
		routingContext.response()
      		.putHeader("content-type", "application/json; charset=utf-8")
      		.end(Json.encodePrettily(channel));
	}

	private void addChannel(RoutingContext routingContext) {
		JsonObject requestBody = routingContext.getBodyAsJson();
		JsonArray membersArray = requestBody.getJsonArray("members");
		Channel newChannel = new Channel();
		Iterator membersArrayIterator = membersArray.iterator();
		while (membersArrayIterator.hasNext()) {
			newChannel.AddMember(membersArrayIterator.next().toString());
		}
		channelRepository.saveChannel(newChannel);
		routingContext.response()
		    .setStatusCode(201)
		    .putHeader("content-type", "application/json; charset=utf-8")
		    .end(Json.encodePrettily(newChannel));
	}

	private void addMessage(RoutingContext routingContext) {
		// Create a message from the body of the request.
		JsonObject messageJson = routingContext.getBodyAsJson();
		String creator = messageJson.getString("creator");
		String content = messageJson.getString("content");
		Message message = new Message(creator, content);

		// Get the channel specified in the route.
		String channelIdentifier = routingContext.request().getParam("identifier");
		Channel channel = channelRepository.getChannelByIdentifier(channelIdentifier);

		// Send the message, then save the updated channel.
		channel.SendMessage(message);
		channelRepository.saveChannel(channel);

		routingContext.response()
		    .setStatusCode(201)
		    .putHeader("content-type", "application/json; charset=utf-8")
		    .end(Json.encodePrettily(message));
	}
}
