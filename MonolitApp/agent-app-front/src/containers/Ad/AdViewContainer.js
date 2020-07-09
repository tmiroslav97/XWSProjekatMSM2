import React, { useState, useEffect } from 'react';
import AdDetailViewComponent from '../../components/Ad/AdDetailViewComponent';
import { useDispatch, useSelector } from 'react-redux';
import { Container, Row, Col,Button } from 'react-bootstrap';
import jwt_decode from 'jwt-decode';
import PaginationContainer from '../Pagination/PaginationContainer';
import PaginationSize from '../../components/Pagination/PaginationSize';
import { adSelector, searchDataSelector } from '../../store/ad/selectors';
import { putSuccessMsg, putWarnMsg } from '../../store/common/actions';
import { fetchAd } from '../../store/ad/actions';
import SpinnerContainer from '../Common/SpinnerContainer';


const AdViewContainer = (props) => {
    const dispatch = useDispatch();
    const searchData = useSelector(searchDataSelector);
    const ad = useSelector(adSelector);
    const isFetchAd = ad.isFetch;
    const adId = props.adId;
    const token = localStorage.getItem('token');

    useEffect(() => {
        dispatch(
            fetchAd({
                adId
            })
        );
    }, []);

    const hasRole = (accessRole) => {
        if (token != null) {
            const roles = jwt_decode(token).roles;
            const role = roles.filter(val => accessRole.includes(val));
            return role.length > 0;
        } else {
            return false;
        }
    };

    const addToCart = (ad) => {
        let cart = new Map();
        if (JSON.parse(localStorage.getItem('cart')) != null) {
            cart = new Map(JSON.parse(localStorage.getItem('cart')));
        }
        if (cart.get(ad.publisherUserId) == null) {
            cart.set(ad.publisherUserId, { bundle: false, startDate: searchData.startDate, endDate: searchData.endDate, ads: [{ id: ad.id, adName: ad.name }] });
            dispatch(putSuccessMsg('Oglas uspjesno dodat u korpu'));
        } else {
            var temp = cart.get(ad.publisherUserId);
            var flag = false;
            for (let item of temp.ads) {
                if (item.id == ad.id) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                dispatch(putWarnMsg('Oglas ste vec dodali u korpu'));
            } else {
                cart.get(ad.publisherUserId).ads.push({ id: ad.id, adName: ad.name });
                dispatch(putSuccessMsg('Oglas uspjesno dodat u korpu'));
            }
        }

        localStorage.setItem('cart', JSON.stringify(Array.from(cart.entries())));
    };

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
        ret = + hours + ":" + minutes + " " + date + "-" + month + "-" + year;
        return ret;
    }

    const handleYear = (event) => {
        var datum = new Date(event);
        var year = datum.getFullYear();
        return year;
    }

    return (

        <Container >

            <Row>
                <Col >

                    <Button onClick={props.handleBack}>Vrati se</Button>
                    {
                        isFetchAd ? <AdDetailViewComponent
                            id={adId}
                            ad={ad.data}
                            token={token}
                            handleDateFormat={handleDateFormat}
                            handleYear={handleYear}
                            hasRole={hasRole}
                            addToCart={addToCart}
                        /> : <SpinnerContainer />
                    }
                    
                </Col>
            </Row>

        </Container>


    );
}

export default AdViewContainer;