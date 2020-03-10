import java.util.Collection;

/**
 * Interface describing a repository for channels.
 *
 * @author Gregory McDonald
 * @since Mar 08, 2020
 */
interface IChannelRepository {
    /**
     * @return a Channel with the specified identifier. null if not found.
     */
    public Channel GetChannelByIdentifier(String identifier);

    /**
     * @return a List of all channels that the specified member is a member of.
     */
    public Collection<Channel> getChannelsByMember(String member);

    /**
     * Save a new or existing channel.
     *
     * @param channel - the channel to save.
     */
    public void SaveChannel(Channel channel);

    /**
     * Delete an existing channel.
     *
     * @param identifier - the unique identifier of the channel to delete.
     */
    public void DeleteChannel(String identifier);
}