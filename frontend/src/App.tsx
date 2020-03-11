import * as React from 'react';
import { render } from 'react-dom';

import ChannelPanel from './channel-panel/ChannelPanel';

import './App.scss';
import MessagesPanel from './messages-panel/MessagesPanel';

class App extends React.Component {
    state = {
      username: "Gregory",
      channels: [],
      selectedChannel: {
          messages: [
              {
                  creator: "Gregory",
                  content: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exe"
              },
              {
                creator: "Hannah",
                content: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exe"
              }
          ]
      }
    };
  
    handleChange(event) {
      this.setState({username: event.target.value})
    }
  
    componentDidMount() {
      fetch("http://localhost:8080/api/channels?member=Gregory")
        .then(response => response.json())
        .then(json => this.setState({channels: json}));
    }
  
    render () {
      return (
        <div className="container">
            <ChannelPanel user={this.state.username} channels={this.state.channels} />
            <MessagesPanel user={this.state.username} messages={this.state.selectedChannel.messages}/>
            <input className="username" type="text" value={this.state.username} onChange={this.handleChange.bind(this)}/>
        </div>
      );
    }
  }

render(
    <App />,
    document.getElementById('root')
);