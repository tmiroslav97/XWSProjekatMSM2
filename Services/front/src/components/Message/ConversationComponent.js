import React from 'react';
import { Form, Button, Container, Row, Col, ListGroup, Tooltip, OverlayTrigger } from 'react-bootstrap'

const ConversationComponent = (props) => {

    return (
        <div>
            <Row>
                <Col md={12} xs={12}>
                    <ListGroup variant="flush">
                        {
                            props.conversations.map((conv, idx) => {
                                return (
                                    <ListGroup.Item action key={idx} variant="secondary" onClick={() => { props.handleFetchConversationMessages(conv); }}>Razgovor <br />{conv.convName}</ListGroup.Item>
                                );
                            })
                        }
                    </ListGroup>

                </Col>
            </Row>
        </div>
    );
}

export default ConversationComponent;
