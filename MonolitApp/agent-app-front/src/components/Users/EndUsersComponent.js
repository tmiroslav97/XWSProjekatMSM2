import React from 'react';
import { Row, Col, Button, Table } from 'react-bootstrap'

const EndUsersComponent = (props) => {

    return (
        <Row>
            <Col md={12} xs={12}>
                <Table responsive>
                    <thead>
                        <tr>
                            <th>Email</th>
                            <th>Ime i prezime</th>
                            <th>Broj otkazivanja</th>
                            <th className="text-right">Blokiranje</th>
                            <th className="text-right">Zabrana</th>
                            <th className="text-right">Logičko brisanje</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            props.endUsers.map((endUser) => {
                                return (
                                    <tr key={endUser.id}>
                                        <td>{endUser.email}</td>
                                        <td>{endUser.firstName + ' ' + endUser.lastName}</td>
                                        <td>{endUser.canceledCnt}</td>
                                        {!endUser.deleted ? <td align="right">
                                            {endUser.enabled ? <Button variant="outline-danger" onClick={() => { props.handleBlock(endUser.id); }}>Blokiraj</Button> : <Button variant="outline-success" onClick={() => { props.handleUnblock(endUser.id); }}>Odblokiraj</Button>}
                                        </td> : <td></td>
                                        }
                                        {!endUser.deleted && endUser.enabled ? <td align="right">
                                            {!endUser.obligated ? <Button variant="outline-danger" onClick={() => { props.handleObligate(endUser.id); }}>Uvedi zabranu</Button > : <Button variant="outline-success" onClick={() => { props.handleUnObligate(endUser.id); }}>Skini zabranu</Button>}
                                        </td> : <td></td>
                                        }
                                        <td align="right">
                                            {endUser.deleted ? <Button variant="outline-success" onClick={() => { props.handleRevert(endUser.id); }}>Vrati</Button> : <Button variant="outline-danger" onClick={() => { props.handleLogDelete(endUser.id); }}>Obriši</Button>}
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

export default EndUsersComponent;
