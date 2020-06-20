import React from 'react';
import { history } from '../../index';
import { Row, Col, OverlayTrigger, Tooltip, ListGroup, Table, Container, Form, Button } from 'react-bootstrap'

const CartComponent = (props) => {
    return (
        <Container fluid>
            <Row className="justify-content-center">
                <Col md={6} xs={12}>
                    <h3 className="border-bottom mt-5">Korpa</h3>
                </Col>
            </Row>
            <Row className="justify-content-center">
                <Col md={6} xs={12}>
                    <Table responsive>
                        <thead>
                            <tr>
                                <th>Bundle</th>
                                <th>Naziv oglasa</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                [...props.cart.keys()].map((item, idx) => {
                                    return (
                                        <tr key={idx}>
                                            <td>
                                                <ListGroup variant="flush">
                                                    {
                                                        props.cart.get(item).ads.length > 1 ?
                                                            <ListGroup.Item>
                                                                <Form.Group >
                                                                    <Form.Check type="checkbox" defaultChecked={props.cart.get(item).bundle} onClick={(e) => { props.handleBundle(e, item) }} label="Kreiraj zahtjev u bundle" />
                                                                </Form.Group>
                                                            </ListGroup.Item> : null
                                                    }
                                                </ListGroup>
                                            </td>
                                            <td>
                                                <ListGroup variant="flush">
                                                    {
                                                        props.cart.get(item).ads.map((ad, idx) => {
                                                            return (
                                                                <OverlayTrigger key={idx} overlay={<Tooltip id="tooltip-disabled">Klikni za detaljno</Tooltip>}>
                                                                    <span className="d-inline-block">
                                                                        <ListGroup.Item action onClick={() => { history.push('/ad-detail-view/' + ad.id); }}>{ad.adName}</ListGroup.Item>
                                                                    </span>
                                                                </OverlayTrigger>
                                                            );
                                                        })
                                                    }
                                                </ListGroup>
                                            </td>

                                        </tr>

                                    );
                                })
                            }
                        </tbody>
                    </Table>
                </Col>
            </Row>
            {props.cart.size == 0 ?
                <Row className="justify-content-center mt-2">
                    <Col md={6} xs={12}>
                        <p>Korpa je prazna</p>
                    </Col>
                </Row> :
                <Row className="justify-content-center mt-2">
                    <Col md={6} xs={12}>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Datum pocetka rentiranja {props.startDate} </Form.Label>
                            </Form.Group>
                        </Form.Row>
                        <Form.Row >
                            <Form.Group as={Col}>
                                <Form.Label>Datum zavrsetka rentiranja {props.endDate}</Form.Label>
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} >
                                <Button variant="primary" id="createReq" onClick={props.handleCreateReq}>
                                    Kreiraj zahtjev
                                </Button>
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} >
                                <Button variant="danger" id="delete" onClick={props.handleClearCart}>
                                    Isprazni korpu
                                    </Button>
                            </Form.Group>
                        </Form.Row>
                    </Col>
                </Row>
            }
        </Container >
    );
}

export default CartComponent;
