import java.util.List;

interface IChannel {
    public List<Message> GetMessages();
    public void SendMessage(Message message);
}