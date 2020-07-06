import React, { useState } from 'react';
import { Form, Button, Container, Row, Col, InputGroup } from 'react-bootstrap'

const AddPricelistComponent = (props) => {

    return (
        <Container>
            <Row>
                <Col md={12} xs={12}>
                    <Form noValidate validated={props.validated} onSubmit={props.onSubmit}>

                        <Form.Row>
                            <Col>
                                <Form.Group as={Col}>
                                    <Form.Label>*Cena po danu</Form.Label>
                                    <InputGroup className="mb-3">
                                        <InputGroup.Prepend>
                                            <InputGroup.Text>RSD</InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <Form.Control name="pricePerDay" required id="numPricePerDay"
                                            type="number" placeholder="Cena po danu"
                                        // defaultValue={props.data.pricePerDay}
                                        />
                                        <InputGroup.Append>
                                            <InputGroup.Text>.00</InputGroup.Text>
                                        </InputGroup.Append>
                                    </InputGroup>
                                </Form.Group>
                                <Form.Group as={Col}>
                                    <Form.Label>Cena po kilometru</Form.Label>
                                    <InputGroup className="mb-3">
                                        <InputGroup.Prepend>
                                            <InputGroup.Text>RSD</InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <Form.Control name="pricePerKm" id="numPricePerKm"
                                            type="number" placeholder="Cena po kilometru"
                                        // defaultValue={props.data.pricePerKm}
                                        />
                                        <InputGroup.Append>
                                            <InputGroup.Text>.00</InputGroup.Text>
                                        </InputGroup.Append>
                                    </InputGroup>
                                </Form.Group>
                                <Form.Group as={Col}>
                                    <Form.Label>Cena po kilometru (CDW)</Form.Label>
                                    <InputGroup className="mb-3">
                                        <InputGroup.Prepend>
                                            <InputGroup.Text>RSD</InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <Form.Control name="pricePerKmCDW" id="numPricePerKmCDW"
                                            type="number" placeholder="Cena po kilometru (CDW)"
                                        // defaultValue={props.data.pricePerKmCDW}
                                        />
                                        <InputGroup.Append>
                                            <InputGroup.Text>.00</InputGroup.Text>
                                        </InputGroup.Append>
                                    </InputGroup>
                                </Form.Group>
                                {/* { props.validated === true ?
                                    <Form.Group as={Col}>
                                        <Form.Label style="color:red">Obavezna polja su oznacena sa *</Form.Label>
                                    </Form.Group>
                                    : null
                                } */}

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

export default AddPricelistComponent;
