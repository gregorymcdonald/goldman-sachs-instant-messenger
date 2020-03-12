import * as React from 'react';
import './MessagesPanel.scss';
import { Message } from '../shared/Message';

interface Props {
  user: string;
  messages?: Message[];
  onSend?: (message: Message) => void;
}

export default class MessagesPanel extends React.Component<Props> {
  state = {
    textMessage: ''
  };

  private handleTextMessageChange(event) {
    this.setState({textMessage: event.target.value})
  }

  private sendMessage(): void {
    const message: Message = {
      creator: this.props.user,
      content: this.state.textMessage
    };
    this.props.onSend(message)
    this.setState({textMessage: ''})
  }

  render () {
    const messages = this.props.messages.map(
      (message, i) => {
        const className: string = `message${message.creator === this.props.user ? ' you' : ''}`;
        return (
          <div className={className} key={i}>
              <span>{message.content}</span>
          </div>
        );
      }
    );

    return (
      <div className="messages-panel">
          <h1>Messages</h1>
          <div className="message-scroll">
            {messages}
          </div>
          <div className="compose-container">
            <input className="compose-input"
                   type="text"
                   placeholder="Text message"
                   value={this.state.textMessage}
                   onChange={this.handleTextMessageChange.bind(this)}
                   onKeyPress={(event) => event.key === 'Enter' ? this.sendMessage() : ''} />
            <button onClick={() => this.sendMessage()}
                    disabled={!this.state.textMessage}>
              Send
            </button>
          </div>
      </div>
    );
  }
}