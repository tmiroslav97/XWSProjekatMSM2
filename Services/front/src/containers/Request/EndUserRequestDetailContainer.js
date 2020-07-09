import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap'
import { useDispatch } from 'react-redux';
import SpinnerContainer from '../Common/SpinnerContainer';
import RequestService from '../../services/RequestService';
import MessageService from '../../services/MessageService';
import EndUserRequestDetailComponent from '../../components/Request/EndUserRequestDetailComponent';
import { ratingAd, addCommentForAd } from '../../store/ad/actions';
import { putErrorMsg, putSuccessMsg } from '../../store/common/actions';


const EndUserRequestDetailContainer = (props) => {
    const id = props.match.params.id;
    const dispatch = useDispatch();
    const [isFetch, setIsFetch] = useState(false);
    const [request, setRequest] = useState();
    const [flagComment, setFlagComment] = useState(false);
    const [flagMessage, setFlagMessage] = useState(false);
    const [adId, setAdId] = useState(false);
    const [validated, setValidated] = useState(false);
    const [validatedMessage, setValidatedMessage] = useState(false);

    const fetchRequest = async () => {
        setIsFetch(false);
        const result = await RequestService.fetchRequest({ "id": id });
        setRequest(result);
        setIsFetch(true);

    }

    const sendFirstMessage = async (payload) => {
        const result = await MessageService.sendFirstMessage(payload);
        if(result==='Uspjesno poslata poruka'){
            dispatch(putSuccessMsg(result));
        }else{
            dispatch(putErrorMsg(result));
        }
    }


    useEffect(() => {
        fetchRequest();
    }, [id]);



    const addComment = (event) => {
        setFlagComment(true);
        setAdId(event);
    }

    const sendMessage = (event) => {
        setFlagMessage(true);
    }

    const handleMessageForm = (event) => {
        event.preventDefault();
        const form = event.target;
        const data = new FormData(event.target);
        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidatedMessage(true);
        } else {
            const payload = {
                "convName": data.get('convName'),
                "content": data.get('content'),
                "requestId": request.id,
                "publisherUserId": request.publisherUserId,
                "publisherUserFirstName": request.publisherUserFirstName,
                "publisherUserLastName": request.publisherUserLastName,
                "publisherUserEmail": request.publisherUserEmail,
                "endUserId": request.endUserId,
                "endUserFirstName": request.endUserFirstName,
                "endUserLastName": request.endUserLastName,
                "endUserEmail": request.endUserEmail,
            };
            console.log(payload);
            sendFirstMessage(payload);
            setValidatedMessage(false);
        }
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
                            flagMessage={flagMessage} setFlagMessage={setFlagMessage}
                            sendMessage={sendMessage}
                            addComment={addComment}
                            adId={adId} setAdId={setAdId}
                            validated={validated}
                            validatedMessage={validatedMessage}
                            handleMessageForm={handleMessageForm}
                            handleCommentForm={handleCommentForm}
                            handleRatingForm={handleRatingForm} /> : <SpinnerContainer />
                    }
                </Col>
            </Row>
        </Container >
    );
}

export default EndUserRequestDetailContainer;
