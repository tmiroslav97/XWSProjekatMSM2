import React, { useState, useEffect } from 'react';
import AdDetailViewComponent from '../../components/Ad/AdDetailViewComponent';
import { useDispatch, useSelector } from 'react-redux';
import { Container, Row, Col } from 'react-bootstrap';

import PaginationContainer from '../Pagination/PaginationContainer';
import PaginationSize from '../../components/Pagination/PaginationSize';
import { adSelector } from '../../store/ad/selectors'; 
import { fetchAd } from '../../store/ad/actions';
import SpinnerContainer from '../Common/SpinnerContainer';


const AdDetailViewContainer = (props) => {
    const dispatch = useDispatch();
    const ad = useSelector(adSelector);
    const isFetchAd = ad.isFetch;
    const adId = props.match.params.ad;
    const token = localStorage.getItem('token');

    useEffect(() => {
        dispatch(
            fetchAd({
               adId
            })
        );
    }, []);
    
    console.log(ad);
    
    const handleDateFormat = (event) => {
        var datum = new Date(event);
        let date = datum.getDate();
        let month = datum.getMonth() + 1;
        let year = datum.getFullYear();
        let hours = datum.getHours();
        let minutes = datum.getMinutes();
        let ret = "";
        if (month < 10) {
            month = "0" + month;
        }
        if (date < 10) {
            date = "0" + date;
        }
        if (hours < 10) {
            hours = "0" + hours;
        }
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        ret = + hours + ":" + minutes + " " +  date  + "-" + month + "-" + year;
        return ret;
    }
    
    const handleYear = (event) => {
        var datum = new Date(event);
        var year = datum.getFullYear();
        return year;
    }

    return(
       
        <Container >
       
            <Row>
                <Col >
                {/* <AdDetailViewComponent id={adId} ad={ad.data}/> */}
                

                    {
                        isFetchAd ?  <AdDetailViewComponent id={adId} 
                                                            ad={ad.data} 
                                                            token={token}
                                                            handleDateFormat={handleDateFormat}
                                                            handleYear={handleYear}
                                                            /> : <SpinnerContainer />
                    }
                </Col>
            </Row>

        </Container>

       
    );
}

export default AdDetailViewContainer;