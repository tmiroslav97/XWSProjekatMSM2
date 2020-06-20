import React from 'react';
import { Form, Col, Container, Button, Table } from 'react-bootstrap';
import { Typography } from '@material-ui/core';

const Form4CreateAd = (props) => {
    return (
        <Container>
            <Form id="step4" onSubmit={props.addTerm} noValidate validated={props.validated}>
                <Form.Row>
                    <Col>
                        <Form.Label>Unesi zauzece:</Form.Label>
                    </Col>
                    <Col>
                        <Form.Control required name="startDate" id="startDate" type="datetime-local"
                            min={props.getCurrentDate()}
                            onChange={props.handleStartDate}
                            // defaultValue={props.getCurrentDate()}
                            placeholder="Datum pocetka"
                        />
                    </Col>
                    <Col>
                        <Form.Control required name="endDate" id="endDate" type="datetime-local"
                            min={props.startDate}
                            onChange={props.handleEndDate}
                            // defaultValue={props.getCurrentDate()}
                            placeholder="Datum kraja"
                        />
                    </Col>
                    <Col>
                        <Button type="submit" >Dodaj</Button>
                    </Col>
                </Form.Row>
                {props.flag === 0 ?
                    <label style={{color:"red"}}>Unesite bar jedan termin</label>
                    :null
                }
                    
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
                                {props.getCarCalentarTermList()}

                            </tbody>
                        </Table>

                    </Col>
                </Form.Row>
                <Form.Row>
                    <Col>
                        <Form.Group as={Col} >
                            {props.activeStep === props.steps.length ? (
                                <div>
                                    <Typography
                                    // className={classes.instructions}
                                    >
                                        Svi koraci su zavrseni. Uspesno ste dodadali oglas!
                                    </Typography>
                                    <Typography>
                                        Potvrdite dodavanje
                                    </Typography>


                                    <Button 
                                        // onClick={props.handleReset}
                                        onClick={props.handleCreatedAd}
                                    //  className={classes.button} 
                                    >
                                        Dodaj
                                    </Button>
                                </div>
                            ) : (
                                    <div>
                                        <div>
                                            <Button disabled={props.activeStep === 0}
                                                onClick={props.handleBack} className="float-left">
                                                Nazad
                                            </Button>

                                            {props.isStepOptional(props.activeStep) && (
                                                <Button
                                                    variant="contained"
                                                    onClick={props.handleSkip}
                                                    className="float-right"
                                                >
                                                    Preskoci
                                                </Button>
                                            )}
                                            <Button
                                                onClick={props.handlerForm4}
                                                className="float-right"
                                            >
                                                Dalje
                                            </Button>
                                        </div>
                                    </div>
                                )}
                        </Form.Group>
                    </Col>
                </Form.Row>


            </Form>
        </Container>
    );
}
export default Form4CreateAd;
