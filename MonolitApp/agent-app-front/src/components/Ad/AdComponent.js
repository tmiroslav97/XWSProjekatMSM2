import React from 'react';
import { Row, Col, Card, Button } from 'react-bootstrap';
import { history } from '../../index';
import { LocalGasStation, AirlineSeatReclineNormal, MyLocation, LocalOffer, ConfirmationNumber } from '@material-ui/icons';


const AdComponent = (props) => {

    var disable = false;
    if (props.token === null) {
        disable = true;
    }

    return (

        props.ads.map((variant, idx) => (


            <Card
                key={idx}
                border="secondary"
                className="mt-5 mb-2"
            >
                <Card.Body>
                    <Card.Title>{variant.name}</Card.Title>
                    <Card.Subtitle className="mb-2 text-muted">{variant.carManufacturer} {variant.carModel}  </Card.Subtitle>
                    <Row>
                        <Col md={4}>
                            <Card.Img src={`data:image/jpeg;base64,${variant.coverPhoto}`} />
                        </Col>
                        <Col>
                            <Row className="mb-2">
                                <Col sm={2}>
                                    <AirlineSeatReclineNormal />
                                </Col>
                                <Col>
                                    <Card.Text>
                                        {variant.childrenSeatNum}
                                    </Card.Text>
                                </Col>
                            </Row>
                            <Row className="mb-2">
                                <Col sm={2}>
                                    <MyLocation />
                                </Col>
                                <Col>
                                    <Card.Text>
                                        {variant.location}
                                    </Card.Text>
                                </Col>
                            </Row>
                            <Row className="mb-2">
                                <Col sm={2}>
                                    <LocalOffer />
                                </Col>
                                <Col>
                                    <Card.Text>
                                        {variant.price} $
                                    </Card.Text>
                                </Col>
                            </Row>
                        </Col>
                        <Col>
                            <Row className="mb-2">
                                <Col sm={2}>
                                    <LocalGasStation />
                                </Col>
                                <Col>
                                    <Card.Text>
                                        {variant.fuelType}
                                    </Card.Text>
                                </Col>
                            </Row>
                            <Row className="mb-2">
                                <Col sm={2}>
                                    <ConfirmationNumber />
                                </Col>
                                <Col>
                                    <Card.Text>
                                        {variant.mileage}
                                    </Card.Text>
                                </Col>
                            </Row>
                            <Row>
                                <Col >
                                    <Card.Text>
                                        <Button variant="link" onClick={() => { history.push('/agent-firm/ad-detail-view/' + variant.id); }}>Vise detalja</Button>
                                    </Card.Text>
                                </Col>
                            </Row>
                        </Col>

                        <Col>
                            {
                                disable ? null : <Button variant="outline-success" >Dodaj u korpu</Button>
                            }
                        </Col>

                    </Row>
                </Card.Body>
            </Card>

        ))

    );
}

export default AdComponent;