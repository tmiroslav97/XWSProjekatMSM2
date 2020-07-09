import React from 'react';
import { Row, Col, Button, Table } from 'react-bootstrap';

const DiscountsFromAdComponent = (props) => {

    return (
        <Row>
            <Row>
                <Col md={12} xs={12}>
                    <h2 className="border-bottom">Dodaj popuste za oglas</h2>
                </Col>
            </Row>
            <Col md={12} xs={12}>
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th >Popust</th>
                            <th >Broj dana za rentiranje</th>
                            <th>Odaberi</th>
                        </tr>
                    </thead>
                    <tbody>
                        {props.getDiscountList()}
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

export default DiscountsFromAdComponent;
