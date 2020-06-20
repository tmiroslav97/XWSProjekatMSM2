import React from 'react';
import { Form, Col, Container, Button, Table } from 'react-bootstrap';
import { Typography } from '@material-ui/core';

const Availability = (props) => {
    return (
        <Container>
            <Form id="availability"
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
                        <Button //type="submit"
                        onClick={props.addTermToCalendar}
                         >Dodaj</Button>
                    </Col>
                </Form.Row>

                {/* {props.flag === 0 ?
                    <label style={{color:"red"}}>Unesite bar jedan termin</label>
                    :null
                } */}

                <br />
                <Form.Row>
                    <Col>
                        <Table striped bordered hover>
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
                </Form.Row>
                <Form.Row>
                    <Col>
                        <Button
                            onClick={props.handleBack}
                            className="float-right"
                        >
                            Vrati se 
                        </Button>
                    </Col>
                </Form.Row >


            </Form >
        </Container >
    );
}
export default Availability;
