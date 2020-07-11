import React, { useState, useEffect, Component } from 'react';
import { Row, Col } from 'react-bootstrap'
import { Map, GeoObject } from 'react-yandex-maps';

class MapsComponent extends Component {

    constructor(props) {
        super(props);
        this.token = props.token;
        this.lat= 45.2464362;
        this.long = 19.8517172;
    }


    render() {
        return (
            <Row>
                <Col md={12} xs={12}>
                    <Map defaultState={{ center: [this.lat, this.long], zoom: 7 }}>
                        <GeoObject
                            geometry={{
                                type: 'Point',
                                coordinates: [this.lat, this.long],
                            }}
                            properties={{
                                iconContent: '',
                                hintContent: 'Car',
                            }}
                        />
                    </Map>
                </Col>
            </Row >
        );
    }

}

export default MapsComponent;
