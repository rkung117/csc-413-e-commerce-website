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
        // Axios.post('/submit-listing', body)

        setTitle('');
        setDescription('');
        setPrice('');
    };

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
        </div>
    );
};

export default PostListings;