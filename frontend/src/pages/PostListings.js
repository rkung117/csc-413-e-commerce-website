import axios from 'axios';
import React from 'react';

// watch week 10 classwork 9 - 90 min mark for sending info to backend
const PostListings = ({ ws }) => { // props incoming in the function
    // text in the box
    const [email, setEmail] = React.useState('');
    const [product, setProduct] = React.useState('');
    const [description, setDescription] = React.useState('');
    const [price, setPrice] = React.useState('');
    
    // list of messages
    const [messageList, setMessageList] = React.useState([]);

    const handleEmail = (e) => {
        setEmail(e.target.value);
    };

    const handleProduct = (e) => {
        setProduct(e.target.value);
    };

    const handleDescription = (e) => {
        setDescription(e.target.value);
    };

    const handlePrice = (e) => {
        setPrice(e.target.value);
    };

    const handleSubmit = () => {
        const body = {
            email : email,
            product : product,
            description : description,
            price : price,
        };
        axios.post('/submit-listing', body)
            .then(fetchMessages);
        setEmail('');
        setProduct('');
        setDescription('');
        setPrice('');
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
        ws.addEventListener('email, product, description, price', 
            (email, product, description, price) => {
            const parsedData = JSON.parse(email.data, product.data,
                description.data, price.data);
            console.log(parsedData);
            setMessageList(parsedData.emails, parsedData.product,
                parsedData.description, parsedData.price); // triggers the refresh
        });
    }, []);
    
    return(
        <div>
            <h1>Post Listings</h1>

            <form id="postForm">
                <h3 id="email">Email</h3>
                <input required id="postEmail" value={email} type="email" onChange={handleEmail}/>
            
                <h3 id="product">Product</h3>
                <input required id="postProduct" value={product} onChange={handleProduct}/>

                <h3 id="description">Description</h3>
                <textarea required id="postDescription" value={description} onChange={handleDescription}/>

                <h3 id="price">Price</h3>
                <input required id="postPrice" value={price} type="number" onChange={handlePrice}/>

                <button id="postSubmit" type="submit" onClick={handleSubmit}>Submit Listing</button>
            </form>
            

            {/* <div>
                {messageList.map((object, i) => 
                <div key={i}>
                    {object.email} {object.product} {object.description} {object.price}
                </div>)}
            </div> */}
        </div>
    );
};

export default PostListings;