import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap'
import EndUserRequestsComponent from '../../components/Request/EndUserRequestsComponent';
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
                            <EndUserRequestsComponent requests={paidRequests} status="paid" /> : <SpinnerContainer />
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
