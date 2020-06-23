import React from 'react';
import { Row, Col, Card, Button } from 'react-bootstrap';



const MyAdComponent = (props) => {

    var disable = false;
    if (props.token === null) {
        disable = true;
    }

    return (

        props.ads.map((variant, idx) => (

            <Card
                key={variant.id}
                border="secondary"
                className="mb-5"
            >
                <Card.Body>
                    <Card.Title>{variant.name}</Card.Title>
                    <Card.Subtitle className="mb-2 text-muted">{variant.carManufacturer} {variant.carModel}  </Card.Subtitle>
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
                                    {variant.childrenSeatNum}
                                </Card.Text>
                            </Row>
                            <Row>
                                <Col sm={2}>
                                    <Card.Img src="/img-icon/location.png" />
                                </Col>
                                <Card.Text>
                                    {variant.location}
                                </Card.Text>
                            </Row>
                            <Row>
                                <Col sm={2}>
                                    <Card.Img src="/img-icon/price-tag.png" />
                                </Col>
                                <Card.Text>
                                    {variant.price} $
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
                                    {variant.fuelType}
                                </Card.Text>
                            </Row>
                            <Row>
                                <Col sm={2}>
                                    <Card.Img src="/img-icon/km.png" />
                                </Col>
                                <Card.Text>
                                    {variant.mileage}
                                </Card.Text>
                            </Row>

                            {/* <Card.Link href={'/agent-firm/ad-detail-view/' + variant.id} >Vise detalja</Card.Link> */}

                        </Col>
                        
                        <Col>
                            {
                                disable ? null : <Button className="mb-2" variant="outline-success" 
                                onClick={() => { props.viewAd(variant.id);}}
                                >Pregled oglasa</Button>
                            }
                            {
                                disable ? null : 
                                <Button variant="outline-success"
                                    onClick={() => { props.definingAvailability(variant.id); }}
                                >Definisi dostupnost</Button>
                            }
                        </Col>

                    </Row>
                </Card.Body>
            </Card>
        ))

    );
}

export default MyAdComponent;