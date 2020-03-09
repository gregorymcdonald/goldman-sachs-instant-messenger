import org.junit.Assert;
import org.junit.Test;

public class ChannelTest {
    @Test
    public void NoArgumentConstructor_HasNoMembers() {
        Channel channel = new Channel();
        Assert.assertEquals(0, channel.GetMembers().size());
    }

    @Test
    public void NoArgumentConstructor_HasNoMessages() {
        Channel channel = new Channel();
        Assert.assertEquals(0, channel.GetMessages().size());
    }

    @Test
    public void DifferentInstances_HaveDifferentIdentifiers() {
        Channel channel1 = new Channel();
        Channel channel2 = new Channel();
        Assert.assertNotEquals(channel1.GetIdentifier(), channel2.GetIdentifier());
    }

    @Test
    public void AddMember_Null_ThrowsExcception() {
        Channel channel = new Channel();
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            channel.AddMember(null);
        });
    }

    @Test
    public void AddMember_Empty_ThrowsExcception() {
        Channel channel = new Channel();
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            channel.AddMember("");
        });
    }

    @Test
    public void AddMember_Valid_IncreasesMemberCountByOne() {
        Channel channel = new Channel();
        channel.AddMember("test");
        Assert.assertEquals(1, channel.GetMembers().size());
    }

    @Test
    public void RemoveMember_ExistingMember() {
        Channel channel = new Channel();
        channel.AddMember("test");
        channel.RemoveMember("test");
        Assert.assertEquals(0, channel.GetMembers().size());
    }
}