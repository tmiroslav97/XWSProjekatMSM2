import React from 'react';
import { Container, Row, Col, Button, Table } from 'react-bootstrap'

const GearboxTypeComponent = (props) => {

    return (
        <Row>
            <Col md={6} xs={12}>
                <Table responsive>
                    <thead>
                        <tr>
                            <th>Naziv</th>
                            <th className="text-right">Izmjena</th>
                            <th className="text-right">Brisanje</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            props.gearboxTypes.map((gearboxType) => {
                                return (
                                    <tr key={gearboxType.id}>
                                        <td>{gearboxType.name}</td>
                                        <td align="right">
                                            <Button variant="outline-success" onClick={() => { props.handleEdit(gearboxType); }}>Izmjeni</Button>
                                        </td>
                                        <td align="right">
                                            <Button variant="outline-danger" onClick={() => { props.handleDelete(gearboxType.id); }}>Obriši</Button>
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

export default GearboxTypeComponent;