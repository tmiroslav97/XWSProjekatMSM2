import React, { useState, useEffect } from 'react';
import { Container, Row, Col } from 'react-bootstrap'
import { history } from '../../index';
import CartComponent from '../../components/Request/CartComponent';
import { useSelector, useDispatch } from 'react-redux';
import { searchDataSelector } from '../../store/ad/selectors';
import { putSuccessMsg, putErrorMsg } from '../../store/common/actions';
import RequestService from '../../services/RequestService';

const CartContainer = () => {
    const dispatch = useDispatch();
    const [cart, setCart] = useState(new Map(JSON.parse(localStorage.getItem('cart'))));

    const handleCreateReq = async () => {
        const obj = {};
        [...cart.keys()].map((item, idx) => {
            obj[item] = cart.get(item);
        });
        const result = await RequestService.submitReq(obj);
        console.log(result);
        if(result === 'Zahtjev uspjesno kreiran.'){
            dispatch(putSuccessMsg(result));
        }else{
            dispatch(putErrorMsg(result));
        }
        handleClearCart();
    };

    const handleBundle = (event, item) => {
        cart.get(item).bundle = event.target.checked;
        setCart(cart);
        localStorage.setItem('cart', JSON.stringify(Array.from(cart.entries())));
    };

    const handleClearCart = () => {
        const temp = new Map();
        localStorage.setItem('cart', JSON.stringify(Array.from(temp.entries())));
        setCart(temp);
    };

    const handleRemoveItem = (item) => {
        cart.delete(item);
        const temp = new Map(cart);
        localStorage.setItem('cart', JSON.stringify(Array.from(temp.entries())));
        setCart(temp);
    };

    return (
        <Container fluid>
            <Row>
                <Col md={{ span: 12, offset: 1 }} xs={12}>
                    <CartComponent cart={cart} handleCreateReq={handleCreateReq} handleRemoveItem={handleRemoveItem} handleClearCart={handleClearCart} handleBundle={handleBundle} />
                </Col>
            </Row>
        </Container >
    );
}

export default CartContainer;
