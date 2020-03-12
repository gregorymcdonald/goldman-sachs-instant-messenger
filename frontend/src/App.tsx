import * as React from 'react';
import { render } from 'react-dom';

import ChannelPanel from './channel-panel/ChannelPanel';

import './App.scss';
import MessagesPanel from './messages-panel/MessagesPanel';
import { Channel } from './shared/Channel';
import { Message } from './shared/Message';

interface State {
    username: string,
    channels: Channel[],
    selectedChannelIdentifier: string
}

class App extends React.Component {
    public state: State = {
      username: "Gregory",
      channels: [],
      selectedChannelIdentifier: null
    };
    
    /**
     * Refresh the data displayed in the App.
     */
    private refresh(): void {
        fetch(`http://localhost:8080/api/channels?member=${this.state.username}`)
            .then(response => response.json())
            .then(json => this.setState({channels: json}));
    }

    private onUsernameChange(event: any): void {
      this.setState({
          username: event.target.value,
          channels: [],
          selectedChannelIdentifier: null
        }, this.refresh);
    }

    private onChannelClick(channel: Channel): void {
        this.setState({selectedChannelIdentifier: channel.identifier});
    }

    private onSendClick(message: Message): void {
        fetch(`http://localhost:8080/api/channels/${this.state.selectedChannelIdentifier}/messages`, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                creator: message.creator,
                content: message.content,
            })
        }).then(response => this.refresh());
    }
  
    componentDidMount() {
      this.refresh();
    }
  
    render () {
      const selectedChannel: Channel = this.state.channels.find(v => v.identifier === this.state.selectedChannelIdentifier)
      return (
        <div className="container">
            <div className="left-panel">
                <div>
                    <span>User:&nbsp;</span>
                    <input className="username"
                           type="text"
                           value={this.state.username}
                           onChange={this.onUsernameChange.bind(this)} />
                </div>
                <ChannelPanel user={this.state.username} channels={this.state.channels} onChannelClick={this.onChannelClick.bind(this)} />
            </div>
            { selectedChannel
                ? <MessagesPanel user={this.state.username} messages={selectedChannel.messages} onSend={this.onSendClick.bind(this)}/>
                : null
            }
            
        </div>
      );
    }
  }

render(
    <App />,
    document.getElementById('root')
);