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
import EndUserRequestDetailContainer from './Request/EndUserRequestDetailContainer';
import AgentRequestsContainer from './Request/AgentRequestsContainer';
import AgentRequestDetailContainer from './Request/AgentRequestDetailContainer';
import PrivateRoute from '../authorization/PrivateRoute';
import PanelHomeContainer from './PanelHomeContainer';
import CommentsContainer from '../containers/Comment/CommentsContainer';
import RegAgentContainer from '../containers/Authorization/RegAgentContainer';
import RegFirmContainer from '../containers/Authorization/RegFirmContainer';
import PricelistContainer from '../containers/Pricelists/PricelistContainer';
import DiscountsContainer from '../containers/Discount/DiscountsContainer';
import StatisticsContainer from '../containers/AgentFirm/StatisticsContainer';
import HomeIcon from '@material-ui/icons/Home';
import PagesIcon from '@material-ui/icons/Pages';
import AddBoxIcon from '@material-ui/icons/AddBox';
import StorageIcon from '@material-ui/icons/Storage';
import GroupIcon from '@material-ui/icons/Group';
import EqualizerIcon from '@material-ui/icons/Equalizer';
import LocalOfferIcon from '@material-ui/icons/LocalOffer';
import AccountTreeIcon from '@material-ui/icons/AccountTree';
import LocalGasStationIcon from '@material-ui/icons/LocalGasStation';
import DriveEtaIcon from '@material-ui/icons/DriveEta';
import CommuteIcon from '@material-ui/icons/Commute';
import BrandingWatermarkIcon from '@material-ui/icons/BrandingWatermark';
import DynamicFeedIcon from '@material-ui/icons/DynamicFeed';
import CommentIcon from '@material-ui/icons/Comment';
import PermIdentityIcon from '@material-ui/icons/PermIdentity';
import SupervisedUserCircleIcon from '@material-ui/icons/SupervisedUserCircle';
import AllInboxIcon from '@material-ui/icons/AllInbox';

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
                <Col sm={2} md={2} xs={12} className="pl-0 ">
                    <Nav variant="pills" className="flex-column bg-light">
                        {hasRole(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_AGENT']) &&
                            <Nav.Item>
                          
                           
                      
                                <Nav.Link eventKey="home" onClick={() => { history.push("/panel/home"); }}>
                                <PagesIcon />  Panel pocetna
                                </Nav.Link>
                            </Nav.Item>
                            
                        }
                        {hasRole(['ROLE_ADMIN']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="car-man" onClick={() => { history.push("/panel/car-man"); }}>
                                  <BrandingWatermarkIcon/>  Proizvođači automobila
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_ADMIN']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="car-model" onClick={() => { history.push("/panel/car-model"); }}>
                                   <CommuteIcon/> Modeli automobila
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_ADMIN']) &&
                            <Nav.Item >
                                <Nav.Link eventKey="car-type" onClick={() => { history.push("/panel/car-type"); }}>
                                   <DriveEtaIcon/> Tipovi automobila
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_ADMIN']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="fuel-type" onClick={() => { history.push("/panel/fuel-type"); }}>
                                <LocalGasStationIcon/>   Tipovi goriva
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_ADMIN']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="gb-type" onClick={() => { history.push("/panel/gb-type"); }}>
                                  <AccountTreeIcon/>   Tipovi mjenjača
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_ADMIN']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="end-users" onClick={() => { history.push("/panel/man-end-users"); }}>
                                   <GroupIcon/>  Krajnji korisnici
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_AGENT', 'ROLE_USER']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="create-ad" onClick={() => { history.push("/panel/create-ad"); }}>
                                   <AddBoxIcon/> Dodaj oglas
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_AGENT', 'ROLE_USER']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="my-ads" onClick={() => { history.push("/panel/my-ads"); }}>
                                  <StorageIcon/>  Moji oglasi
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_USER']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="end-user-reqs" onClick={() => { history.push("/panel/end-user-reqs"); }}>
                                <DynamicFeedIcon/>  Moji zahtjevi
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_AGENT', 'ROLE_USER']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="publisher-user-reqs" onClick={() => { history.push("/panel/publisher-user-reqs"); }}>
                                  <AllInboxIcon/>  Zahtjevi za moje oglase
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_AGENT']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="statistics" onClick={() => { history.push("/panel/statistics"); }}>
                                   <EqualizerIcon/> Statistika
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_ADMIN']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="comments" onClick={() => { history.push("/panel/comments"); }}>
                                   <CommentIcon/> Komentari
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_ADMIN']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="man-agent" onClick={() => { history.push("/panel/man-agent"); }}>
                                  <PermIdentityIcon/>  Agenti
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_ADMIN']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="man-firm" onClick={() => { history.push("/panel/man-firm"); }}>
                                   <SupervisedUserCircleIcon/> Firme
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_AGENT', 'ROLE_USER']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="pricelist" onClick={() => { history.push("/panel/pricelist"); }}>
                                   <LocalOfferIcon/> Cenovnici
                                </Nav.Link>
                            </Nav.Item>
                        }
                        {hasRole(['ROLE_AGENT']) &&
                            <Nav.Item>
                                <Nav.Link eventKey="discount-list" onClick={() => { history.push("/panel/discount-list"); }}>
                                    Moji popusti
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
                    <PrivateRoute exact path={`${match.path}/end-user-reqs/:id`} component={EndUserRequestDetailContainer} token={token} hasRightRole={hasRole} accessRole={['ROLE_USER']} />
                    <PrivateRoute exact path={`${match.path}/publisher-user-reqs`} component={AgentRequestsContainer} token={token} hasRightRole={hasRole} accessRole={['ROLE_AGENT', 'ROLE_USER']} />
                    <PrivateRoute exact path={`${match.path}/publisher-user-reqs/:id`} component={AgentRequestDetailContainer} token={token} hasRightRole={hasRole} accessRole={['ROLE_AGENT', 'ROLE_USER']} />
                    <PrivateRoute exact path={`${match.path}/statistics`} component={StatisticsContainer} token={token} hasRightRole={hasRole} accessRole={['ROLE_AGENT']} />
                    <PrivateRoute exact path={`${match.path}/comments`} component={CommentsContainer} token={token} hasRightRole={hasRole} accessRole={["ROLE_ADMIN"]} />
                    <PrivateRoute exact path={`${match.path}/man-agent`} component={RegAgentContainer} token={token} hasRightRole={hasRole} accessRole={["ROLE_ADMIN"]} />
                    <PrivateRoute exact path={`${match.path}/man-firm`} component={RegFirmContainer} token={token} hasRightRole={hasRole} accessRole={["ROLE_ADMIN"]} />
                    <PrivateRoute exact path={`${match.path}/pricelist`} component={PricelistContainer} token={token} hasRightRole={hasRole} accessRole={['ROLE_AGENT', 'ROLE_USER']} />
                    <PrivateRoute exact path={`${match.path}/discount-list`} component={DiscountsContainer} token={token} hasRightRole={hasRole} accessRole={['ROLE_AGENT']} />
                </Col>
            </Row>
        </Container >
    );
}

export default PanelContainer;
