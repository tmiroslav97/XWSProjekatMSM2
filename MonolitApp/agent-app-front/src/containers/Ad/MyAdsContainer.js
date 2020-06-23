import React, { useState, useEffect } from 'react';
import MyAdComponent from '../../components/Ad/MyAdComponent';
import AvailabilityContainer from '../Ad/AvailabilityContainer';
import { useDispatch, useSelector } from 'react-redux';
import { Container, Row, Col, Button } from 'react-bootstrap';
import { adsSelector } from '../../store/ad/selectors';
import PaginationContainer from '../Pagination/PaginationContainer';
import PaginationSize from '../../components/Pagination/PaginationSize';
import OrdinarySearchContainer from '../../containers/Search/OrdinarySearchContainer'
import { fetchAdsFromPublisher } from '../../store/ad/actions';
import SpinnerContainer from '../Common/SpinnerContainer';
import AdViewContainer from '../Ad/AdViewContainer';

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

    useEffect(() => {
        dispatch(
            fetchAdsFromPublisher({
                nextPage,
                size
            })
        );
    }, [nextPage, size]);

    const definingAvailability = (event) => {
        console.log(event);
        setAdId(event);
        console.log("definisanje dostupnosti");
        setFlagAvailability(true);

    }
    const handleBack = () => {
        setFlagAvailability(false);
        setFlagAdView(false);
    }
    const viewAd = (event) => {
        console.log(event);
        setAdId(event);
        console.log("definisanje dostupnosti");
        setFlagAdView(true);
    }

    return (
        <Container>
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
            {flagAdView === false && flagAvailability === false ?
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