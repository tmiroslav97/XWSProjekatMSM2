import React from 'react';
import { history } from '../../index';
import { Row, Col, OverlayTrigger, Tooltip, ListGroup, Table } from 'react-bootstrap'

const EndUserRequestsComponent = (props) => {

    return (
        <div>
            <Row>
                <Col md={10} xs={12}>
                    <h3 className="border-bottom mt-5">Zahtjevi sa statusom {props.status}</h3>
                </Col>
            </Row>
            <Row>
                <Col md={10} xs={12}>
                    <Table responsive>
                        <thead>
                            <tr>
                                <th>Datum podnosenja zahtjeva</th>
                                <th>Datum pocetka rentiranja</th>
                                <th>Datum zavrsetka rentiranja</th>
                                <th>Bundle zahtjev</th>
                                <th>Naziv oglasa</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                props.requests.map((req, idx) => {
                                    return (
                                        <tr key={idx}>
                                            <td>{req.submitDate}</td>
                                            <td>{req.startDate}</td>
                                            <td>{req.endDate}</td>
                                            <td> {
                                                req.bundle ?
                                                    <p>Da</p> : <p>Ne</p>
                                            }
                                            </td>

                                            <td>
                                                <ListGroup variant="flush">
                                                    {
                                                        req.ads.map((ad, idx) => {
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
        </div >
    );
}

export default EndUserRequestsComponent;
