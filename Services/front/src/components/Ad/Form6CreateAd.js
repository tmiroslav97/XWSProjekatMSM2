import React from 'react';
import { Form, Row, Col, Container, Button } from 'react-bootstrap';
import { Typography } from '@material-ui/core';


const Form6CreateAd = (props) => {
    return (
        <Container>
            <Form id="step6" onSubmit={props.handleCreatedAd} noValidate validated={props.validated}>
                <Form.Row>
                    <Col>
                        <Form.Group as={Col} >
                            {props.activeStep === props.steps.length ? (
                                <div>
                                    <Typography>
                                        Potvrdite dodavanje
                                    </Typography>

                                    {/* <Button onClick={props.handleReset} >
                                        Reset
                                    </Button> */}
                                    <Button
                                        // variant="contained"
                                        // color="primary"
                                        onClick={props.handleCreatedAd}
                                        // onClick={props.handleForm6}
                                        // className={classes.button}
                                        // type="submit"
                                        className="float-right"
                                    >
                                        Dodaj
                                    </Button>
                                </div>
                            ) :
                                (
                                    <div>

                                        <Typography>
                                            Pritisnite dodaj ako zelite da dodate oglas.
                                        </Typography>
                                        <div>
                                            <Button disabled={props.activeStep === 0} onClick={props.handleBack} className="float-left" >
                                                Nazad
                                            </Button>

                                            {/* {props.isStepOptional(props.activeStep) && (
                                                <Button
                                                    variant="contained"
                                                    // color="primary"
                                                    onClick={props.handleSkip}
                                                    // className={classes.button}
                                                    className="float-right"
                                                >
                                                    Preskoci
                                                </Button>
                                            )} */}

                                            <Button
                                                // variant="contained"
                                                // color="primary"
                                                onClick={props.handleForm6}
                                                // className={classes.button}
                                                // type="submit"
                                                className="float-right"
                                            >
                                                Dodaj
                                            </Button>
                                        </div>
                                    </div>
                                )
                            }
                        </Form.Group>
                    </Col>
                </Form.Row>


            </Form>
        </Container>
    );
}
export default Form6CreateAd;
