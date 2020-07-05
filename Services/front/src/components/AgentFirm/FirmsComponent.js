import React from 'react';
import { Row, Col, Button, Table } from 'react-bootstrap'

const FirmsComponent = (props) => {

    return (
        <Row>
            <Col md={12} xs={12}>
                <Table responsive>
                    <thead>
                        <tr>
                            <th>Email</th>
                            <th>Naziv firme</th>
                            <th>PMB</th>
                            <th>Adresa</th>
                            <th className="text-right">Logičko brisanje</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            props.firms.map((firm) => {
                                return (
                                    <tr key={firm.id}>
                                        <td>{firm.email}</td>
                                        <td>{firm.firmName}</td>
                                        <td>{firm.pmb}</td>
                                        <td>{firm.address}</td>
                                        <td align="right">
                                            {firm.deleted ? <Button variant="outline-success" onClick={() => { props.handleRevert(firm.id); }}>Vrati</Button> : <Button variant="outline-danger" onClick={() => { props.handleLogDelete(firm.id); }}>Obriši</Button>}
                                        </td>
                                    </tr>
                                );
                            })
                        }
                    </tbody>
                </Table>
            </Col>
        </Row>
    );
}

export default FirmsComponent;
