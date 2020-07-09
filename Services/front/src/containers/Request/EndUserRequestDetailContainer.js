import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap'
import { useDispatch } from 'react-redux';
import SpinnerContainer from '../Common/SpinnerContainer';
import RequestService from '../../services/RequestService';
import EndUserRequestDetailComponent from '../../components/Request/EndUserRequestDetailComponent';
import { ratingAd, addCommentForAd } from '../../store/ad/actions';


const EndUserRequestDetailContainer = (props) => {
    const id = props.match.params.id;
    const dispatch = useDispatch();
    const [isFetch, setIsFetch] = useState(false);
    const [request, setRequest] = useState();
    const [flagComment, setFlagComment] = useState(false);
    const [adId, setAdId] = useState(false);
    const [validated, setValidated] = useState(false);

    const fetchRequest = async () => {
        setIsFetch(false);
        const result = await RequestService.fetchRequest({ "id": id });
        setRequest(result);
        setIsFetch(true);

    }

    const submitReport = async (payload) => {
        const result = await RequestService.submitReport(payload);
        fetchRequest();
    }

    useEffect(() => {
        fetchRequest();
    }, [id]);



    const addComment = (event) => {
        setFlagComment(true);
        console.log(event)
        setAdId(event);
    }


    const handleCommentForm = (event) => {
        event.preventDefault();
        const form = event.target;
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

    const handleRatingForm = (requestId, adId, mainId, newRating) => {
        dispatch(ratingAd({
            'requestId': requestId,
            'mainId': mainId,
            'rating': newRating,
            'adId': adId
        }));

    }


    return (
        <Container>
            <Row>
                <Col md={12} xs={12}>
                    {
                        isFetch ? <EndUserRequestDetailComponent request={request}
                            flagComment={flagComment} setFlagComment={setFlagComment}
                            addComment={addComment}
                            adId={adId} setAdId={setAdId}
                            validated={validated}
                            handleCommentForm={handleCommentForm}
                            handleRatingForm={handleRatingForm} /> : <SpinnerContainer />
                    }
                </Col>
            </Row>
        </Container >
    );
}

export default EndUserRequestDetailContainer;
