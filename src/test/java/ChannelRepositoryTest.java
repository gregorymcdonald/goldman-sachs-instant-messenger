import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;

public class ChannelRepositoryTest {
    @Test
    public void SaveChannel_Null_ThrowsException() {
        IChannelRepository channelRepository = new InMemoryChannelRepository();
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            channelRepository.SaveChannel(null);
        });
    }

    @Test
    public void GetChannelByIdentifier_Null_ReturnsNull() {
        IChannelRepository channelRepository = new InMemoryChannelRepository();
        Channel channel = channelRepository.GetChannelByIdentifier(null);
        Assert.assertNull(channel);
    }

    @Test
    public void GetChannelByIdentifier_BogusIdentifier_ReturnsNull() {
        IChannelRepository channelRepository = new InMemoryChannelRepository();
        Channel channel = channelRepository.GetChannelByIdentifier("BOGUS");
        Assert.assertNull(channel);
    }

    @Test
    public void SaveChannel_GetChannelByIdentifier_ReturnsSavedChannel() {
        IChannelRepository channelRepository = new InMemoryChannelRepository();
        Channel channel = new Channel();
        channelRepository.SaveChannel(channel);
        Assert.assertSame(channel, channelRepository.GetChannelByIdentifier(channel.GetIdentifier()));
    }

    @Test
    public void GetChannelByUser_BogusUser_ReturnsEmpty() {
        IChannelRepository channelRepository = new InMemoryChannelRepository();
        Collection<Channel> channels = channelRepository.GetChannelsByUser("BOGUS");
        Assert.assertEquals(0, channels.size());
    }

    @Test
    public void GetChannelByUser_ValidUser_ReturnsChannels() {
        // Create a channel with one member named "test".
        Channel channel = new Channel();
        channel.AddMember("test");

        IChannelRepository channelRepository = new InMemoryChannelRepository();
        channelRepository.SaveChannel(channel);

        Collection<Channel> channels = channelRepository.GetChannelsByUser("test");
        Assert.assertEquals(1, channels.size());
    }
}