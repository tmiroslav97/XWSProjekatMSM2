import React from 'react';
import { Form, Col, Container, Button } from 'react-bootstrap';
import { Typography } from '@material-ui/core';

const Form1CreateAd = (props) => {
    return (
        <Container>
            <Form id="step1" onSubmit={props.onSubmit} noValidate validated={props.validated}>
                <Form.Row>
                    <Col>
                        <Form.Group as={Col}>
                            <Form.Label>Naziv oglasa</Form.Label>
                            <Form.Control required name="name" type="text" id="txtName" placeholder="Naziv oglasa"
                                onChange={props.handleName}
                                defaultValue={props.name}
                            />
                        </Form.Group>

                        <Form.Group as={Col}>
                            <Form.Label>Proizvodjac</Form.Label>
                            <Form.Control as="select" required name="carManufacturer" id="txtCarManufacturer"
                                placeholder="Proizvodjac"
                                value={props.carManufacturer}
                                onChange={props.handleCarManufacturers} >
                                {props.getCarManufacturers()}
                            </Form.Control>
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>Model</Form.Label>
                            <Form.Control as="select" name="carModel" id="txtCarModel" placeholder="Model"
                                defaultValue={props.carModel} onChange={props.handleCarModel}>
                                {props.getCarModels()}
                            </Form.Control>
                        </Form.Group>

                        <Form.Group as={Col}>
                            <Form.Label>Tip</Form.Label>
                            <Form.Control as="select" required name="carType" id="txtCarType" placeholder="Tip"
                                value={props.carType} onChange={props.handleCarType} >
                                {props.getCarTypes()}
                            </Form.Control>
                        </Form.Group>
                    </Col>
                    <Col>
                        <Form.Group as={Col}>
                            <Form.Label>Mesto</Form.Label>
                            <Form.Control required name="location" id="txtLocation" type="text" 
                                placeholder="Lokacija"
                                onChange={props.handleLocation} defaultValue={props.location} />
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>Godina proizvodnje</Form.Label>
                            <Form.Control required name="year" id="dateYear" type="date"
                                max={props.getCurrentDate()}
                                onChange={props.handleYear}
                                defaultValue={props.year}
                                placeholder="Godina proizvodnje" />
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>Predjeni kilometri</Form.Label>
                            <Form.Control required name="mileage" id="numMileage"
                                type="number" min="0" max="10000000" pattern="[0-9]{7}"
                                placeholder="Predjeni kilometri"
                                onChange={props.handleMileage}
                                defaultValue={props.mileage} />
                            <Form.Control.Feedback type="invalid">
                                min 0 max 10000000 km
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group as={Col} >
                            <Form.Check name="distanceLimitFlag" id="chbDistanceLimitFlag" type="checkbox" label="Da li je ogranicena kilometraza?"
                                onChange={props.handleDistanceLimitFlag} checked={props.distanceLimitFlag} />
                        </Form.Group>
                        {props.distanceLimitFlag ?
                            <Form.Group as={Col}>
                                <Form.Label>Unesi kilometrazu</Form.Label>
                                <Form.Control name="distanceLimit" required
                                    onChange={props.handleDistanceLimit}
                                    defaultValue={props.distanceLimit}
                                    id="txtDistanceLimit" type="number" pattern="[0-9]{7}"
                                    placeholder="Kilometraza" min="0" max="10000000" />
                                <Form.Control.Feedback type="invalid">
                                    min 0 max 10000000 km
                                </Form.Control.Feedback>
                            </Form.Group>
                            :
                            null
                        }
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

                                    <Button onClick={props.handleReset}
                                    //  className={classes.button} 
                                    >
                                        Reset
                                    </Button>
                                </div>
                            ) : (
                                    <div>
                                        <div>
                                            {props.isStepOptional(props.activeStep) && (
                                                <Button
                                                    variant="contained"
                                                    // color="primary"
                                                    onClick={props.handleSkip}
                                                    className="float-right"
                                                >
                                                    Preskoci
                                                </Button>
                                            )}

                                            <Button
                                                // variant="contained"
                                                // color="primary"
                                                // onClick={props.handleNext}
                                                type="submit"
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
export default Form1CreateAd;
