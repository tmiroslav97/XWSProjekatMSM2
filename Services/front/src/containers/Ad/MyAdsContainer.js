import React, { useState, useEffect } from 'react';
import MyAdComponent from '../../components/Ad/MyAdComponent';
import AvailabilityContainer from '../Ad/AvailabilityContainer';
import { useDispatch, useSelector } from 'react-redux';
import { Container, Row, Col, Button } from 'react-bootstrap';
import { adsSelector } from '../../store/ad/selectors';
import PaginationContainer from '../Pagination/PaginationContainer';
import PaginationSize from '../../components/Pagination/PaginationSize';
import { fetchAdsFromPublisher } from '../../store/ad/actions';
import SpinnerContainer from '../Common/SpinnerContainer';
import AdViewContainer from '../Ad/AdViewContainer';
import PricelistFromAdContainer from "../Pricelists/PricelistFromAdContainer";
import DiscountsFromAdContainer from "../Discount/DiscountsFromAdContainer";

const MyAdsContainer = () => {
    const dispatch = useDispatch();
    const ads = useSelector(adsSelector);
    const isFetchAds = ads.isFetch;
    const [nextPage, setNextPage] = useState(ads.nextPage);
    const [size, setSize] = useState(ads.size);
    const token = localStorage.getItem('token');

    const [flagAvailability, setFlagAvailability] = useState(false);
    const [adId, setAdId] = useState(null);
    const [flagAdView, setFlagAdView] = useState(false);
    const [flagEditPricelist, setFlagEditPricelist] = useState(false);
    const [flagDiscountlist, setFlagDiscountlist] = useState(false);
    

    useEffect(() => {
        dispatch(
            fetchAdsFromPublisher({
                nextPage,
                size
            })
        );
    }, [nextPage, size]);

    const definingAvailability = (event) => {
        setAdId(event);
        setFlagAvailability(true);

    }
    const handleBack = () => {
        setFlagAvailability(false);
        setFlagAdView(false);
        setFlagEditPricelist(false);
        setFlagDiscountlist(false);
    }
    const viewAd = (event) => {
        setAdId(event);
        setFlagAdView(true);
    }
    const editPricelist = (event) => {
        setAdId(event);

        setFlagEditPricelist(true);
    }

    const definedDiscountList = (event) => {
        setAdId(event);
        setFlagDiscountlist(true);
    }    

    return (
        <Container>
            { flagDiscountlist ?
                <DiscountsFromAdContainer
                adId={adId} setAdId={setAdId}
                flagDiscountlist={flagDiscountlist} setFlagDiscountlist={setFlagDiscountlist}
                handleBack={handleBack}
                />
               : null 
            }
             {flagEditPricelist ?
                <PricelistFromAdContainer
                    adId={adId} setAdId={setAdId}
                    flagEditPricelist={flagEditPricelist} setFlagEditPricelist={setFlagEditPricelist}
                    handleBack={handleBack}
                />
                :
                null
            }
            {flagAvailability ?
                <AvailabilityContainer
                    adId={adId} setAdId={setAdId}
                    flagAvailability={flagAvailability} setFlagAvailability={setFlagAvailability}
                    handleBack={handleBack}
                />
                :
                null

            }
            {flagAdView ?
                <AdViewContainer
                    adId={adId} setAdId={setAdId}
                    flagAdView={flagAdView} setFlagAdView={setFlagAdView}
                    handleBack={handleBack}
                />
                :
                null

            }
            {flagAdView === false && flagAvailability === false && flagEditPricelist === false && flagDiscountlist=== false ?
                <Container>
                    <Row>
                        <Col md={12} xs={12}>
                            <PaginationSize size={size} setSize={setSize} />
                        </Col>
                    </Row>
                    <Row>
                        <Col >

                            {
                                isFetchAds ?
                                    <MyAdComponent
                                        ads={ads.data}
                                        token={token}
                                        definingAvailability={definingAvailability}
                                        viewAd={viewAd}
                                        editPricelist={editPricelist}
                                        definedDiscountList={definedDiscountList}
                                    />
                                    : <SpinnerContainer />
                            }
                        </Col>
                    </Row>
                    <Row>
                        <PaginationContainer setNextPage={setNextPage} totalPageCnt={ads.totalPageCnt} nextPage={nextPage}></PaginationContainer>
                    </Row>
                </Container>
                : null
            }

        </Container >

    );
}

export default MyAdsContainer;