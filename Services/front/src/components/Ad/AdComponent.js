import React from 'react';
import { Row, Col, Card, Button } from 'react-bootstrap';
import { history } from '../../index';


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
                            <Card.Img src="/img-ad/fiat.jpg" />
                        </Col>
                        <Col >
                            <br />
                            <Row>
                                <Col sm={2}>
                                    <Card.Img src="/img-icon/seat-num.png" />
                                </Col>
                                <Card.Text>
                                    {ad.childrenSeatNum}
                                </Card.Text>
                            </Row>
                            <Row>
                                <Col sm={2}>
                                    <Card.Img src="/img-icon/location.png" />
                                </Col>
                                <Card.Text>
                                    {ad.location}
                                </Card.Text>
                            </Row>
                            <Row>
                                <Col sm={2}>
                                    <Card.Img src="/img-icon/price-tag.png" />
                                </Col>
                                <Card.Text>
                                    {ad.price} $
                                        </Card.Text>
                            </Row>

                        </Col>
                        <Col>
                            <br />
                            <Row>
                                <Col sm={2}>
                                    <Card.Img src="/img-icon/fuel24.png" />
                                </Col>
                                <Card.Text>
                                    {ad.fuelType}
                                </Card.Text>
                            </Row>
                            <Row>
                                <Col sm={2}>
                                    <Card.Img src="/img-icon/km.png" />
                                </Col>
                                <Card.Text>
                                    {ad.mileage}
                                </Card.Text>
                            </Row>
                            <Button variant="link" onClick={() => { history.push('/ad-detail-view/' + ad.id); }}>Vise detalja</Button>
                        </Col>

                        <Col>
                            {
                                props.hasRole(['ROLE_USER']) ? <Button variant="outline-success" onClick={() => { props.addToCart(ad); }} >Dodaj u korpu</Button> : null
                            }
                        </Col>

                    </Row>
                </Card.Body>
            </Card>
        ))

    );
}

export default AdComponent;