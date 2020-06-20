import React from 'react';
import { Row, Col, Container, Card, Button, ListGroup } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const AgentFirmHomePage = () => {

    return (
        <Container >
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <center><h1>AgentFirm Homepage</h1></center>
                </Col>
            </Row>
            <Row style={{ margin: '10px' }}>
                <Col>
                    <Card style={{ width: '300px', margin: '0 auto', alignItem: 'center' }}>
                        <Card.Body style={{ alignItem: 'center', fontSize: '20px' }}>
                            <Link to="/agent-firm/create-ad">Dodaj oglas</Link>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card style={{ width: '300px', margin: '0 auto', alignItem: 'center' }}>
                        <Card.Body style={{ alignItem: 'center', fontSize: '20px' }}>
                            Moji oglasi
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card style={{ width: '300px', margin: '0 auto', alignItem: 'center' }}>
                        <Card.Body style={{ alignItem: 'center', fontSize: '20px' }}>
                            Sanduce
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            <Row style={{ margin: '10px' }}>
                <Col>
                    <Card style={{ width: '300px', margin: '0 auto', alignItem: 'center' }}>
                        <Card.Body style={{ alignItem: 'center', fontSize: '20px' }}>
                            Statistika 
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card style={{ width: '300px', margin: '0 auto', alignItem: 'center' }}>
                        <Card.Body style={{ alignItem: 'center', fontSize: '20px' }}>
                            Profil
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card style={{ width: '300px', margin: '0 auto', alignItem: 'center' }}>
                        <Card.Body style={{ alignItem: 'center', fontSize: '20px' }}>
                            Sanduce
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
}

export default AgentFirmHomePage;