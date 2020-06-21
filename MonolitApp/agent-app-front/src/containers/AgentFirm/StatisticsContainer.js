import React, { useState, useEffect } from 'react';
import AdComponent from '../../components/Ad/AdComponent';
import { useDispatch, useSelector } from 'react-redux';
import { fetchBestGrade } from '../../store/ad/actions';
import StatisticsComponent from '../../components/AgentFirm/StatisticsComponent'
import { adSelector } from '../../store/ad/selectors'; 
import { tokenSelector } from '../../store/user/selectors'; 
import jwt_decode from 'jwt-decode';
import { Container, Row, Col } from 'react-bootstrap';
import SpinnerContainer from '../Common/SpinnerContainer';


const StatisticsContainer = () => {
    const token = useSelector(tokenSelector);
    const dispatch = useDispatch();
    const ad = useSelector(adSelector);
    const isFetchAd = ad.isFetch;
    const email = jwt_decode(token).sub;

    useEffect(() => {
        dispatch(
            fetchBestGrade(email)
        );
    }, []);

    console.log(ad.data)
    return(

      
        <Container>
    
        <Row>
            <Col >

                {
                    isFetchAd ?  <StatisticsComponent ad={ad.data}>  </StatisticsComponent> : <SpinnerContainer />
                }
            </Col>
        </Row>

    </Container>
  
    );
}

export default StatisticsContainer;