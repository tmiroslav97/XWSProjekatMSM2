import React from 'react';
import { Form, Button, Container, Row, Col, ListGroup, Tooltip, OverlayTrigger, Badge } from 'react-bootstrap'

const ConversationComponent = (props) => {
    console.log(props.conversation);
    return (
        <div>
            <Row>
                <Col md={12} xs={12}>
                    <ListGroup variant="flush">
                        {
                            props.conversations.map((conv, idx) => {
                                if (conv.unseenNum == 0) {
                                    return (
                                        <ListGroup.Item action key={idx} onClick={() => { props.handleFetchConversationMessages(conv.conversation); }}>Razgovor: {conv.conversation.convName}</ListGroup.Item>
                                    );
                                } else {
                                    return (
                                        <ListGroup.Item action key={idx} onClick={() => { props.handleFetchConversationMessages(conv.conversation); }}>
                                            <Row>
                                                <Col md={10} xs={10}>
                                                    Razgovor: {conv.conversation.convName}
                                                </Col>
                                                <Col md={2} xs={2}>
                                                    <Badge className="mr-auto" pill variant="dark">{conv.unseenNum}</Badge>
                                                </Col>
                                            </Row>
                                        </ListGroup.Item>
                                    );
                                }

                            })
                        }
                    </ListGroup>

                </Col>
            </Row>
        </div>
    );
}

export default ConversationComponent;
