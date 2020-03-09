import org.junit.Assert;
import org.junit.Test;
import java.lang.IllegalArgumentException;

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
        Channel channel = channelRepository.GetChannelByIdentifier("NOT A REAL IDENTIFIER");
        Assert.assertNull(channel);
    }

    @Test
    public void SaveChannel_GetChannelByIdentifier_ReturnsSavedChannel() {
        IChannelRepository channelRepository = new InMemoryChannelRepository();
        Channel channel = new Channel();
        channelRepository.SaveChannel(channel);
        Assert.assertSame(channel, channelRepository.GetChannelByIdentifier(channel.GetIdentifier()));
    }
}