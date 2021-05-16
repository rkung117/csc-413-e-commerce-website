import React from 'react';
import axios from 'axios';

const Login = ({ ws }) => {

  const [message, setMessage] = React.useState('');
  const [messageList, setMessageList] = React.useState([]);

  const handleMessageUpdate = (e) => {
    setMessage(e.target.value);
  };

  const handleSubmit = () => {
    console.log(message);
    const body = {
      message : message,
    };
    axios.post('/postListing', body)
      .then(fetchMessages);
    setMessage('');
  };

  const fetchMessages = () => {
    axios.get('/viewListings')
      .then((res) => {
        console.log(res.data);
        setMessageList(res.data);
      });
  };

  React.useEffect(() => {
    fetchMessages();

    ws.addEventListener('message', (message) => {
      console.log(message);
      const parsedData = JSON.parse(message.data);
      console.log(parsedData);
      setMessageList(parsedData.messages);
    });
  }, []);

  return (
    <div>
      <h1>Login</h1>
      <section>Email</section>
      <input value={message} onChange={handleMessageUpdate}/>
      <button onClick={handleSubmit}>Submit</button>

      <div>
      {messageList.map((object, i) => <div key={i}>{object.message}</div>)}
      </div>
    </div>
  );
};

export default Login;