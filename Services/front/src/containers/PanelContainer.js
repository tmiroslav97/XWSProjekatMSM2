import React, { useState } from 'react';
import { history } from '../index';
import { useSelector } from 'react-redux';
import { Link, Route, Switch } from 'react-router-dom';
import { Container, Row, Col, Button, CardDeck, ListGroup, Tab, Nav } from 'react-bootstrap'
import jwt_decode from 'jwt-decode';
import { tokenSelector } from '../store/user/selectors';
import CarManufacturerContainer from '../containers/Codebooks/CarManufacturerContainer';
import CarModelContainer from '../containers/Codebooks/CarModelContainer';
import CarTypeContainer from '../containers/Codebooks/CarTypeContainer';
import FuelTypeContainer from '../containers/Codebooks/FuelTypeContainer';
import GearboxTypeContainer from '../containers/Codebooks/GearboxTypeContainer';
import EndUsersContainer from '../containers/Users/EndUsersContainer';
import CreateAdContainer from './Ad/CreateAdContainer';
import MyAdsContainer from './Ad/MyAdsContainer';
import EndUserRequestsContainer from './Request/EndUserRequestsContainer';
import AgentRequestsContainer from './Request/AgentRequestsContainer';
import PrivateRoute from '../authorization/PrivateRoute';
import PanelHomeContainer from './PanelHomeContainer';


const PanelContainer = ({ match }) => {
    const token = useSelector(tokenSelector);

    const hasRole = (accessRole) => {

        if (token != null) {
            const roles = jwt_decode(token).roles;
            const role = roles.filter(val => accessRole.includes(val));
            return role.length > 0;
        } else {
            return false;
        }
    };

    return (
        <Container fluid>
            <Row>
                <Col sm={2} md={2} xs={12} className="pl-0">
                    <Nav variant="pills" className="flex-column bg-light">
                        {hasRole(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_AGENT']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="home" onClick={() => { history.push("/panel/home"); }}>
                                    Panel pocetna
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_ADMIN']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="car-man" onClick={() => { history.push("/panel/car-man"); }}>
                                    Proizvođači automobila
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_ADMIN']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="car-model" onClick={() => { history.push("/panel/car-model"); }}>
                                    Modeli automobila
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_ADMIN']) &&
                            <Nav.Item >
                                <Nav.Link eventKey="car-type" onClick={() => { history.push("/panel/car-type"); }}>
                                    Tipovi automobila
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_ADMIN']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="fuel-type" onClick={() => { history.push("/panel/fuel-type"); }}>
                                    Tipovi goriva
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_ADMIN']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="gb-type" onClick={() => { history.push("/panel/gb-type"); }}>
                                    Tipovi mjenjača
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_ADMIN']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="end-users" onClick={() => { history.push("/panel/man-end-users"); }}>
                                    Krajnji korisnici
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_AGENT', 'ROLE_USER']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="create-ad" onClick={() => { history.push("/panel/create-ad"); }}>
                                    Dodaj oglas
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_AGENT', 'ROLE_USER']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="my-ads" onClick={() => { history.push("/panel/my-ads"); }}>
                                    Moji oglasi
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_USER']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="end-user-reqs" onClick={() => { history.push("/panel/end-user-reqs"); }}>
                                    Moji zahtjevi
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_AGENT']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="publisher-user-reqs" onClick={() => { history.push("/panel/publisher-user-reqs"); }}>
                                    Moji zahtjevi
                                </Nav.Link>
                            </Nav.Item>
                        }
                    </Nav>
                </Col>
                <Col sm={10} md={10} xs={12}>
                    <PrivateRoute exact path={`${match.path}/home`} component={PanelHomeContainer} token={token} hasRightRole={hasRole} accessRole={["ROLE_ADMIN", "ROLE_USER", "ROLE_AGENT"]} />
                    <PrivateRoute exact path={`${match.path}/car-man`} component={CarManufacturerContainer} token={token} hasRightRole={hasRole} accessRole={["ROLE_ADMIN"]} />
                    <PrivateRoute exact path={`${match.path}/car-model`} component={CarModelContainer} token={token} hasRightRole={hasRole} accessRole={["ROLE_ADMIN"]} />
                    <PrivateRoute exact path={`${match.path}/car-type`} component={CarTypeContainer} token={token} hasRightRole={hasRole} accessRole={["ROLE_ADMIN"]} />
                    <PrivateRoute exact path={`${match.path}/fuel-type`} component={FuelTypeContainer} token={token} hasRightRole={hasRole} accessRole={["ROLE_ADMIN"]} />
                    <PrivateRoute exact path={`${match.path}/gb-type`} component={GearboxTypeContainer} token={token} hasRightRole={hasRole} accessRole={["ROLE_ADMIN"]} />
                    <PrivateRoute exact path={`${match.path}/man-end-users`} component={EndUsersContainer} token={token} hasRightRole={hasRole} accessRole={["ROLE_ADMIN"]} />
                    <PrivateRoute exact path={`${match.path}/create-ad`} component={CreateAdContainer} token={token} hasRightRole={hasRole} accessRole={['ROLE_AGENT', 'ROLE_USER']} />
                    <PrivateRoute exact path={`${match.path}/my-ads`} component={MyAdsContainer} token={token} hasRightRole={hasRole} accessRole={['ROLE_AGENT', 'ROLE_USER']} />
                    <PrivateRoute exact path={`${match.path}/end-user-reqs`} component={EndUserRequestsContainer} token={token} hasRightRole={hasRole} accessRole={['ROLE_USER']} />
                    <PrivateRoute exact path={`${match.path}/publisher-user-reqs`} component={AgentRequestsContainer} token={token} hasRightRole={hasRole} accessRole={['ROLE_AGENT']} />
                </Col>
            </Row>
        </Container >
    );
}

export default PanelContainer;
