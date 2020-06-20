import React from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap'

const CodebookAdFormComponent = (props) => {

    return (
        <Container>
            <Row>
                <Col md={12} xs={12}>
                    <Form noValidate validated={props.validated} id="addCBForm" onSubmit={props.onSubmit}>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Naziv</Form.Label>
                                <Form.Control required type="text" name="name" id="txtName" placeholder="Naziv" />
                            </Form.Group>
                        </Form.Row>
                        {props.data &&
                            <Form.Row>
                                <Form.Group as={Col}>
                                    <Form.Label>Naziv proizvođača</Form.Label>
                                    <Form.Control as="select" name="selCarMan" id="selCarManID" required>
                                        {
                                            props.data.map((item) => {
                                                return (
                                                    <option key={item.id} value={item.id}>{item.name}</option>
                                                );
                                            })
                                        }
                                    </Form.Control>
                                </Form.Group>
                            </Form.Row>
                        }
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Button variant="primary" id="btnAdd" type="submit">
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

export default CodebookAdFormComponent;
