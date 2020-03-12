import * as React from 'react';
import './ChannelPanel.scss';
import { Channel } from '../shared/Channel';

interface Props {
  user: string;
  channels?: Channel[];
  onChannelClick?: (channel: Channel) => void;
}

interface State {
  /** The identifier of the channel that is selected. */
  selected: string;
}

export default class ChannelPanel extends React.Component<Props, State> {
  public state: State = {
    selected: null
  };

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

  private onChannelClick(channel: Channel): void {
    this.setState({selected: channel.identifier});
    if (this.props.onChannelClick) {
      this.props.onChannelClick(channel);
    }
  }

  static getDerivedStateFromProps(nextProps: Props, prevState: State): State {
    if (nextProps.channels && nextProps.channels.find(c => c.identifier === prevState.selected)) {
      return prevState;
    }
    return {
      selected: null
    };
  }

  render () {
    const channels = this.props.channels.map((channel) => 
        <div className={`channel ${this.state.selected === channel.identifier ? "selected" : ""}`} key={channel.identifier} onClick={() => this.onChannelClick(channel)}>
            <div className="avatar"></div>
            <div className="text-content"> 
              <span>{this.getChannelName(channel)}</span>
              <span className="snippet">{this.getSnippet(channel)}</span>
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