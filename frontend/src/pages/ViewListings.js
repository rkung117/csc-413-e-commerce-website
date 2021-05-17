import React from 'react';
import axios from'axios';

const ViewListings = ({ ws }) => { 
    // text in the box
    const [email, setEmail] = React.useState('');
    const [product, setProduct] = React.useState('');
    const [description, setDescription] = React.useState('');
    const [price, setPrice] = React.useState('');
    const [toDelete, setToDelete] = React.useState('');
    
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

    const handleToDelete = (e) => {
        setEmail(e.target.email);
        console.log(e.target.email);
        setProduct(e.target.product);
        setDescription(e.target.description);
        setPrice(e.target.price);
        // setToDelete(e.target.value);
    }

    const fetchMessages = () => {
        axios.get('/get-listing') //asyc
        .then((res) => {
            // res is what the spark server sent back
            console.log(res.data);
            setMessageList(res.data) // save for using on the page
        });
    };

    // const deleteMessages = () => {
    //     axios.delete('/delete-listing');

    // };

    const handleDelete = () => {
        const body = {
            // email : email,
            // product : product,
            // description : description,
            // price : price,
            email : email,
            product : product,
            description : description,
            price : price
        };
        axios.post('/delete-listing', body)
        // deleteMessages();
    };

    React.useEffect(() => {
        // Trigger only 1 time
        fetchMessages();
        // deleteMessages();

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

    return (
        <div>
            <h1>View Listings</h1>
            {/* <input value={message} onChange={handleMessageUpdate}/>
            <button onClick={handleSubmit}>Submit</button> */}

            <div>
                {messageList.map((object, i) => 
                <div value={toDelete} key={i}>
                    <section value={email} onChange={handleToDelete}>Email: {object.email}<br></br> </section>
                    <section value={product} onChange={handleToDelete}>Product: {object.product}<br></br> </section>
                    <section value={description} onChange={handleToDelete}>Description: {object.description}<br></br> </section>
                    <section value={price} onChange={handleToDelete}>Price: {object.price}<br></br></section>
                    <button onClick={handleDelete}>Delete Listing</button><br></br><br></br> 
                </div>)}      
            </div>
                
        </div>
    );
};

export default ViewListings;