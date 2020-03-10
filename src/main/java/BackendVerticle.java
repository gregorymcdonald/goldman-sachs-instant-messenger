import java.io.IOException;
import java.util.Collection;

import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.core.Vertx;
import io.vertx.core.AbstractVerticle;
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
		channelRepository.SaveChannel(defaultChannel);

		Router router = Router.router(vertx);
		// Route example: /api/channels?member=test
	    Route channelsRoute = router.get("/api/channels");
	    channelsRoute.handler(this::getChannelsByParam);
	    // Route example: /api/channels/
	    Route channelsIdentifierRoute = router.get("/api/channels/:identifier");
	    channelsIdentifierRoute.handler(this::getChannelsByIdentifier);

	    vertx
	    	.createHttpServer()
	     	.requestHandler(router)
	      	.listen(8080);
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
}
