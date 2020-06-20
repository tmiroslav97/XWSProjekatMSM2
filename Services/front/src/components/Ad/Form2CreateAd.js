import React from 'react';
import { Form, Col, Container, Button } from 'react-bootstrap';
import { Typography } from '@material-ui/core';

const Form2CreateAd = (props) => {
    return (
        <Container>
            <Form id="step2" onSubmit={props.onSubmit} noValidate validated={props.validated}>
                <Form.Row>
                    <Col>
                        <Form.Group as={Col}>
                            <Form.Label>Menjac</Form.Label>
                            <Form.Control as="select" required name="gearboxType" id="txtGearboxType" 
                            type="text" placeholder="Menjac" value={props.gearboxType} onChange={props.handleGearboxType}>
                                {props.getGearboxTypes()}
                            </Form.Control>
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>Tip goriva</Form.Label>
                            <Form.Control as="select" required name="fuelType" id="txtFuelType" 
                            type="text" placeholder="Tip goriva" value={props.fuelType} onChange={props.handleFuelType} >
                                {props.getFuelTypes()}
                            </Form.Control>
                        </Form.Group>
                    </Col>
                    <Col>
                        <Form.Group as={Col}>
                            <Form.Label>Broj sedista za decu</Form.Label>
                            <Form.Control required name="childrenSeatNum" id="numChildrenSeatNum" 
                            type="number" min="0" max="9" pattern="[0-9]{1}" placeholder="Broj sedista za decu"
                            defaultValue={props.childrenSeatNum} onChange={props.handleChildrenSeatNum}  />              
                            <Form.Control.Feedback type="invalid">
                                min 0 max 9 sedista
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Check name="cdw" id="chbCDW" type="checkbox" 
                            checked={props.cdw} 
                            label="Da li poseduje CDW?" onChange={props.handleCDW} />
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Check name="androidFlag" id="chbAndroidFlag" type="checkbox" 
                            checked={props.androidFlag}
                            label="Da li poseduje android uredjaj?" onChange={props.handleAndroidFlag} />
                        </Form.Group>

                    </Col>
                </Form.Row>
                
                <Form.Row>
                    <Col>
                        <Form.Group as={Col} >
                            {props.activeStep === props.steps.length ? (
                                <div>
                                    <Typography >
                                        Svi koraci su zavrseni. Uspesno ste dodadali oglas!
                                    </Typography>
                                    <Button onClick={props.handleReset}
                                    
                                     >
                                        Reset
                                    </Button>
                                </div>
                            ) : (
                                <div>
                                        <div>
                                            <Button  onClick={props.handleBack} className="float-left" >
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
export default Form2CreateAd;
