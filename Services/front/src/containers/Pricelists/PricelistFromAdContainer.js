import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Container, Row, Col, Button } from 'react-bootstrap';
import PricelistFromAdComponent from '../../components/Pricelists/PricelistFromAdComponent';
import { pricelistsSelector } from '../../store/pricelist/selectors';
import { fetchPriceListsFromPublisher, reversePricelist } from '../../store/pricelist/actions';
import { adSelector } from '../../store/ad/selectors';
import { fetchAd } from '../../store/ad/actions';
import SpinnerContainer from '../Common/SpinnerContainer';
import { putSuccessMsg, putErrorMsg } from '../../store/common/actions';

const PricelistFromAdContainer = (props) => {
    const dispatch = useDispatch();
    const pricelists = useSelector(pricelistsSelector);
    const ad = useSelector(adSelector);

    useEffect(() => {
        dispatch(
            fetchPriceListsFromPublisher()
        );
        dispatch(
            fetchAd({"adId": props.adId})
        );
    }, []);

    const getPricelists = () => {
        let list = [];
        if (pricelists.data != "") {
            pricelists.data.map((pricelist) => {
                let dugme = [];
                    if(ad.data.priceId === pricelist.id){
                        dugme.push(
                            <Button key={pricelist.id}  variant="success">Izabran</Button>
                        );
                    }else{
                        
                        if(ad.data.distanceLimitFlag === "LIMITED" && pricelist.pricePerKm === null){
                            dugme.push(
                                
                            )
                        }else if(ad.data.cdw === true && pricelist.pricePerKmCDW === null){
                            dugme.push(
                                
                                )
                        }else{
                            dugme.push(
                                <Button key={pricelist.id} 
                                onClick={() => { handlerReverse(pricelist.id); }}  
                                variant="outline-success">Izaberi</Button>
                            )
                        }
                        
                        
                        
                    }
            
                
                let ss = pricelist.creationDate.substring(0, 10);
                let ss2 = pricelist.creationDate.substring(11, 16);
                ss = ss + " " + ss2;

                list.push(
                    <tr key={pricelist.id}>
                        <td>{ss}</td>
                        <td>{pricelist.pricePerDay}</td>
                        <td>{pricelist.pricePerKm}</td>
                        <td>{pricelist.pricePerKmCDW}</td>
                        <td align="right">
                            {dugme}
                        </td>
                        
                    </tr>
                );
                dugme = [];
            })
        }
        return list;
    };

    const handlerReverse = (id)=>{
       
            dispatch(reversePricelist({
                "adId": props.adId,
                "pricelistId": id
            }));
        
        
    };

    return (
        <Container>
            <Row>
                <Col >
                    {pricelists.isFetch && ad.isFetch?
                        <PricelistFromAdComponent
                            getPricelists={getPricelists}
                            handleBack={props.handleBack}
                            
                        />
                        : null
                    }

                </Col>
            </Row>
        </Container>
    );
}
export default PricelistFromAdContainer;