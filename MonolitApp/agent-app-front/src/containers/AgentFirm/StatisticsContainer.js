import React, { useState, useEffect } from 'react';
import AdComponent from '../../components/Ad/AdComponent';
import { useDispatch, useSelector } from 'react-redux';
import { fetchBestGrade, fetchMaxMileage } from '../../store/ad/actions';
import StatisticsComponent from '../../components/AgentFirm/StatisticsComponent'
import { adSelector } from '../../store/ad/selectors'; 
import { tokenSelector } from '../../store/user/selectors'; 
import jwt_decode from 'jwt-decode';
import { Container, Row, Col } from 'react-bootstrap';
import SpinnerContainer from '../Common/SpinnerContainer';
import { putAd } from '../../store/ad/actions';



const StatisticsContainer = () => {
    const token = useSelector(tokenSelector);
    const dispatch = useDispatch();
    const ad = useSelector(adSelector);
    const isFetchAd = ad.isFetch;
    const email = jwt_decode(token).sub;
    const [key, setKey] = useState("grade");

    useEffect(() => {
        dispatch(
            fetchBestGrade(email)
        );
        dispatch(
            fetchMaxMileage(email)
        );
        return () => {
            dispatch(putAd({
                'data': [],
                'isFetch': false
            }));

 
        };
    }, []);

    const handleTabClick = (k) => {
        console.log(k)
        setKey(k);
        if(k==="grade"){
           
            dispatch(
                fetchBestGrade(email)
            );
        }else if(k==="mileage"){
            dispatch(
                fetchMaxMileage(email)
            );
        }else{
            console.log("NISYAAAA")
        }
    }

    console.log(ad.data)
    return(

      
        <Container>
    
        <Row>
            <Col >

                {
                    isFetchAd ?  <StatisticsComponent ad={ad.data} handleTabClick={handleTabClick} key={key}>  </StatisticsComponent> : <SpinnerContainer />
                }
            </Col>
        </Row>

    </Container>
  
    );
}

export default StatisticsContainer;