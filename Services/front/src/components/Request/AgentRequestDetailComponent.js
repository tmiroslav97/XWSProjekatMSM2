import React from 'react';
import { history } from '../../index';
import { Container, Row, Col, Card, OverlayTrigger, ListGroup, Tooltip, Button } from 'react-bootstrap'
import ReportComponent from '../Report/ReportComponent';

const AgentRequestDetailComponent = (props) => {
    return (
        <Card border="secondary" className="mt-5">
            <Card.Body>
                <Card.Title as="h4">Detalji o zahtjevu</Card.Title>
                <Row>
                    <Col md={7} xs={12}>
                        <ListGroup variant="flush">
                            <ListGroup.Item>
                                Bundle zahtjev: {
                                    props.request.bundle ?
                                        "Da" : "Ne"
                                }
                            </ListGroup.Item>
                            <ListGroup.Item>
                                Datum podnosenja zahtjeva: {props.request.submitDate}
                            </ListGroup.Item>
                        </ListGroup>
                    </Col>
                </Row>
                <Row>
                    <Col md={7} xs={12}>
                        <h4>Oglasi</h4>
                    </Col>
                </Row>
                {
                    props.request.ads.map((ad, idx) => {
                        return (
                            <Container  key={idx}>
                                <Row>
                                    <Col md={5} xs={12}>
                                        <ListGroup variant="flush">
                                            <OverlayTrigger overlay={<Tooltip id="tooltip-disabled">Klikni za detaljno</Tooltip>}>
                                                <span className="d-inline-block">
                                                    <ListGroup.Item action onClick={() => { history.push('/ad-detail-view/' + ad.mainId); }}>{ad.adName}</ListGroup.Item>
                                                </span>
                                            </OverlayTrigger>
                                        </ListGroup>
                                    </Col>
                                    
                                </Row>
                                <Row>
                                    <Col md={5} xs={12}>
                                        {
                                            ad.report == null ?
                                                <Button variant="outline-primary" onClick={()=>{props.setSelectedAd(ad.id); props.setShow(true);}}>Ostavi izvjestaj</Button>
                                                : <ReportComponent report={ad.report} />
                                        }
                                    </Col>
                                </Row>
                            </Container>
                           
                        );
                    })
                }

            </Card.Body>
        </Card>

    );
}

export default AgentRequestDetailComponent;
