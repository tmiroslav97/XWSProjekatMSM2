import React from 'react';
import { Row, Col, Card, Button } from 'react-bootstrap';
import { history } from '../../index';
import { LocalGasStation, AirlineSeatReclineNormal, MyLocation, LocalOffer, ConfirmationNumber } from '@material-ui/icons';

const AdComponent = (props) => {


    return (

        props.ads.map((ad, idx) => (

            <Card
                key={idx}
                border="secondary"
                className="mb-5"
            >
                <Card.Body>
                    <Card.Title>{ad.name}</Card.Title>
                    <Card.Subtitle className="mb-2 text-muted">{ad.carManufacturer} {ad.carModel}  </Card.Subtitle>
                    <Row>
                        <Col md={4}>
                            <Card.Img src={`data:image/jpeg;base64,${ad.coverPhoto}`} />
                        </Col>
                        <Col>
                            <Row className="mb-2">
                                <Col sm={2}>
                                    <AirlineSeatReclineNormal />
                                </Col>
                                <Col>
                                    <Card.Text>
                                        {ad.childrenSeatNum}
                                    </Card.Text>
                                </Col>
                            </Row>
                            <Row className="mb-2">
                                <Col sm={2}>
                                    <MyLocation />
                                </Col>
                                <Col>
                                    <Card.Text>
                                        {ad.location}
                                    </Card.Text>
                                </Col>
                            </Row>
                            <Row className="mb-2">
                                <Col sm={2}>
                                    <LocalOffer />
                                </Col>
                                <Col>
                                    <Card.Text>
                                        {ad.price} $
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
                                        {ad.fuelType}
                                    </Card.Text>
                                </Col>
                            </Row>
                            <Row className="mb-2">
                                <Col sm={2}>
                                    <ConfirmationNumber />
                                </Col>
                                <Col>
                                    <Card.Text>
                                        {ad.mileage}
                                    </Card.Text>
                                </Col>
                            </Row>
                            <Row>
                                <Col >
                                    <Card.Text>
                                        <Button variant="link" onClick={() => { history.push('/ad-detail-view/' + ad.id); }}>Vise detalja</Button>
                                    </Card.Text>
                                </Col>
                            </Row>
                        </Col>
                        <Col>
                            {
                                props.hasRole(['ROLE_USER']) ? <Button variant="outline-success" onClick={() => { props.addToCart(ad); }} >Dodaj u korpu</Button> : null
                            }
                        </Col>

                    </Row>
                </Card.Body>
            </Card >
        ))

    );
}

export default AdComponent;