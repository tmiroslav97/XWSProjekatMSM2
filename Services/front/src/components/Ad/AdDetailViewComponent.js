import React from 'react';
import { Container, Row, Col, Button, Card, Carousel, Table } from 'react-bootstrap'
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
                        <Card.Img src={`data:image/jpeg;base64,${props.ad.coverPhoto}`} />

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
                            {props.ad.cdw ? <ListGroup.Item>Posjeduje CDW: Da</ListGroup.Item> : <ListGroup.Item>Posjeduje CDW: Ne</ListGroup.Item>}
                            {props.ad.pricePerKmCDW !== null && props.ad.cdw ? <ListGroup.Item>Cijena po Km sa CDW: {props.ad.pricePerKmCDW} RSD</ListGroup.Item> : null}
                            {props.ad.distanceLimitFlag==="LIMITED" ? <ListGroup.Item>Postoji ogranicenje u kilometrazi: Da</ListGroup.Item> : <ListGroup.Item>Postoji ogranicenje u kilometrazi: Ne</ListGroup.Item>}
                            {props.ad.distanceLimitFlag==="LIMITED" ? <ListGroup.Item>Ograniceno na: {props.ad.distanceLimit} Km</ListGroup.Item> : null}
                            {props.ad.pricePerKm !== null && props.ad.distanceLimitFlag==="LIMITED" ? <ListGroup.Item>Cijena po Km: {props.ad.pricePerKm} RSD</ListGroup.Item> : null}
                            <ListGroup.Item>Posjeduje android uredjaja: {androidFlag}</ListGroup.Item>
                            <ListGroup.Item>Broj rentiranja: {props.ad.rentCnt}</ListGroup.Item>
                            {props.ad.ratingCnt != 0 ? <ListGroup.Item>Ocjena: {(props.ad.ratingNum / props.ad.ratingCnt).toFixed(2)}</ListGroup.Item> : <ListGroup.Item>Niko nije ocjenio oglas</ListGroup.Item>}
                            {props.ad.pricePerDay !== null ? <ListGroup.Item>Cijena po danu: {props.ad.pricePerDay} RSD</ListGroup.Item> : null}
                        </ListGroup>
                    </Col>
                </Row>

                <Row>
                    <Col>
                        {
                            props.flagComments === false ?
                                <div>
                                    {
                                        props.hasRole(['ROLE_USER']) ?
                                            <Button variant="outline-success" onClick={() => { props.getCommentsFromUser(props.ad.id); }} >Komentari</Button>
                                            : <Button variant="outline-success" onClick={() => { props.getComments(props.ad.id); }} >Komentari</Button>
                                    }
                                </div>
                                :
                                null
                        }
                        {props.flagComments === true ?
                            <Button
                                variant="outline-success"
                                onClick={props.hideComments}
                            >Sakrij komentare</Button>
                            : null
                        }

                    </Col>
                    <Col>
                        {
                            props.hasRole(['ROLE_USER']) ?
                                <Button variant="outline-success" onClick={() => { props.addToCart(props.ad); }} >Dodaj u korpu</Button>
                                : null
                        }
                    </Col>
                </Row>
                <Row>
                    {props.flagComments ?
                        <Col>
                            <Table striped bordered hover >
                                <thead>
                                    <tr>
                                        <th>Datum kreiranja</th>
                                        <th>Ime i prezime</th>
                                        <th>Sadrzaj</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    {props.getCommentsView()}
                                </tbody>
                            </Table>

                        </Col>
                        : null}
                </Row>
            </Card.Body>
        </Card>

    );
}

export default AdDetailViewComponent;