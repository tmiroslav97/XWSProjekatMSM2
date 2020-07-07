import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Container, Row, Col, Button } from 'react-bootstrap'
import EndUserRequestsComponent from '../../components/Request/EndUserRequestsComponent';
import EndUserRequestsPendingComponent from '../../components/Request/EndUserRequestsPendingComponent';
import EndUserRequestsPaidComponent from '../../components/Request/EndUserRequestsPaidComponent';
import SpinnerContainer from '../Common/SpinnerContainer';
import RequestService from '../../services/RequestService';
import { ratingAd, addCommentForAd } from '../../store/ad/actions';
import { putSuccessMsg, putErrorMsg } from '../../store/common/actions';

const EndUserRequestsContainer = () => {
    const dispatch = useDispatch();
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
    const [validated, setValidated] = useState(false);

    const addComment = (event) => {
        setFlagComment(true);
        console.log(event)
        setAdId(event);
    }


    const handleCommentForm = (event) => {
        event.preventDefault();
        const form = event.target;
        console.log("komentar");
        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            setValidated(false);
            setFlagComment(false);
            dispatch(addCommentForAd({
                "adId": adId,
                "content": form.content.value
            }))
            
        }
        

    }

    const handleRatingForm = (adId, newRating) => {
        console.log("ocena");
        console.log(adId);
        console.log(newRating);
        dispatch(ratingAd({
            'rating': newRating,
            'adId': adId
        }));

    }

    const handleQuit = async (reqId) => {
        const result = await RequestService.quitRequest({ "id": reqId, "action": "quit" });
        if (result === "Uspjesno odustajanje od zahtjeva") {
            dispatch(putSuccessMsg(result));
        } else {
            dispatch(putErrorMsg(result));
        }
        fetchPendingRequests();
        fetchPaidRequests();
        fetchCanceledRequests();
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
                            <EndUserRequestsPendingComponent requests={pendingRequests} handleQuit={handleQuit} status="pending" /> : <SpinnerContainer />
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
