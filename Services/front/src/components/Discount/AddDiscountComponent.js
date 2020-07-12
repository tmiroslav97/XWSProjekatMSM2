import React, { useState } from 'react';
import { Form, Button, Container, Row, Col, InputGroup } from 'react-bootstrap'

const AddDiscountComponent = (props) => {

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
                                            type="number" placeholder="Popust" max="100"
                                        // defaultValue={props.data.pricePerDay}
                                        />
                                        <InputGroup.Append>
                                            <InputGroup.Text>%</InputGroup.Text>
                                        </InputGroup.Append>
                                    </InputGroup>
                                </Form.Group>
                                <Form.Group as={Col}>
                                    <Form.Label>*Broj potrebnih dana rentiranja</Form.Label>
                                    <InputGroup className="mb-3">
                                        <Form.Control name="dayNum" required
                                            type="number" placeholder="Broj potrebnih dana"
                                        // defaultValue={props.data.pricePerKm}
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

export default AddDiscountComponent;
