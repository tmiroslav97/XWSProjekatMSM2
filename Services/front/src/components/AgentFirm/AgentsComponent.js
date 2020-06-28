import React from 'react';
import { Row, Col, Button, Table } from 'react-bootstrap'

const AgentsComponent = (props) => {

    return (
        <Row>
            <Col md={12} xs={12}>
                <Table responsive>
                    <thead>
                        <tr>
                            <th>Email</th>
                            <th>Ime i prezime</th>
                            <th>Lokalni agent</th>
                            <th className="text-right">Logičko brisanje</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            props.agents.map((agent) => {
                                return (
                                    <tr key={agent.id}>
                                        <td>{agent.email}</td>
                                        <td>{agent.firstName + ' ' + agent.lastName}</td>
                                        <td>
                                            {agent.local ? "Da" : "Ne"}
                                        </td>
                                        <td align="right">
                                            {agent.deleted ? <Button variant="outline-success" onClick={() => { props.handleRevert(agent.id); }}>Vrati</Button> : <Button variant="outline-danger" onClick={() => { props.handleLogDelete(agent.id); }}>Obriši</Button>}
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

export default AgentsComponent;
