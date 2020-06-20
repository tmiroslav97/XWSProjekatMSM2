import React, { useState, useEffect } from 'react';
import AdComponent from '../../components/Ad/AdComponent';
import { useDispatch, useSelector } from 'react-redux';
import { Container, Row, Col } from 'react-bootstrap';
import { adsSelector } from '../../store/ad/selectors';
import PaginationContainer from '../Pagination/PaginationContainer';
import PaginationSize from '../../components/Pagination/PaginationSize';
import OrdinarySearchContainer from '../../containers/Search/OrdinarySearchContainer'
import { fetchAds } from '../../store/ad/actions';
import SpinnerContainer from '../Common/SpinnerContainer';
import { loadImage } from '../../store/ad/saga';



const AdListContainer = () => {
    const dispatch = useDispatch();
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


    const handleCoverPh =  (event) => {
        console.log("ISPISIIIIIII")
        console.log(event);
        setNamePhoto(event)
        console.log(namePhoto)
    }

    return(
       
        <Container>
            <Row>
                <Col>
                    <OrdinarySearchContainer></OrdinarySearchContainer>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 12, offset: 3 }} xs={12}>
    
                    <PaginationSize size={size} setSize={setSize} />
                </Col>
            </Row>
            <Row>
                <Col >

                    {
                        isFetchAds ?  <AdComponent ads={ads.data} token={token} handleCoverPh={handleCoverPh}/> : <SpinnerContainer />
                    }
                </Col>
            </Row>
            <Row>
                <PaginationContainer setNextPage={setNextPage} totalPageCnt={ads.totalPageCnt} nextPage={nextPage}></PaginationContainer>
            </Row>
        </Container>

       
    );
}

export default AdListContainer;