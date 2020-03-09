import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.lang.IllegalArgumentException;
import java.lang.UnsupportedOperationException;

/**
 * Class for an in-memory channel repository, i.e. not persistent.
 *
 * @author Gregory McDonald
 * @since Mar 08, 2020
 */
public class InMemoryChannelRepository implements IChannelRepository {
    // Map from unique identifiers of channels to the Channel.
    private Map<String, Channel> identifierToChannelMap;

    public InMemoryChannelRepository() {
        identifierToChannelMap = new HashMap<String, Channel>();
    }

    public Channel GetChannelByIdentifier(String identifier) {
        return identifierToChannelMap.containsKey(identifier) ? identifierToChannelMap.get(identifier) : null;
    }

    public List<Channel> GetChannelsByUser(String user) {
        throw new UnsupportedOperationException();
    }

    public void SaveChannel(Channel channel) {
        if (channel == null) {
            throw new IllegalArgumentException("channel can not be null");
        }
        identifierToChannelMap.put(channel.GetIdentifier(), channel);
    }

    public void DeleteChannel(String identifier) {
        throw new UnsupportedOperationException();
    }
}