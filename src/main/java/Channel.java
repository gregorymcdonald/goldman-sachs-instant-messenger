import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Interface describing a conversation between one or more members, i.e. a channel.
 *
 * @author Gregory McDonald
 * @since Mar 10, 2020
 */
public class Channel {
    private String identifier;
    private Set<String> members;
    private List<Message> messages;

    /**
     * Default Channel constructor.
     * Initializes an empty channel with no messages.
     */
    public Channel() {
        identifier = UUID.randomUUID().toString();
        members = new HashSet<String>();
        messages = new ArrayList<Message>();
    }

    /**
     * @return a string uniquely identifying the channel. This identifier will not change over the lifetime of the channel.
     */
    public String GetIdentifier() {
        return identifier;
    }

    /**
     * @return a List of all user(s) that are members of the channel.
     */
    public Collection<String> getMembers() {
        return new HashSet(members);
    }

    /**
     * Add a member to the channel.
     *
     * @param user - the user to add to the channel
     */
    public void AddMember(String user) {
        if (user == null || user.isEmpty()) {
            throw new IllegalArgumentException("user can not be null or empty");
        }
        members.add(user);
    }

    /**
     * Remove a member from the channel.
     *
     * @param user - the user to remove from the channel
     */
    public void RemoveMember(String user) {
        members.remove(user);
    }

    /**
     * @return a List of all message(s) sent to the channel in the order they were sent.
     */
    public List<Message> getMessages() {
        // Deepcopy the internal messages array to prevent mangling.
        ArrayList<Message> clone = new ArrayList<Message>(messages.size());
        for(Message m : this.messages) {
            clone.add(new Message(m));
        }
        return clone;
    }

    /**
     * Send a message to the channel.
     * @param message - Message to send.
     */
    public void SendMessage(Message message) {
        this.messages.add(new Message(message));
    }
}