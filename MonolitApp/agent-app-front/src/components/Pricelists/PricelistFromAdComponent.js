import React from 'react';
import { Row, Col, Button, Table } from 'react-bootstrap';

const PricelistFromAdComponent = (props) => {

    return (
        <Row>
            <Row>
                <Col md={12} xs={12}>
                    <h2 className="border-bottom">Izmeni cenovnik</h2>
                </Col>
            </Row>
            <Col md={12} xs={12}>
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th >Datum kreiranja</th>
                            <th >Cena po danu</th>
                            <th >Cena po km</th>
                            <th >Cena po km (CDW)</th>
                            <th>Odaberi</th>
                        </tr>
                    </thead>
                    <tbody>
                        {props.getPricelists()}
                    </tbody>
                </Table>

                <Button
                    onClick={props.handleBack}
                    className="float-right"
                >
                    Vrati se
                </Button>

            </Col>
        </Row>
    );
}

export default PricelistFromAdComponent;
