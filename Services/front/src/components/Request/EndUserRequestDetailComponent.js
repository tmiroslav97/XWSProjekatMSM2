import React from 'react';
import { history } from '../../index';
import { Row, Col, OverlayTrigger, Tooltip, ListGroup, Container, Button, Card } from 'react-bootstrap'
import FormModalContainer from '../../containers/Common/FormModalContainer';
import CommentComponent from '../Comment/CommentComponent';
import ReactStars from "react-rating-stars-component";
import ReportComponent from '../Report/ReportComponent';
import MessageComponent from '../Message/MessageComponent';

const EndUserRequestDetailComponent = (props) => {


    return (
        <div>
            <FormModalContainer show={props.flagComment} setShow={props.setFlagComment} name={'Komentar'} footer={false} data={props.adId} onSubmit={props.handleCommentForm} validated={props.validated} component={CommentComponent} />
            <FormModalContainer show={props.flagMessage} setShow={props.setFlagMessage} name={'Poruka'} footer={false} onSubmit={props.handleMessageForm} validated={props.validatedMessage} component={MessageComponent} />
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
                                <ListGroup.Item>
                                    Vlasnik: {props.request.publisherUserFirstName + ' ' + props.request.publisherUserLastName}
                                </ListGroup.Item>
                                <ListGroup.Item>
                                    Email vlasnika: {props.request.publisherUserEmail}
                                </ListGroup.Item>
                            </ListGroup>
                        </Col>
                    </Row>
                    <Row>
                        <Col md={2} xs={12}>
                            <ListGroup variant="flush">
                                <Button variant="outline-primary" className="mt-2" onClick={() => { props.sendMessage() }}>Posalji poruku</Button>
                            </ListGroup>
                        </Col>
                    </Row>
                    <Row>
                        <Col md={7} xs={12}>
                            <h4 className="mt-2">Oglasi</h4>
                        </Col>
                    </Row>
                    {
                        props.request.ads.map((ad, idx) => {
                            return (
                                <Container key={idx}>
                                    <Row >
                                        <Col md={4} xs={12}>
                                            <ListGroup variant="flush">
                                                <OverlayTrigger overlay={<Tooltip id="tooltip-disabled">Klikni za detaljno</Tooltip>}>
                                                    <span className="d-inline-block">
                                                        <ListGroup.Item action onClick={() => { history.push('/ad-detail-view/' + ad.mainId); }}>{ad.adName}</ListGroup.Item>
                                                    </span>
                                                </OverlayTrigger>
                                            </ListGroup>
                                        </Col>
                                        <Col md={3} xs={12}>
                                            <ListGroup variant="flush">
                                                <OverlayTrigger key={idx} overlay={<Tooltip id="tooltip-disabled">Ostavi komentar</Tooltip>}>
                                                    <span className="d-inline-block">
                                                        <Button onClick={() => { props.addComment(ad.mainId) }}>Ostavi komentar</Button>
                                                    </span>
                                                </OverlayTrigger>
                                            </ListGroup>
                                        </Col>
                                        <Col md={3} xs={12}>
                                            <ListGroup variant="flush">{
                                                ad.review == null ?
                                                    <OverlayTrigger key={idx} overlay={<Tooltip id="tooltip-disabled">Oceni</Tooltip>}>
                                                        <span className="d-inline-block">
                                                            <ReactStars
                                                                count={5}
                                                                onChange={(newRating) => { props.handleRatingForm(props.request.id, ad.id, ad.mainId, newRating) }}
                                                                size={26}
                                                                half={false}
                                                                color2={"#ffd700"}
                                                            />
                                                        </span>
                                                    </OverlayTrigger>
                                                    : 'Ocjenjeno sa: ' + ad.review
                                            }
                                            </ListGroup>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col md={4} xs={12}>
                                            {
                                                ad.report != null ?
                                                    <ReportComponent report={ad.report} /> : null
                                            }
                                        </Col>
                                    </Row>
                                </Container>
                            );
                        })
                    }

                </Card.Body>
            </Card>
        </div >
    );
}

export default EndUserRequestDetailComponent;
