import React, { useState, useEffect, Component } from 'react';
import AdComponent from '../../components/Ad/AdComponent';
import { useDispatch, useSelector } from 'react-redux';
import { Container, Row, Col } from 'react-bootstrap';
import { adsSelector, searchDataSelector } from '../../store/ad/selectors';
import PaginationContainer from '../Pagination/PaginationContainer';
import PaginationSize from '../../components/Pagination/PaginationSize';
import OrdinarySearchContainer from '../../containers/Search/OrdinarySearchContainer'
import { fetchAds, searchAd } from '../../store/ad/actions';
import SpinnerContainer from '../Common/SpinnerContainer';
import { loadImage } from '../../store/ad/saga';
import jwt_decode from 'jwt-decode';
import { putSuccessMsg, putWarnMsg } from '../../store/common/actions';
import { putAds } from '../../store/ad/actions';
import Form from 'react-bootstrap/Form'


const AdListContainer = () => {
    const dispatch = useDispatch();
    const searchData = useSelector(searchDataSelector);
    const ads = useSelector(adsSelector);
    const isFetchAds = ads.isFetch;
    const [nextPage, setNextPage] = useState(ads.nextPage);
    const [size, setSize] = useState(ads.size);
    const [namePhoto, setNamePhoto] = useState();
    const token = localStorage.getItem('token');
    const [sort, setSort] = useState(ads.sort);

    useEffect(() => {
        dispatch(
            searchAd({
                'data': {
                    'location': searchData.location,
                    'startDate': searchData.startDate,
                    'endDate': searchData.endDate,
                    'carManufacturer': searchData.carManufacturer,
                    'carModel': searchData.carModel,
                    'carType': searchData.carType,
                    'mileage': searchData.mileage,
                    'mileageKM': searchData.mileageKM,
                    'gearboxType': searchData.gearboxType,
                    'fuelType': searchData.fuelType,
                    'childrenSeatNum': searchData.childrenSeatNum,
                    'cdw': searchData.cdw,
                    'startPrice': searchData.startPrice,
                    'endPrice': searchData.endPrice,
                    'advancedSearch': searchData.advancedSearch,
                    'nextPage': nextPage,
                    'size': size,
                    'sort': sort
                }
            })
        );
    }, [nextPage, size, sort]);

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
        if (searchData.startDate === undefined || searchData.startDate === null || searchData.startDate === '' || searchData.endDate === undefined || searchData.endDate === null || searchData.endDate === '') {
            dispatch(putWarnMsg('Niste odabrali pocetni i krajnji datum rentiranja'));
        } else {
            let cart = new Map();
            if (JSON.parse(localStorage.getItem('cart')) != null) {
                cart = new Map(JSON.parse(localStorage.getItem('cart')));
            }
            if (cart.get(ad.publisherUserId) == null) {
                cart.set(ad.publisherUserId, { user: ad.publisherUserFirstName + " " + ad.publisherUserLastName, bundle: false, ads: [{ id: ad.id, adName: ad.name, startDate: searchData.startDate, endDate: searchData.endDate }] });
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
                    cart.get(ad.publisherUserId).ads.push({ id: ad.id, adName: ad.name, startDate: searchData.startDate, endDate: searchData.endDate });
                    dispatch(putSuccessMsg('Oglas uspjesno dodat u korpu'));
                }
            }

            localStorage.setItem('cart', JSON.stringify(Array.from(cart.entries())));
        }
    };

    const handleCoverPh = (event) => {
        setNamePhoto(event)
    }

    return (

        <Container>
            <Row>
                <Col>
                    <OrdinarySearchContainer sort={sort}></OrdinarySearchContainer>
                </Col>
            </Row>
            <Row>
                <Col md={12} xs={12}>
                    <Container>
                        <Row>
                            
                               <Col md={{ span: 6 }} xs={12}>
                                    <Form.Row>
                                        <Form.Label >Sortiraj rezultate po: </Form.Label>
                                        <Form.Group as={Col} md={7} xs={12}>
                                            <Form.Control as="select" id="sort" value={sort} onChange={({ currentTarget }) => {
                                                setSort(currentTarget.value);
                                            }}>
                                                <option key="1" value="name desc">imenu opadajuce</option>
                                                <option key="2" value="name asc">imenu rastuce</option>
                                                <option key="3" value="pricePerDay desc">cijeni opadajuce</option>
                                                <option key="4" value="pricePerDay asc">cijeni rastuce</option>
                                                <option key="5" value="mileage desc">kilometrazi opadajuce</option>
                                                <option key="6" value="mileage asc">kilometrazi rastuce</option>
                                            </Form.Control> 
                                        </Form.Group>
                                    </Form.Row>

                                </Col>
                                

                        </Row>
                    </Container>
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