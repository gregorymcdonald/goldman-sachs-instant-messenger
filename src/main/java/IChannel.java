import java.util.Collection;
import java.util.List;

/**
 * Interface describing a conversation between one or more participants, i.e. a channel.
 *
 * @author Gregory McDonald
 * @since Mar 08, 2020
 */
interface IChannel {
    /**
     * @return a string uniquely identifying the channel. This identifier will not change over the lifetime of the channel.
     */
    public String GetIdentifier();

    /**
     * @return a List of all user(s) that are members of the channel.
     */
    public Collection<String> GetMembers();

    /**
     * Add a member to the channel.
     *
     * @param user - the user to add to the channel
     */
    public void AddMember(String user);

    /**
     * Remove a member from the channel.
     *
     * @param user - the user to remove from the channel
     */
    public void RemoveMember(String user);

    /**
     * @return a List of all message(s) sent to the channel in the order they were sent.
     */
    public List<Message> GetMessages();

    /**
     * Send a message to the channel.
     * @param message - Message to send.
     */
    public void SendMessage(Message message);
}