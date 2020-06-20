import React, { useState, useEffect } from 'react';
import { Container, Row, Col } from 'react-bootstrap'
import { history } from '../../index';
import CartComponent from '../../components/Request/CartComponent';
import { useSelector, useDispatch } from 'react-redux';
import { searchDataSelector } from '../../store/ad/selectors';
import { isEmptyObject } from 'jquery';
import { putSuccessMsg } from '../../store/common/actions';
import RequestService from '../../services/RequestService';

const CartContainer = () => {
    const dispatch = useDispatch();
    const [cart, setCart] = useState(new Map(JSON.parse(localStorage.getItem('cart'))));
    const searchData = useSelector(searchDataSelector);

    const handleCreateReq = async () => {
        const obj = {};
        [...cart.keys()].map((item, idx) => {
            obj[item] = cart.get(item);
        });
        const result = await RequestService.submitReq(obj);
        dispatch(putSuccessMsg(result));
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
                <Col md={{ span: 8, offset: 2 }} xs={12}>
                    <CartComponent cart={cart} handleCreateReq={handleCreateReq} startDate={searchData.startDate} endDate={searchData.endDate} handleRemoveItem={handleRemoveItem} handleClearCart={handleClearCart} handleBundle={handleBundle} />
                </Col>
            </Row>
        </Container >
    );
}

export default CartContainer;
