import React from 'react';
import { Row, Col, Container, Carousel } from 'react-bootstrap';
import AdListContainer from './Ad/AdListContainer';

const HomePage = () => {
    return (
        <Container>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <center><h1>Rent A Car - Agent</h1></center>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <AdListContainer />
                </Col>
            </Row>
        </Container>
    );
}

export default HomePage;