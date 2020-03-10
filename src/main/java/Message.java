public class Message {
	private String creator;
    private String content;

	/**
	 * Default Message constructor.
	 * @param creator - name of the user that created this message.
	 * @param content - text of the message
	 */
    public Message(String creator, String content) {
    	this.creator = creator;
    	this.content = content;
    }

	/**
	 * Copy constructor for Message objects.
	 * @param message - Message to copy.
	 */
    public Message(Message message) {
    	this(message.creator, message.content);
    }

    public String getCreator() {
    	return creator;
    }

    public String getContent() {
    	return content;
    }
}
