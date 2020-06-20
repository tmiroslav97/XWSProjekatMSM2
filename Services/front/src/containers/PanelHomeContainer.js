import React, { useState } from 'react';
import { Container, Row, Col, Button, CardDeck, ListGroup, Tab, Nav } from 'react-bootstrap'


const PanelHomeContainer = ({ match }) => {

    return (
        <Container>
            <Row>
                <Col md={12} xs={12}>
                    <center><h2 className="border-bottom">Pocetna stranica panela</h2></center>
                </Col>
            </Row>
        </Container >
    );
}

export default PanelHomeContainer;
