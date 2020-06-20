import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap'
import EndUserRequestsComponent from '../../components/Request/EndUserRequestsComponent';
import EndUserRequestsPaidComponent from '../../components/Request/EndUserRequestsPaidComponent';
import SpinnerContainer from '../Common/SpinnerContainer';
import RequestService from '../../services/RequestService';

const EndUserRequestsContainer = () => {
    const [pendingRequests, setPendingRequests] = useState([]);
    const [isFetchPendingRequests, setIsFetchPendingRequests] = useState(false);
    const [paidRequests, setPaidRequests] = useState([]);
    const [isFetchPaidRequests, setIsFetchPaidRequests] = useState(false);
    const [canceledRequests, setCanceledRequests] = useState([]);
    const [isFetchCanceledRequests, setIsFetchCanceledRequests] = useState(false);

    const fetchPendingRequests = async () => {
        setIsFetchPendingRequests(false);
        const result = await RequestService.fetchAllByEndUserIdAndStatus({ "status": "PENDING" });
        setPendingRequests(result);
        setIsFetchPendingRequests(true);
    }

    const fetchPaidRequests = async () => {
        setIsFetchPaidRequests(false);
        const result = await RequestService.fetchAllByEndUserIdAndStatus({ "status": "PAID" });
        setPaidRequests(result);
        setIsFetchPaidRequests(true);
    }

    const fetchCanceledRequests = async () => {
        setIsFetchCanceledRequests(false);
        const result = await RequestService.fetchAllByEndUserIdAndStatus({ "status": "CANCELED" });
        setCanceledRequests(result);
        setIsFetchCanceledRequests(true);
    }

    const [flagComment, setFlagComment] = useState(false);
    const [adId, setAdId] = useState(false);
    const [validated, setValidated]= useState(false);
    
    const addComment = (event) =>{
        setFlagComment(true);
        console.log(event)
        setAdId(event);
    }

    
    const handleCommentForm =(event)=>{
        console.log("komentar");
    }

    const handleRatingForm =(adId, newRating)=>{
        console.log("ocena");
        console.log(adId);
        console.log(newRating);
    }

    useEffect(() => {
        fetchPendingRequests();
        fetchPaidRequests();
        fetchCanceledRequests();
    }, []);

    return (
        <Container>
            
            <Row>
                <Col md={12} xs={12}>
                    {
                        isFetchPendingRequests ?
                            <EndUserRequestsComponent requests={pendingRequests} status="pending" /> : <SpinnerContainer />
                    }
                </Col>
            </Row>
            <Row>
                <Col md={12} xs={12}>
                    {
                        isFetchPaidRequests ?
                            <EndUserRequestsPaidComponent requests={paidRequests} status="paid"
                            flagComment={flagComment} setFlagComment={setFlagComment}
                            addComment={addComment}
                            adId={adId} setAdId={setAdId}
                            validated={validated}
                            handleCommentForm={handleCommentForm}
                            handleRatingForm={handleRatingForm}
                            /> : <SpinnerContainer />
                    }
                </Col>
            </Row>
            <Row>
                <Col md={12} xs={12}>
                    {
                        isFetchCanceledRequests ?
                            <EndUserRequestsComponent requests={canceledRequests} status="canceled" /> : <SpinnerContainer />
                    }
                </Col>
            </Row>
        </Container >
    );
}

export default EndUserRequestsContainer;
