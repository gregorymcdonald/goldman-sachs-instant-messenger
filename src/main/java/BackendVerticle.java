import java.io.IOException;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class BackendVerticle extends AbstractVerticle {
	@Override
	public void start(Future<Void> startFuture) throws Exception {
		super.start(startFuture);
		Router router = Router.router(vertx);
	    Route messageRoute = router.get("/api/message");
	    messageRoute.handler(rc -> {
	      rc.response().end("Hello React from Vert.x!");
	    });

	    router.get().handler(StaticHandler.create());

	    vertx.createHttpServer()
	      .requestHandler(router)
	      .listen(8080);
	}

	public static void main(String[] args) throws Exception {
		Vertx vertx = Vertx.vertx();
    	vertx.deployVerticle(new BackendVerticle());
	}
}
