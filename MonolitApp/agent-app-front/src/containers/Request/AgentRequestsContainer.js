import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap'
import AgentRequestsComponent from '../../components/Request/AgentRequestsComponent';
import SpinnerContainer from '../Common/SpinnerContainer';
import RequestService from '../../services/RequestService';

const AgentRequestsContainer = () => {
    const [pendingRequests, setPendingRequests] = useState([]);
    const [isFetchPendingRequests, setIsFetchPendingRequests] = useState(false);
    const [paidRequests, setPaidRequests] = useState([]);
    const [isFetchPaidRequests, setIsFetchPaidRequests] = useState(false);
    const [canceledRequests, setCanceledRequests] = useState([]);
    const [isFetchCanceledRequests, setIsFetchCanceledRequests] = useState(false);

    const fetchPendingRequests = async () => {
        setIsFetchPendingRequests(false);
        const result = await RequestService.fetchAllByAgentIdAndStatus({ "status": "PENDING" });
        setPendingRequests(result);
        setIsFetchPendingRequests(true);
    }

    const fetchPaidRequests = async () => {
        setIsFetchPaidRequests(false);
        const result = await RequestService.fetchAllByAgentIdAndStatus({ "status": "PAID" });
        setPaidRequests(result);
        setIsFetchPaidRequests(true);
    }

    const fetchCanceledRequests = async () => {
        setIsFetchCanceledRequests(false);
        const result = await RequestService.fetchAllByAgentIdAndStatus({ "status": "CANCELED" });
        setCanceledRequests(result);
        setIsFetchCanceledRequests(true);
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
                            <AgentRequestsComponent requests={pendingRequests} status="pending" /> : <SpinnerContainer />
                    }
                </Col>
            </Row>
            <Row>
                <Col md={12} xs={12}>
                    {
                        isFetchPaidRequests ?
                            <AgentRequestsComponent requests={paidRequests} status="paid" /> : <SpinnerContainer />
                    }
                </Col>
            </Row>
            <Row>
                <Col md={12} xs={12}>
                    {
                        isFetchCanceledRequests ?
                            <AgentRequestsComponent requests={canceledRequests} status="canceled" /> : <SpinnerContainer />
                    }
                </Col>
            </Row>
        </Container >
    );
}

export default AgentRequestsContainer;
