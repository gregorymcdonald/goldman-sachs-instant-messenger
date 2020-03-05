public class Message {
	public String creator;
    public String content;

    public Message(String creator, String content) {
    	this.creator = creator;
    	this.content = content;
    }

    public Message(Message message) {
    	this(message.creator, message.content);
    }
}
