import org.junit.Assert;
import org.junit.Test;

public class MessageTest {
    @Test
    public void AllParametersConstructor_ValidInput_Succeeds() {
        String user = "user";
        String content = "content";
        Message message = new Message(user, content);
        Assert.assertEquals(user, message.getCreator());
        Assert.assertEquals(content, message.getContent());
    }

    @Test
    public void CopyConstructor_ValidInput_Succeeds() {
        Message original = new Message("user", "content");
        Message copy = new Message(original);
        Assert.assertNotSame(original, copy);
        Assert.assertEquals(original.getCreator(), copy.getCreator());
        Assert.assertEquals(original.getContent(), copy.getContent());
    }
}