import React from 'react';
import { Row, Col, Card, Button } from 'react-bootstrap';
import { LocalGasStation, AirlineSeatReclineNormal, MyLocation, LocalOffer, ConfirmationNumber } from '@material-ui/icons';

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
                            <Card.Img src={`data:image/jpeg;base64,${variant.coverPhoto}`} />
                        </Col>
                        <Col >
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
                                        {variant.price} RSD
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
                                        {variant.mileage} Km
                                    </Card.Text>
                                </Col>
                            </Row>
                            {/* <Card.Link href={'/agent-firm/ad-detail-view/' + variant.id} >Vise detalja</Card.Link> */}
                        </Col>

                        <Col>
                            {
                                disable ? null : <Button className="mb-2" variant="outline-success" onClick={() => { props.viewAd(variant.id); }}>Pregled oglasa</Button>
                            }
                            {
                                disable ? null :
                                    <Button className="mb-2" variant="outline-success" onClick={() => { props.definingAvailability(variant.id); }}>Definisi dostupnost</Button>
                            }
                            {
                                disable ? null :
                                    <Button  className="mb-2" variant="outline-success" onClick={() => { props.editPricelist(variant.id); }}>Izmeni cenovnik</Button>
                            }
                            {
                                disable ? null :
                                    <Button variant="outline-success" onClick={() => { props.definedDiscountList(variant.id); }}>Dodaj popust</Button>
                            }
                        </Col>

                    </Row>
                </Card.Body>
            </Card>
        ))

    );
}

export default MyAdComponent;