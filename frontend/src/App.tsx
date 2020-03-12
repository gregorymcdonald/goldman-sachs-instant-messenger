import * as React from 'react';
import { render } from 'react-dom';

import ChannelPanel from './channel-panel/ChannelPanel';

import './App.scss';
import MessagesPanel from './messages-panel/MessagesPanel';
import { Channel } from './shared/Channel';

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
  
    handleChange(event) {
      this.setState({username: event.target.value})
    }

    private onChannelClick(channel: Channel): void {
        this.setState({selectedChannelIdentifier: channel.identifier});
    }
  
    componentDidMount() {
      fetch("http://localhost:8080/api/channels?member=Gregory")
        .then(response => response.json())
        .then(json => this.setState({channels: json}));
    }
  
    render () {
      const selectedChannel: Channel = this.state.channels.find(v => v.identifier === this.state.selectedChannelIdentifier)
      return (
        <div className="container">
            <ChannelPanel user={this.state.username} channels={this.state.channels} onChannelClick={this.onChannelClick.bind(this)}/>
            { selectedChannel
                ? <MessagesPanel user={this.state.username} messages={selectedChannel.messages}/>
                : null
            }
            <input className="username" type="text" value={this.state.username} onChange={this.handleChange.bind(this)}/>
        </div>
      );
    }
  }

render(
    <App />,
    document.getElementById('root')
);