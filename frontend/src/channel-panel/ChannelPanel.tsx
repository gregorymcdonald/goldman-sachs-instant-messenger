import * as React from 'react';
import './ChannelPanel.scss';

interface Message {
  creator: string;
  content: string;
}

interface Channel {
  identifier: string;
  members: string[];
  messages?: Message[];
}

interface Props {
  channels?: Channel[];
}

export default class ChannelPanel extends React.Component<Props> {
  private getSnippet(channel: Channel): string {
    if (!channel || !channel.messages) {
      return "";
    }
    return channel.messages[0].content;
  }

  render () {
    const channels = this.props.channels.map((channel, i) => 
        <div className="channel" key={channel.identifier}>
            <div className="avatar"></div>
            <div className="text-content"> 
              <span>{channel.members.join(', ')}</span>
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