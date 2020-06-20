import React from 'react';
import { Form, Button, Row, Col, Container } from 'react-bootstrap';

const RegPage = (props) => {

    return (
        <Container>
            <Row className="justify-content-center">
                <Col md={5} xs={12} className="text-center">
                    <h2 className="border-bottom">Registracija</h2>
                </Col>
            </Row>
            <Row className="justify-content-center">
                <Col md={5} xs={12}>
                    <Form noValidate validated={props.validated} id="regForm" onSubmit={props.onSubmit}>
                        <Form.Row>
                            <Form.Group as={Col} md={8}>
                                <Form.Label>E-mail adresa</Form.Label>
                                <Form.Control required type="email" name="email" id="txtEmail" placeholder="E-mail" />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Lozinka</Form.Label>
                                <Form.Control required type="password" name="password" id="txtPassword" pattern=".{5,25}" placeholder="Lozinka" />
                                <Form.Control.Feedback type="invalid">
                                    min 5 max 25 karaktera
                                </Form.Control.Feedback>
                            </Form.Group>
                            <Form.Group as={Col}>
                                <Form.Label>Potvrdi lozinku</Form.Label>
                                <Form.Control required type="password" name="password2" id="txtPassword2" pattern=".{5,25}" placeholder="Potvrdi lozinku" />
                                <Form.Control.Feedback type="invalid">
                                    min 5 max 25 karaktera
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Ime</Form.Label>
                                <Form.Control required type="text" name="firstName" id="txtFirstName" placeholder="Ime" />
                            </Form.Group>
                            <Form.Group as={Col}>
                                <Form.Label>Prezime</Form.Label>
                                <Form.Control required type="text" name="lastName" id="txtLastName" placeholder="Prezime" />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Button variant="primary" id="btnSignUp" type="submit">
                                    Registruj se
                                </Button>
                            </Form.Group>
                        </Form.Row>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
}

export default RegPage;