import Axios from 'axios';
import React from 'react';

// watch week 10 classwork 9 - 90 min mark for sending info to backend
const PostListings = () => {
    const [email, setEmail] = React.useState('');
    const [title, setTitle] = React.useState('');
    const [description, setDescription] = React.useState('');
    const [price, setPrice] = React.useState('');

    const handleEmail = (e) => {
        const inputEmail = e.target.value;
        setEmail(inputEmail);
    };

    const handleTitle = (e) => {
        const inputTitle = e.target.value;
        setTitle(inputTitle);
    };

    const handleDescription = (e) => {
        const inputDescription = e.target.value;
        setDescription(inputDescription);
    };

    const handlePrice = (e) => {
        const inputPrice = e.target.value;
        setPrice(inputPrice);
    };

    const handleSubmit = () => {
        console.log('the title is ' + title);
        console.log('the description is ' + description);
        console.log('the price is' + price);
        
        // sends data in text fields to backend
        // change post endpoint to whatever it is called in the backend
        // const body = {
        //     title : title,
        //     description : description,
        //     price : price,
        // };
        // axios.post('/submit-listing', body)
        //     .then(fetchListings);

        setTitle('');
        setDescription('');
        setPrice('');
    };

    // fetches info from backend
    // const fetchListings = () => {
    //     axios.get('/get-listings') // asyc, waits to finish
    //     .then((res) => {
    //         // res is what spark server sent back
    //         console.log(res.data);
    //         setTitle(res.body.title);
    //         setDescription(res.body.description);
    //         setPrice(res.body.price);
    //         setDescription(res.data.description);
    //     });
    // };

    // make it so listings load when the page loads rather than on submit
    // React.useEffect(() => {
    //     // Trigger only 1 time
    //     fetchListings();
    // }, []);

    return (
        <div>
            <h1>Post Listings</h1>

            <h2>Enter Email</h2>
            <input value={email} onChange={handleEmail} />

            {/* create text fields for listing info */}
            <h2>Enter Listings Info</h2> 
            <h4>Title</h4>
            <input value={title} onChange={handleTitle} />
            <h4>Description</h4>
            <input value={description} onChange={handleDescription} />
            <h4>Price</h4>
            <input value={price} onChange={handlePrice} />

            <button onClick={handleSubmit}>Submit Post</button>

            {/* week 10 classwork 9 - 105 min
            {messageList.map((object, i) => <div key={i}>{object.message}</div>)}
            maps messageList so we print messages every time we click submit and
            also has a history of messages */}
        </div>
    );
};

export default PostListings;