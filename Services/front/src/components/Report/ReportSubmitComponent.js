import React from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap'


const ReportSubmitComponent = (props) => {

    return (
        <Container>
            <Row>
                <Col md={12} xs={12}>
                    <Form noValidate validated={props.validated} id="subreport" onSubmit={props.onSubmit}>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Trenutna kilometraza</Form.Label>
                                <Form.Control required type="number" min={props.data} name="distanceTraveled" id="txtDistance" placeholder="Broj predjenih kilometara" />
                                <Form.Text className="text-muted">
                                    Kilometraza na satu : {props.data} KM
                                </Form.Text>
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Opis</Form.Label>
                                <Form.Control required type="text" name="description" id="txtDesc" placeholder="opis" />
                            </Form.Group>
                        </Form.Row>
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

export default ReportSubmitComponent;
