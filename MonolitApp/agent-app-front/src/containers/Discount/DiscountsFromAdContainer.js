import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Container, Row, Col, Button } from 'react-bootstrap';
import DiscountsFromAdComponent from '../../components/Discount/DiscountsFromAdComponent';
import { discountsSelector } from '../../store/ad/selectors';
import { fetchDiscountsFromAgent } from '../../store/ad/actions';
import { adSelector } from '../../store/ad/selectors';
import { fetchAd } from '../../store/ad/actions';
import SpinnerContainer from '../Common/SpinnerContainer';

const DiscountsFromAdContainer = (props) => {
    const dispatch = useDispatch();
    const discounts = useSelector(discountsSelector);
    const ad = useSelector(adSelector);

    useEffect(() => {
        dispatch(
            fetchDiscountsFromAgent()
        );
        dispatch(
            fetchAd({"adId": props.adId})
        );
    }, []);

    const getDiscountList = () => {
        let list = [];
        if (discounts.data != "") {
            discounts.data.map((discount) => {
                let dugme = [];
                // discount.adsId.map((ad))
                // if(discount.adsId === props.adId)
                list.push(
                    <tr key={discount.id}>
                        <td>{discount.discount}</td>
                        <td>{discount.dayNum}</td>
                        <td>

                        </td>
                    </tr>
                );
                dugme = [];
            })
        }
        return list;
    };

    

    return (
        <Container>
            <Row>
                <Col >
                    {discounts.isFetch && ad.isFetch?
                        <DiscountsFromAdComponent
                            getDiscountList={getDiscountList}
                            handleBack={props.handleBack}
                            
                        />
                        : null
                    }

                </Col>
            </Row>
        </Container>
    );
}
export default DiscountsFromAdContainer;