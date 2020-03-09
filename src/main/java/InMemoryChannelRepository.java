import java.util.Collection;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

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

    public Collection<Channel> GetChannelsByUser(String user) {
        ArrayList<Channel> channels = new ArrayList<Channel>();
        for (Channel channel : identifierToChannelMap.values()) {
            if (channel.GetMembers().contains(user)) {
                channels.add(channel);
            }
        }
        return channels;
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