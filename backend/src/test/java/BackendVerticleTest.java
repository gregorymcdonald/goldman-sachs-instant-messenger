import io.vertx.core.json.*;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class BackendVerticleTest {
    private Vertx vertx;

    @Before
    public void setUp(TestContext context) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(BackendVerticle.class.getName(), context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void GET_channels_NotEmpty(TestContext context) {
        final Async async = context.async();

        vertx.createHttpClient().getNow(8080, "localhost", "/api/channels?member=Gregory",
            response -> {
                response.exceptionHandler(e -> {
                    context.fail(e);
                });
                response.handler(buffer -> {
                    JsonArray channels = (JsonArray) Json.decodeValue(buffer);
                    context.assertFalse(channels.isEmpty());
                    async.complete();
                });
        });
    }

    @Test
    public void POST_channels_AddsChannel(TestContext context) {
        final Async async = context.async();

        // Create a JSON object representing a channel to add.
        JsonObject newChannel = new JsonObject();
        JsonArray newChannelMembers = new JsonArray();
        newChannelMembers.add("tooth");
        newChannelMembers.add("nail");
        newChannel.put("members", newChannelMembers);

        HttpClient client = vertx.createHttpClient();
        client.post(8080, "localhost", "/api/channels",
            response -> {
                response.exceptionHandler(e -> {
                    context.fail(e);
                });
                response.handler(buffer -> {
                    JsonObject channel = (JsonObject) Json.decodeValue(buffer);
                    context.assertFalse(channel.getString("identifier").isEmpty());
                    context.assertTrue(channel.getJsonArray("messages").isEmpty());
                    async.complete();
                });
        }).end(newChannel.toBuffer());
    }
}