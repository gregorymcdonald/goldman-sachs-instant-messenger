import * as React from 'react';
import './ChannelPanel.scss';

interface Channel {
  identifier: string;
  members: string[];
}

interface Props {
  channels?: Channel[];
}

export default class ChannelPanel extends React.Component<Props> {
  state = {
    channels: [
        {
            identifier: "1",
            members: [
                "Panel"
            ]
        }
    ]
  };

  render () {
    const channels = this.props.channels.map((channel, i) => 
        <div className="channel" key={channel.identifier}>
            <span>{channel.members.join(', ')}</span>
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