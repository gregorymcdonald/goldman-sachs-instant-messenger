import org.junit.Assert;
import org.junit.Test;

public class ChannelTest {
    @Test
    public void NoArgumentConstructor_HasNoMessages() {
        Channel channel = new Channel();
        Assert.assertEquals(0, channel.GetMessages().size());
    }

    @Test
    public void DifferentInstances_HaveDifferentIdentifiers() {
        Channel channel1 = new Channel();
        Channel channel2 = new Channel();
        Assert.assertNotEquals(channel1.GetIdentifier(), channel2.GetIdentifier());
    }
}