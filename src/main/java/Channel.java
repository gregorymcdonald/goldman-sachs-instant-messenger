import java.util.List;
import java.util.ArrayList;

public class Channel implements IChannel {
    private ArrayList<Message> messages;

    /**
     * Default Channel constructor.
     * Initializes an empty channel with no messages.
     */
    public Channel() {
        this.messages = new ArrayList<Message>();
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