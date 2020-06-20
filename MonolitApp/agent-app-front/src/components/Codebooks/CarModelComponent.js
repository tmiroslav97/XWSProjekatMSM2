import React from 'react';
import { Container, Row, Col, Button, Table } from 'react-bootstrap'

const CarModelComponent = (props) => {

    return (
        <Row>
            <Col md={6} xs={12}>
                <Table responsive>
                    <thead>
                        <tr>
                            <th>Naziv</th>
                            <th>Proizvodjač</th>
                            <th className="text-right">Izmjena</th>
                            <th className="text-right">Brisanje</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            props.carModels.map((carModel) => {
                                return (
                                    <tr key={carModel.id}>
                                        <td>{carModel.name}</td>
                                        <td>{carModel.carManufacturer}</td>
                                        <td align="right">
                                            <Button variant="outline-success" onClick={() => { props.handleEdit(carModel); }}>Izmjeni</Button>
                                        </td>
                                        <td align="right">
                                            <Button variant="outline-danger" onClick={() => { props.handleDelete(carModel.id); }}>Obriši</Button>
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

export default CarModelComponent;