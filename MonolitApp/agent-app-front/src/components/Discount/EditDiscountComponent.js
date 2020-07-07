import React, { useState } from 'react';
import { Form, Button, Container, Row, Col, InputGroup } from 'react-bootstrap'

const EditDiscountComponent = (props) => {

    return (
        <Container>
            <Row>
                <Col md={12} xs={12}>
                    <Form noValidate validated={props.validated} onSubmit={props.onSubmit}>
                        <Form.Row>
                            <Col>
                                <Form.Group as={Col}>
                                    <Form.Label>*Popust</Form.Label>
                                    <InputGroup className="mb-3">
                                        <Form.Control name="discount" required
                                            type="number" placeholder="Popust"
                                            defaultValue={props.data.discount}
                                        />
                                        <InputGroup.Append>
                                            <InputGroup.Text>%</InputGroup.Text>
                                        </InputGroup.Append>
                                    </InputGroup>
                                </Form.Group>
                                <Form.Group as={Col}>
                                    <Form.Label>Broj potrebnih dana rentiranja</Form.Label>
                                    <InputGroup className="mb-3">
                                        <Form.Control name="dayNum" required
                                            type="number" placeholder="Broj potrebnih dana"
                                            defaultValue={props.data.dayNum}
                                        />
                                        <InputGroup.Append>
                                            <InputGroup.Text>dana</InputGroup.Text>
                                        </InputGroup.Append>
                                    </InputGroup>
                                </Form.Group>


                            </Col>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Button variant="primary" type="submit" className="float-right">
                                    Izmeni
                                </Button>
                            </Form.Group>
                        </Form.Row>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
}

export default EditDiscountComponent;
