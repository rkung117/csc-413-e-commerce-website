import React from 'react';

const PostListings = () => {
    const [email, setEmail] = React.useState('');

    const handleEmail = (e) => {
        const inputEmail = e.target.value;
        setEmail(inputEmail);
    };

    return (
        <div>
            <h1>Post Listings</h1>
            <h2>Enter Email</h2>
            <input value={email} onChange={handleEmail} />
        </div>
    );
};

export default PostListings;