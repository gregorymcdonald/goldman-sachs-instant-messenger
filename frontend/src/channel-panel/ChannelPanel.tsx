import * as React from 'react';
import './ChannelPanel.scss';
import { Channel } from '../shared/Channel';

interface Props {
  user: string;
  channels?: Channel[];
  onChannelClick?: (channel: Channel) => void;
}

export default class ChannelPanel extends React.Component<Props> {
  private getChannelName(channel: Channel): string {
    if (!channel || !channel.messages) {
      return "?";
    }
    const channelMembersExcludingUser = channel.members.filter(m => m !== this.props.user);
    return channelMembersExcludingUser.length > 0 ? channelMembersExcludingUser.join(', ') : 'You';
  }

  private getSnippet(channel: Channel): string {
    if (!channel || !channel.messages || channel.messages.length === 0) {
      return "";
    }
    const mostRecentMessage = channel.messages[channel.messages.length - 1];
    const prefix = this.props.user === mostRecentMessage.creator ? 'You: ' : '';
    return prefix + mostRecentMessage.content;
  }

  render () {
    const channels = this.props.channels.map((channel, i) => 
        <div className="channel" key={channel.identifier} onClick={() => this.props.onChannelClick ? this.props.onChannelClick(channel) : ''}>
            <div className="avatar"></div>
            <div className="text-content" key={channel.identifier}> 
              <span key={channel.identifier}>{this.getChannelName(channel)}</span>
              <span className="snippet" key={channel.identifier}>{this.getSnippet(channel)}</span>
            </div>
        </div>
    );

    return (
      <div className="channel-panel">
          <h1>Channels</h1>
          {channels}
      </div>
    );
  }
}