import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Container, Row, Col, Button } from 'react-bootstrap';
import DiscountsFromAdComponent from '../../components/Discount/DiscountsFromAdComponent';
import { discountsSelector } from '../../store/ad/selectors';
import { fetchDiscountsFromAgent, addDiscountToAd, removeDiscountFromAd } from '../../store/ad/actions';
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
                let flag = 0;
                discount.adsId.map((ad)=>{
                    if(ad === props.adId){
                        flag = 1;  
                    }
                })
                if(flag === 1){
                    dugme.push(
                        <Button key={discount.id} variant="success"
                        onClick={() => { handlerRemoveDiscountFromAd(discount.id); }}  >
                            Izabrano
                        </Button>
                    )
                }else{
                    dugme.push(
                        <Button key={discount.id}
                        onClick={() => { handlerAddDiscountToAd(discount.id); }}  
                        variant="outline-success"
                        >
                            Odaberi
                        </Button>
                    )
                }
                list.push(
                    <tr key={discount.id}>
                        <td>{discount.discount}</td>
                        <td>{discount.dayNum}</td>
                        <td>{dugme}</td>
                    </tr>
                );
                dugme = [];
            })
        }
        return list;
    };

    const handlerAddDiscountToAd = (discountId) =>{
        console.log("popust "+discountId);
        console.log("oglas "+props.adId);

        dispatch(addDiscountToAd({
            "discountId": discountId,
            "adId": props.adId
        }));
    }

    const handlerRemoveDiscountFromAd = (discountId) =>{
        console.log("popust "+discountId);
        console.log("oglas "+props.adId);

        dispatch(removeDiscountFromAd({
            "discountId": discountId,
            "adId": props.adId
        }));
    }


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