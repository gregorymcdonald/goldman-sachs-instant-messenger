import java.io.IOException;

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
	    Route channelsRoute = router.get("/api/channels");
	    channelsRoute.handler(this::getChannelsByMember);

	    vertx
	    	.createHttpServer()
	     	.requestHandler(router)
	      	.listen(8080);
	}

	private void getChannelsByMember(RoutingContext routingContext) {
		try {
			String member = routingContext.request().getParam("member");
			routingContext.response()
	      		.putHeader("content-type", "application/json; charset=utf-8")
	      		.end(Json.encodePrettily(channelRepository.getChannelsByMember(member)));
	      	}
	    catch (Exception e) {
	    	System.out.println(e);
	    }
	}
}
