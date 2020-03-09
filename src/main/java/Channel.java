import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Channel implements IChannel {
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

    public String GetIdentifier() {
        return identifier;
    }

    public Collection<String> GetMembers() {
        return new HashSet(members);
    }

    public void AddMember(String user) {
        if (user == null || user.isEmpty()) {
            throw new IllegalArgumentException("user can not be null or empty");
        }
        members.add(user);
    }

    public void RemoveMember(String user) {
        members.remove(user);
    }

    public List<Message> GetMessages() {
        // Deepcopy the internal messages array to prevent mangling.
        ArrayList<Message> clone = new ArrayList<Message>(messages.size());
        for(Message m : this.messages) {
            clone.add(new Message(m));
        }
        return clone;
    }

    public void SendMessage(Message message) {
        this.messages.add(new Message(message));
    }
}