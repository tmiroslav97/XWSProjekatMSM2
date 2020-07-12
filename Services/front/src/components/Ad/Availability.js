import React from 'react';
import { Form, Col, Container, Button, Table, Row } from 'react-bootstrap';
import { Typography } from '@material-ui/core';

const Availability = (props) => {
    return (
        <Container>
            <Form id="availability" className="mt-3"
                // onSubmit={props.addTerm} 
                noValidate validated={props.validated}
            >
                <Form.Row>
                    <Col>
                        <Form.Label>Unesi slobodan termin:</Form.Label>
                    </Col>
                    <Col>
                        <Form.Control required name="startDate" id="startDate" type="datetime-local"
                            min={props.getCurrentDate()}
                            onChange={props.handleStartDate}
                            placeholder="Datum pocetka"
                        />
                    </Col>
                    <Col>
                        <Form.Control required name="endDate" id="endDate" type="datetime-local"
                            min={props.startDate}
                            onChange={props.handleEndDate}
                            placeholder="Datum kraja"
                        />
                    </Col>
                    <Col>
                        <Button
                            onClick={props.addTermToCalendar}
                        >Dodaj</Button>
                    </Col>
                </Form.Row>
            </Form>
            <Form id="availability" className="mt-3"
                // onSubmit={props.addTerm} 
                noValidate validated={props.validated}
            >
                <Form.Row>
                    <Col>
                        <Form.Label>Unesi zauzece:</Form.Label>
                    </Col>
                    <Col>
                        <Form.Control required name="startDate" id="startDate" type="datetime-local"
                            min={props.getCurrentDate()}
                            onChange={props.handleOccupationStart}
                            placeholder="Datum pocetka"
                        />
                    </Col>
                    <Col>
                        <Form.Control required name="endDate" id="endDate" type="datetime-local"
                            min={props.occupationStart}
                            onChange={props.handleOccupationEnd}
                            placeholder="Datum kraja"
                        />
                    </Col>
                    <Col>
                        <Button //type="submit"
                        onClick={props.addOccupation}
                        >Dodaj</Button>
                    </Col>
                </Form.Row>

            </Form >

            <Row>
                <Col>
                    <Table striped bordered hover className="mt-3">
                        <thead>
                            <tr>
                                <th>Rbr.</th>
                                <th className="text-right">Datum pocetka</th>
                                <th className="text-right">Datum kraja</th>
                            </tr>
                        </thead>

                        <tbody>
                            {props.previewCalendar()}

                        </tbody>
                    </Table>

                </Col>
            </Row>
            <Row>
                <Col>
                    <Button
                        onClick={props.handleBack}
                        className="float-right"
                    >
                        Vrati se
                        </Button>
                </Col>
            </Row >


        </Container >
    );
}
export default Availability;
