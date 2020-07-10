import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap'
import ConversationComponent from '../../components/Message/ConversationComponent';
import MessageService from '../../services/MessageService';
import SpinnerImageContainer from '../Common/SpinnerContainer';
import MessageComponent from '../../components/Message/MessageComponent';
import { putSuccessMsg, putErrorMsg } from '../../store/common/actions';
import { useDispatch, useSelector } from 'react-redux';

const InboxContainer = () => {
    const dispatch = useDispatch();

    const [isFetchConversations, setIsFetchConversations] = useState(false);
    const [conversations, setConversations] = useState([]);
    const [messages, setMessages] = useState([]);
    const [showMessages, setShowMessages] = useState(false);
    const [conv, setConv] = useState(null);
    const [validated, setValidated] = useState(false);

    const fetchConversations = async () => {
        setIsFetchConversations(false);
        const result = await MessageService.fetchConversations();
        setConversations(result);
        setIsFetchConversations(true);
    }

    useEffect(() => {
        fetchConversations();
    }, []);

    const fetchConversationMessages = async (id) => {
        const result = await MessageService.fetchConversationMessages(id);
        setMessages(result);
    }

    const sendMessage = async (payload) => {
        const result = await MessageService.sendMessage(payload);
        if (result === 'Uspjesno poslata poruka') {
            dispatch(putSuccessMsg(result));
            fetchConversationMessages(conv.id);
        } else {
            dispatch(putErrorMsg(result));
        }
    }

    const handleFetchConversationMessages = (conversation) => {
        if (conv != null && conv.id == conversation.id) {
            setShowMessages(false);
            setConv(null);
        } else {
            fetchConversationMessages(conversation.id);
            setConv(conversation);
            setShowMessages(true);
        }
    }

    const onSubmit = (event) => {
        event.preventDefault();
        const form = event.target;
        const data = new FormData(event.target);
        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            const payload = {
                "content": data.get('content'),
                "conversationId": conv.id
            };
            form.reset();
            sendMessage(payload);
            setValidated(false);
        }
    }

    return (
        <Container>
            <Row>
                <Col md={12} xs={12}>
                    <h2 className="border-bottom">Poruke</h2>
                </Col>
            </Row>
            <Row>
                <Col md={4} xs={12}>
                    {isFetchConversations ? <ConversationComponent conversations={conversations} handleFetchConversationMessages={handleFetchConversationMessages} /> : <SpinnerImageContainer />}
                </Col>
                <Col md={8} xs={12}>
                    {showMessages ? <MessageComponent messages={messages} onSubmit={onSubmit} validated={validated} /> : null}
                </Col>
            </Row>
        </Container >
    );
}

export default InboxContainer;
