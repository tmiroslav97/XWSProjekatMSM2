import React from 'react';
import { history } from '../../index';
import { Row, Col, OverlayTrigger, Tooltip, ListGroup, Table, Button } from 'react-bootstrap'

const AgentRequestsPendingComponent = (props) => {

    return (
        <div>
            <Row>
                <Col md={12} xs={12}>
                    <h3 className="border-bottom mt-5">Zahtjevi sa statusom {props.status}</h3>
                </Col>
            </Row>
            <Row>
                <Col md={12} xs={12}>
                    <Table responsive>
                        <thead>
                            <tr>
                                <th>Naziv oglasa</th>
                                <th>Datum pocetka rentiranja</th>
                                <th>Datum zavrsetka rentiranja</th>
                                <th>Bundle zahtjev</th>
                                <th>Datum podnosenja zahtjeva</th>
                                <th>Akcija</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                props.requests.map((req, idx) => {
                                    return (
                                        <tr key={idx}>
                                            <td>
                                                <ListGroup variant="flush">
                                                    {
                                                        req.ads.map((ad, idx) => {
                                                            return (
                                                                <OverlayTrigger key={idx} overlay={<Tooltip id="tooltip-disabled">Klikni za detaljno</Tooltip>}>
                                                                    <span className="d-inline-block">
                                                                        <ListGroup.Item action onClick={() => { history.push('/agent-firm/ad-detail-view/' + ad.adId); }}>{ad.adName}</ListGroup.Item>
                                                                    </span>
                                                                </OverlayTrigger>
                                                            );
                                                        })
                                                    }
                                                </ListGroup>
                                            </td>
                                            <td>
                                                <ListGroup variant="flush">
                                                    {
                                                        req.ads.map((ad, idx) => {
                                                            return (
                                                                <ListGroup.Item key={idx}>{ad.startDate}</ListGroup.Item>
                                                            );
                                                        })
                                                    }
                                                </ListGroup>
                                            </td>
                                            <td>
                                                <ListGroup variant="flush">
                                                    {
                                                        req.ads.map((ad, idx) => {
                                                            return (
                                                                <ListGroup.Item key={idx}>{ad.endDate}</ListGroup.Item>
                                                            );
                                                        })
                                                    }
                                                </ListGroup>
                                            </td>
                                            <td>
                                                <ListGroup variant="flush">
                                                    <ListGroup.Item> {
                                                        req.bundle ?
                                                            <p>Da</p> : <p>Ne</p>
                                                    }
                                                    </ListGroup.Item>
                                                </ListGroup>
                                            </td>
                                            <td>
                                                <ListGroup variant="flush">
                                                    <ListGroup.Item>
                                                        {req.submitDate}
                                                    </ListGroup.Item>
                                                </ListGroup>
                                            </td>
                                            <td>
                                                <ListGroup variant="flush">
                                                    <ListGroup.Item>
                                                        <Button variant="outline-primary" onClick={() => { props.handleAccept(req.id, 'accept'); }}>Prihvati</Button>
                                                    </ListGroup.Item>
                                                    <ListGroup.Item>
                                                        <Button variant="outline-danger" onClick={() => { props.handleAccept(req.id, 'reject'); }}>Odbij</Button>
                                                    </ListGroup.Item>
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
        </div >
    );
}

export default AgentRequestsPendingComponent;
