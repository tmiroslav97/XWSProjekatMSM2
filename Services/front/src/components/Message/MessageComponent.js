import React from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap'

const MessageComponent = (props) => {

    return (
        <Container>
            <Row>
                <Col md={12} xs={12}>
                    <Form noValidate validated={props.validated} onSubmit={props.onSubmit}>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Naslov poruke</Form.Label>
                                <Form.Control required type="text" name="convName" id="txtConvName" placeholder="Naslov poruke" />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Tekst poruke</Form.Label>
                                <Form.Control required as="textarea" rows="5" name="content" id="txtContent" placeholder="Sadrzaj poruke" />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Button variant="primary"  type="submit">
                                    Posalji
                                </Button>
                            </Form.Group>
                        </Form.Row>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
}

export default MessageComponent;
