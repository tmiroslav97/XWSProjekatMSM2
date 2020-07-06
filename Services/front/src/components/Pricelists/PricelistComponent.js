import React from 'react';
import { Row, Col, Button, Table } from 'react-bootstrap'

const PricelistComponent = (props) => {

    return (
        <Row>
            <Col md={12} xs={12}>
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th >Datum kreiranja</th>
                            <th >Cena po danu</th>
                            <th >Cena po km</th>
                            <th >Cena po km (CDW)</th>
                            <th>Izmeni</th>
                            <th>Izbrisi</th>
                        </tr>
                    </thead>
                    <tbody>
                        {props.getPricelists()}
                    </tbody>
                </Table>
            </Col>
        </Row>
    );
}

export default PricelistComponent;
