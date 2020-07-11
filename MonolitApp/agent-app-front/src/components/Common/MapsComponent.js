import React from 'react';
import { Row, Col } from 'react-bootstrap'
import { Map } from 'react-yandex-maps';

const MapsComponent = (props) => {

    return (
        <Row>
            <Col md={12} xs={12}>
                <Map defaultState={{ center: [45.2464362, 19.8517172], zoom: 9 }} />
            </Col>
        </Row>
    );
}

export default MapsComponent;
