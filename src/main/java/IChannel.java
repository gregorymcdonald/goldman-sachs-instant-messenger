import java.util.List;

/**
 * Interface describing a conversation between one or more participants, i.e. a channel.
 *
 * @author Gregory McDonald
 * @since Mar 04, 2020
 */
interface IChannel {
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