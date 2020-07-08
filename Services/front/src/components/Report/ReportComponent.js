import React from 'react';
import { Row, Col, Table } from 'react-bootstrap'

const ReportComponent = (props) => {

    return (
        <Row>
            <Col md={12} xs={12}>
                <Table responsive>
                    <thead>
                        <tr>
                            <th>Broj predjenih kilometara</th>
                            <th>Opis</th>
                            <th>Dodatna doplata</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                {props.report.distanceTraveled}
                            </td>
                            <td>
                                {props.report.description}
                            </td>
                            <td>
                                {props.report.invoice != null ? props.report.invoice.amount : "-"}
                            </td>
                        </tr>
                    </tbody>
                </Table>
            </Col>
        </Row>
    );
}

export default ReportComponent;
