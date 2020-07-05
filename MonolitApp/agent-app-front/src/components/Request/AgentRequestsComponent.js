import React from 'react';
import { history } from '../../index';
import { Row, Col, OverlayTrigger, Tooltip, ListGroup, Table } from 'react-bootstrap'

const AgentRequestsComponent = (props) => {

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
                                                                        <ListGroup.Item action onClick={() => { history.push('/ad-detail-view/' + ad.mainId); }}>{ad.adName}</ListGroup.Item>
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

export default AgentRequestsComponent;
