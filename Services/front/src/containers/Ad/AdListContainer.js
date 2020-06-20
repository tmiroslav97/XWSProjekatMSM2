import React, { useState, useEffect } from 'react';
import AdComponent from '../../components/Ad/AdComponent';
import { useDispatch, useSelector } from 'react-redux';
import { Container, Row, Col } from 'react-bootstrap';
import { adsSelector, searchDataSelector } from '../../store/ad/selectors';
import PaginationContainer from '../Pagination/PaginationContainer';
import PaginationSize from '../../components/Pagination/PaginationSize';
import OrdinarySearchContainer from '../../containers/Search/OrdinarySearchContainer'
import { fetchAds } from '../../store/ad/actions';
import SpinnerContainer from '../Common/SpinnerContainer';
import { loadImage } from '../../store/ad/saga';
import jwt_decode from 'jwt-decode';
import { putSuccessMsg, putWarnMsg } from '../../store/common/actions';


const AdListContainer = () => {
    const dispatch = useDispatch();
    const searchData = useSelector(searchDataSelector);
    const ads = useSelector(adsSelector);
    const isFetchAds = ads.isFetch;
    const [nextPage, setNextPage] = useState(ads.nextPage);
    const [size, setSize] = useState(ads.size);
    const [namePhoto, setNamePhoto] = useState();
    const token = localStorage.getItem('token');

    useEffect(() => {
        dispatch(
            fetchAds({
                nextPage,
                size
            })
        );

    }, [nextPage, size]);

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

    const handleCoverPh = (event) => {
        setNamePhoto(event)
    }

    return (

        <Container>
            <Row>
                <Col>
                    <OrdinarySearchContainer></OrdinarySearchContainer>
                </Col>
            </Row>
            <Row>
                <Col md={12} xs={12}>
                    <PaginationSize size={size} setSize={setSize} />
                </Col>
            </Row>
            <Row>
                <Col >

                    {
                        isFetchAds ? <AdComponent ads={ads.data} token={token} addToCart={addToCart} hasRole={hasRole} handleCoverPh={handleCoverPh} /> : <SpinnerContainer />
                    }
                </Col>
            </Row>
            <Row>
                <Col md={12} xs={12}>
                    <PaginationContainer setNextPage={setNextPage} totalPageCnt={ads.totalPageCnt} nextPage={nextPage}></PaginationContainer>
                </Col>
            </Row>
        </Container>


    );
}

export default AdListContainer;