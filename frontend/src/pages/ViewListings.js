import React from 'react';
import axios from'axios';

const ViewListings = ({ ws }) => { 
    
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
        axios.post('/submit-listing', body)
            .then(fetchMessages);
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
        ws.addEventListener('message', (message) => {
            console.log(message);
            const parsedData = JSON.parse(message.data);
            console.log(parsedData);
            setMessageList(parsedData.messages); // triggers the refresh
        });
    }, []);

    return (
        <div>
            <h1>View Listings</h1>
            {/* <input value={message} onChange={handleMessageUpdate}/>
            <button onClick={handleSubmit}>Submit</button> */}

            <div>
                {messageList.map((object, i) => <div key={i}>{object.message}</div>)}
            </div>       
        </div>
    );
};

export default ViewListings;