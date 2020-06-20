import React from 'react';
import { history } from '../../index';
import { Row, Col, OverlayTrigger, Tooltip, ListGroup, Table, Button } from 'react-bootstrap'
import FormModalContainer from '../../containers/Common/FormModalContainer';
import CommentComponent from '../CommentAndRating/CommentComponent';
import ReactStars from "react-rating-stars-component";

const EndUserRequestsPaidComponent = (props) => {

    return (
        <div>
            <FormModalContainer show={props.flagComment} setShow={props.setFlagComment} name={'Komentar'} footer={false} data={props.adId} onSubmit={props.handleCommentForm} validated={props.validated} component={CommentComponent} />
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
                                <th>Ostavi komentar</th>
                                <th>Ostavi ocenu</th>
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
                                            <td>
                                                <ListGroup variant="flush">
                                                    {
                                                        req.ads.map((ad, idx) => {
                                                            return (
                                                                <OverlayTrigger key={idx} overlay={<Tooltip id="tooltip-disabled">Ostavi komentar</Tooltip>}>
                                                                    <span className="d-inline-block">
                                                                        <Button onClick={() => { props.addComment(ad.id) }}>Ostavi komentar</Button>
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
                                                                <OverlayTrigger key={idx} overlay={<Tooltip id="tooltip-disabled">Oceni</Tooltip>}>
                                                                    <span className="d-inline-block">
                                                                        <ReactStars
                                                                            count={5}
                                                                            onChange={(newRating) => { props.handleRatingForm(ad.id, newRating) }}
                                                                            size={18}
                                                                            half={false}
                                                                            color2={"#ffd700"}
                                                                        />
                                                                        
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

export default EndUserRequestsPaidComponent;
