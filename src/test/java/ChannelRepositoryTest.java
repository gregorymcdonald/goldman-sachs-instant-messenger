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
    public void getChannelByIdentifier_Null_ReturnsNull() {
        IChannelRepository channelRepository = new InMemoryChannelRepository();
        Channel channel = channelRepository.getChannelByIdentifier(null);
        Assert.assertNull(channel);
    }

    @Test
    public void getChannelByIdentifier_BogusIdentifier_ReturnsNull() {
        IChannelRepository channelRepository = new InMemoryChannelRepository();
        Channel channel = channelRepository.getChannelByIdentifier("BOGUS");
        Assert.assertNull(channel);
    }

    @Test
    public void SaveChannel_getChannelByIdentifier_ReturnsSavedChannel() {
        IChannelRepository channelRepository = new InMemoryChannelRepository();
        Channel channel = new Channel();
        channelRepository.SaveChannel(channel);
        Assert.assertSame(channel, channelRepository.getChannelByIdentifier(channel.getIdentifier()));
    }

    @Test
    public void GetChannelByUser_BogusUser_ReturnsEmpty() {
        IChannelRepository channelRepository = new InMemoryChannelRepository();
        Collection<Channel> channels = channelRepository.getChannelsByMember("BOGUS");
        Assert.assertEquals(0, channels.size());
    }

    @Test
    public void GetChannelByUser_ValidUser_ReturnsChannels() {
        // Create a channel with one member named "test".
        Channel channel = new Channel();
        channel.AddMember("test");

        IChannelRepository channelRepository = new InMemoryChannelRepository();
        channelRepository.SaveChannel(channel);

        Collection<Channel> channels = channelRepository.getChannelsByMember("test");
        Assert.assertEquals(1, channels.size());
    }
}