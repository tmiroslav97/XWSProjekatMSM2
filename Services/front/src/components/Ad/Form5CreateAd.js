import React from 'react';
import { Form, Row, Col, Container, Button } from 'react-bootstrap';
import { Typography } from '@material-ui/core';


const Form5CreateAd = (props) => {
    return (
        <Container>
            <Form id="step5" onSubmit={props.onSubmit} noValidate validated={props.validated}>

                <Form.Row>
                    {
                        (props.brPhotos < 4) ?
                            <Col>
                                <Form.Group as={Col}>
                                    <Form.Label>Dodaj slike</Form.Label>
                                    <Form.File name="coverPhoto" id="fileCoverPhoto" placeholder="Slike"
                                        label="Dodaj sliku"
                                        onChange={props.handleImageChange}
                                        custom
                                    >
                                    </Form.File>
                                </Form.Group>
                            </Col>
                            : null
                    }
                </Form.Row>
                {props.flag1 ?
                    <label style={{color:"red"}}>Morate uneti 4 slike</label>
                    :null
                }
                {props.flag2 ?
                    <label style={{color:"red"}}>Morate oznaciti naslovnu fotografiju</label>
                    :null
                }
                <Form.Row>
                    {props.previewImage()}
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
                                            <Button disabled={props.activeStep === 0} onClick={props.handleBack} className="float-left" >
                                                Nazad
                                            </Button>

                                            {props.isStepOptional(props.activeStep) && (
                                                <Button
                                                    variant="contained"
                                                    // color="primary"
                                                    onClick={props.handleSkip}
                                                    // className={classes.button}
                                                    className="float-right"
                                                >
                                                    Preskoci
                                                </Button>
                                            )}

                                            <Button
                                                // variant="contained"
                                                // color="primary"
                                                // onClick={props.handleNext}
                                                // className={classes.button}
                                                type="submit"
                                                className="float-right"
                                            >
                                                Dodaj
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
export default Form5CreateAd;
