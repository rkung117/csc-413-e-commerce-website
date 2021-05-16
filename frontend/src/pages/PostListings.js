import axios from 'axios';
import React from 'react';

// watch week 10 classwork 9 - 90 min mark for sending info to backend
const PostListings = ({ ws }) => { // props incoming in the function
    // text in the box
    const [message, setMessage] = React.useState('');
    const [email, setEmail] = React.useState('');
    // list of messages
    const [messageList, setMessageList] = React.useState([]);

    const handleMessageUpdate = (e) => {
        setMessage(e.target.value);
    };

    const handleEmail = (e) => {
        setEmail(e.target.value);
    };

    const handleSubmit = () => {
        console.log(email);
        console.log(message);
        const body = {
            email : email,
            message : message,
        };
        axios.post('/submit-listing', body)
            .then(fetchMessages);
            // var email = body.email;
            // var listing = body.message;
        setEmail('');
        setMessage('');
    };

    const fetchMessages = () => {
        axios.get('/get-listing') //asyc
        .then((res) => {
            // res is what the spark server sent back
            console.log(res.data);
            setMessageList(res.data); // save for using on the page
        });
    };

    React.useEffect(() => {
        // Trigger only 1 time
        fetchMessages();

        // listen for ws here
        ws.addEventListener('email, message', (email, message) => {
            console.log(message);
            const parsedData = JSON.parse(email.data, message.data);
            console.log(parsedData);
            setMessageList(parsedData.emails, parsedData.messages); // triggers the refresh
        });
    }, []);
    
    return(
        <div>
            <h1>Post Listings</h1>

            <h2>Enter Email</h2>
            <input value={email} onChange={handleEmail} />
            
            <h2>Listings</h2>
            <input value={message} onChange={handleMessageUpdate}/>
            <button onClick={handleSubmit}>Submit</button>

            <div>
                {messageList.map((object, i) => <div key={i}>{object.email}</div>)}
                {messageList.map((object, i) => <div key={i}>{object.message}</div>)}
            </div>
        </div>
    );
};

export default PostListings;