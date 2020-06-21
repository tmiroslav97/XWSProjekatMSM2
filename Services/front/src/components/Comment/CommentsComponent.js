import React from 'react';
import { Row, Col, Button, Table } from 'react-bootstrap'

const CommentsComponent = (props) => {

    return (
        <Row>
            <Col md={12} xs={12}>
                <Table responsive>
                    <thead>
                        <tr>
                            <th>Datum kreiranja</th>
                            <th>Ime i prezime</th>
                            <th>Sadrzaj</th>
                            <th className="text-right">Odobri</th>
                        </tr>
                    </thead>
                    <tbody>
                        {props.getComments()}
                    </tbody>
                </Table>
            </Col>
        </Row>
    );
}

export default CommentsComponent;
