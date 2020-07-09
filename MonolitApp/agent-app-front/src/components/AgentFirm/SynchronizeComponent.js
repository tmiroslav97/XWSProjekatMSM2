import React from 'react';
import { Row, Col, Container, Form, Button } from 'react-bootstrap';

const SynchronizeComponent = (props) => {

    return (
        <Container>
            <Row>
                <Col md={12} xs={12}>
                    <h2 className="border-bottom">Sinhronizacija sa rent-a-car sistemom</h2>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Form noValidate validated={props.validated} id="syncForm" onSubmit={props.onSubmit}>
                        <Form.Row >
                            <Form.Group as={Col} md={5}>
                                <Form.Label>Identifikacioni kod</Form.Label>
                                <Form.Control required type="password" name="identifier" id="txtIdentifier" placeholder="Identifikacioni kod" />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} >
                                <Button variant="primary" id="btnSync" type="submit">
                                    Zapocni sinhronizaciju
                                </Button>
                            </Form.Group>
                        </Form.Row>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
}

export default SynchronizeComponent;