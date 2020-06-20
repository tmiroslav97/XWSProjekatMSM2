import React from 'react';
import { Container, Row, Col, Button, Card, Carousel } from 'react-bootstrap'
import ListGroup from 'react-bootstrap/ListGroup'

const AdDetailViewComponent = (props) => {

    var androidFlag = props.ad.androidFlag;

    if (androidFlag === false) {
        androidFlag = "Ne"
    } else {
        androidFlag = "Da"
    }

    

    return (


        <Card
            border="secondary"
            className="mt-5"
        >
            <Card.Body>
                <Card.Title as="h4">{props.ad.name}</Card.Title>
                <Row>
                    <Col md={5}>
                        <Card.Img src="/img-ad/fiat.jpg" />

                        {/* <Carousel>
                                    <Carousel.Item>
                                        <img
                                            className="d-block w-100"
                                            src="img/rent1.png"
                                            alt="First slide"
                                        />
                                        <Carousel.Caption>
                                            <h3>Dobro dosli na rent a car!</h3>
                                        </Carousel.Caption>
                                    </Carousel.Item>
                                </Carousel> */}
                        <>
                            <br />

                        </>
                        <ListGroup variant="flush">
                            <ListGroup.Item>Datum objavljivanja: {props.handleDateFormat(props.ad.publishedDate)}</ListGroup.Item>
                            <ListGroup.Item>Oglas objavio: {props.ad.publisherUserFirstName} {props.ad.publisherUserLastName} </ListGroup.Item>
                            <ListGroup.Item></ListGroup.Item>
                        </ListGroup>
                    </Col>
                    <Col >
                        <ListGroup variant="flush">
                            <ListGroup.Item>Proizvodjac: {props.ad.carManufacturer}</ListGroup.Item>
                            <ListGroup.Item>Model: {props.ad.carModel}</ListGroup.Item>
                            <ListGroup.Item>Tip: {props.ad.carType}</ListGroup.Item>
                            <ListGroup.Item>Mjenjac: {props.ad.gearboxType}</ListGroup.Item>
                            <ListGroup.Item>Broj sjedista za djecu:  {props.ad.childrenSeatNum}</ListGroup.Item>
                            <ListGroup.Item>Godiste: {props.handleYear(props.ad.year)}</ListGroup.Item>
                            <ListGroup.Item>Posjedovanje android uredjaja: {androidFlag}</ListGroup.Item>
                            <ListGroup.Item>Broj rentiranja: {props.ad.rentCnt}</ListGroup.Item>
                            <ListGroup.Item>Ocjena: neki broj </ListGroup.Item>
                            <ListGroup.Item></ListGroup.Item>

                        </ListGroup>
                    </Col>
                </Row>

                <Row>
                    <Col>
                        {
                            props.hasRole(['ROLE_USER']) ? <Button variant="outline-success" onClick={() => { props.addToCart(props.ad); }} >Dodaj u korpu</Button> : null
                        }
                    </Col>
                </Row>
            </Card.Body>
        </Card>

    );
}

export default AdDetailViewComponent;