import React from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap'

const CommentComponent = (props) => {

    return (
        <Container>
            <Row>
                <Col md={12} xs={12}>
                    <Form noValidate validated={props.validated} onSubmit={props.onSubmit}>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Komentar</Form.Label>
                                <Form.Control required type="text" name="content" id="txtName" placeholder="Sadrzaj" />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Button variant="primary"  type="submit">
                                    Dodaj
                                </Button>
                            </Form.Group>
                        </Form.Row>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
}

export default CommentComponent;
