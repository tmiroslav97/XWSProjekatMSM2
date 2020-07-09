import React from 'react';
import { Row, Col, Card, Form, Button } from 'react-bootstrap'

const MessageComponent = (props) => {

    return (
        <div>
            <Row>
                <Col md={12} xs={12}>
                    <Form noValidate className="mb-2" validated={props.validated} onSubmit={props.onSubmit}>
                        <Form.Row>
                            <Col md={10} xs={12}>
                                <Form.Control required as="textarea" rows="2" name="content" id="txtContent" placeholder="Sadrzaj poruke" />
                            </Col>
                            <Col md={2} xs={12}>
                                <Button variant="primary" type="submit">
                                    Posalji
                                </Button>
                            </Col>
                        </Form.Row>
                    </Form>
                </Col>
            </Row>
            <Row>
                <Col md={12} xs={12}>
                    {
                        props.messages.map((msg, idx) => {
                            return (
                                <Card key={idx} className="mt-3">
                                    <Card.Header as="h6">{msg.senderFirstName + " " + msg.senderLastName}</Card.Header>
                                    <Card.Body>
                                        <p>
                                            {msg.content}
                                        </p>
                                    </Card.Body>
                                    <Card.Footer>
                                        <small className="text-muted">
                                            Poslato: {msg.sendDate}
                                        </small>
                                    </Card.Footer>
                                </Card>
                            );
                        })
                    }
                </Col>
            </Row>
        </div>
    );
}

export default MessageComponent;
