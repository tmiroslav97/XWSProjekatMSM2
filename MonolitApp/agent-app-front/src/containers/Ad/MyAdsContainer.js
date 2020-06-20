import React, { useState, useEffect } from 'react';
import MyAdComponent from '../../components/Ad/MyAdComponent';
import AvailabilityContainer from '../Ad/AvailabilityContainer';
import { useDispatch, useSelector } from 'react-redux';
import { Container, Row, Col } from 'react-bootstrap';
import { adsSelector } from '../../store/ad/selectors';
import PaginationContainer from '../Pagination/PaginationContainer';
import PaginationSize from '../../components/Pagination/PaginationSize';
import OrdinarySearchContainer from '../../containers/Search/OrdinarySearchContainer'
import { fetchAdsFromPublisher } from '../../store/ad/actions';
import SpinnerContainer from '../Common/SpinnerContainer';

const MyAdsContainer = () => {
    const dispatch = useDispatch();
    const ads = useSelector(adsSelector);
    const isFetchAds = ads.isFetch;
    const [nextPage, setNextPage] = useState(ads.nextPage);
    const [size, setSize] = useState(ads.size);
    const token = localStorage.getItem('token');

    const [flagAvailability, setFlagAvailability] = useState(false);
    const [adId, setAdId] = useState(null);

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
    const handleBack = () =>{
        setFlagAvailability(false);
    }

    return (
        <Container>
            {flagAvailability ?
                <AvailabilityContainer 
                adId = {adId} setAdId={setAdId}
                flagAvailability={flagAvailability} setFlagAvailability={setFlagAvailability}
                handleBack={handleBack}
                />
                :
                <Container>

                    <Row>
                        <Col md={{ span: 12, offset: 3 }} xs={12}>

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
                                    />
                                    : <SpinnerContainer />
                            }
                        </Col>
                    </Row>
                    <Row>
                        <PaginationContainer setNextPage={setNextPage} totalPageCnt={ads.totalPageCnt} nextPage={nextPage}></PaginationContainer>
                    </Row>
                </Container>
            }

        </Container >

    );
}

export default MyAdsContainer;