import React from 'react';
import { Container, Row, Col, Button, Table } from 'react-bootstrap'

const CarManufacturerComponent = (props) => {

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
                            props.carManufacturers.map((carManufacturer) => {
                                return (
                                    <tr key={carManufacturer.id}>
                                        <td>{carManufacturer.name}</td>
                                        <td align="right">
                                            <Button variant="outline-success" onClick={() => { props.handleEdit(carManufacturer); }}>Izmjeni</Button>
                                        </td>
                                        <td align="right">
                                            <Button variant="outline-danger" onClick={() => { props.handleDelete(carManufacturer.id); }}>Obriši</Button>
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

export default CarManufacturerComponent;
