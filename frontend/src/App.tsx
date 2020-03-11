import * as React from 'react';
import { render } from 'react-dom';

import ChannelPanel from './channel-panel/ChannelPanel';

import './App.scss';

class App extends React.Component {
    state = {
      username: "Gregory",
      channels: [],
    };
  
    handleChange(event) {
      this.setState({username: event.target.value})
    }
  
    componentDidMount() { // (5)
      fetch("http://localhost:8080/api/channels?member=Gregory")
        .then(response => response.json())
        .then(json => this.setState({channels: json}));
    }
  
    render () {
      return (
        <div className="container">
            <ChannelPanel channels={this.state.channels} />
            <input className="username" type="text" value={this.state.username} onChange={this.handleChange.bind(this)}/>
        </div>
      );
    }
  }

render(
    <App />,
    document.getElementById('root')
);