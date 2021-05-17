import axios from 'axios';
import React from 'react';

// watch week 10 classwork 9 - 90 min mark for sending info to backend
// to handle email input, should i hide ability to post and have it reveal upon email input
// how do i determine if email input is valid or not?

// watch week 11 classwork 10 - 97 min mark for intro to hw 3
const PostListings = ({ ws }) => { // props incoming in the function
    // text in the box
    const [message, setMessage] = React.useState(''); // message will be description for this part
    const [type, setType] = React.useState('');
    const [title, setTitle] = React.useState('');
    const [price, setPrice] = React.useState('');
    const [email, setEmail] = React.useState('');
    // list of messages
    const [messageList, setMessageList] = React.useState([]);
    const [typeList, setTypeList] = React.useState([]);
    const [priceList, setPriceList] = React.useState([]);
    const [titleList, setTitleList] = React.useState([]);
    

    const handleMessageUpdate = (e) => {
        setMessage(e.target.value);
    };

    const handleTypeUpdate = (e) => {
        setType(e.target.value);
    };

    const handleTitleUpdate = (e) => {
        setTitle(e.target.value);
    };

    const handlePriceUpdate = (e) => {
        setPrice(e.target.value);
    };

    const handleEmail = (e) => {
        setEmail(e.target.value);
    };

    const handleLogin = () => {
        setEmail('');
    };

    const handleSubmit = () => {
        console.log(message);
        console.log(type);
        console.log(price);
        console.log(title);
        const body = {
            message : message,
            type : type,
            price : price,
            title : title,
        };
        axios.post('/submit-listing', body)
            .then(fetchMessages);
        setMessage('');
        setType('');
        setPrice('');
        setTitle('');
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
            const parsedMessageData = JSON.parse(message.data);
            console.log(parsedMessageData);
            setMessageList(parsedMessageData.messages); // triggers the refresh
        });
    }, []);
    
    return(
        <div>
            <h1>Post Listings</h1>

            <h2>Enter Email</h2>
            <input value={email} onChange={handleEmail} />
            <button onClick={handleLogin}>Login</button>
            
            <h2>Listings</h2>
            <input value={message} onChange={handleMessageUpdate}/>
            <input value={type} onChange={handleTypeUpdate}/>
            <input value={price} onChange={handlePriceUpdate}/>
            <input value={title} onChange={handleTitleUpdate}/>
            <button onClick={handleSubmit}>Submit</button>

            <div>
                {messageList.map((object, i) => 
                <div key={i}>
                    Description: {object.message} <br></br>
                    Type: {object.type} <br></br>
                    Price: {object.price} <br></br>
                    Title: {object.title} <br></br>
                    <br></br>
                </div>)}
            </div>
        </div>
    );
};

export default PostListings;