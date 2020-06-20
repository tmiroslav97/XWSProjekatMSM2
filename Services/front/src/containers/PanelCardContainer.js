import React, { useState, useEffect } from 'react';
import { history } from '../index';
import { useSelector } from 'react-redux';
import { Container, Row, Col, Button, Card } from 'react-bootstrap'
import jwt_decode from 'jwt-decode';
import { tokenSelector } from '../store/user/selectors';
import EndUsersContainer from '../containers/Users/EndUsersContainer';
import CreateAdContainer from './Ad/CreateAdContainer';
import MyAdsContainer from './Ad/MyAdsContainer';
import EndUserRequestsContainer from './Request/EndUserRequestsContainer';

const PanelCardContainer = () => {
    const token = useSelector(tokenSelector);
    const [componentsMap, setComponentsMap] = useState(new Map([
        ["CarManufacturerContainer", { roles: ['ROLE_ADMIN'], link: "prazno", title: "Šifarnik proizvođača automobila", description: "Stranica sa proizvođačima" }],
        ["CarModelContainer", { roles: ['ROLE_ADMIN'], link: "prazno", title: "Šifarnik modela automobila", description: "Stranica sa modelima" }],
        ["CarTypeContainer", { roles: ['ROLE_ADMIN'], link: "prazno", title: "Šifarnik tipova automobila", description: "Stranica sa tipovima" }],
        ["FuelTypeContainer", { roles: ['ROLE_ADMIN'], link: "prazno", title: "Šifarnik tipova goriva", description: "Stranica sa tipovima goriva" }],
        ["GearboxTypeContainer", { roles: ['ROLE_ADMIN'], link: "prazno", title: "Šifarnik tipova mjenjača", description: "Stranica sa tipovima mjenjaca" }],
        ["EndUsersContainer", { roles: ['ROLE_ADMIN'], link: "prazno", title: "Manipulacija korisnicima", description: "Stranica za manipulaciju korisnicima" }],
        ["CreateAdContainer", { roles: ['ROLE_AGENT', 'ROLE_USER'], link: "prazno", title: "Kreiranje oglasa", description: "Stranica za kreiranje oglasa" }],
        ["MyAdsContainer", { roles: ['ROLE_AGENT', 'ROLE_USER'], link: "prazno", title: "Moji oglasi", description: "Stranica sa mojim oglasima" }],
        ["EndUserRequestsContainer", { roles: ['ROLE_USER'], link: "prazno", title: "Moji zahtjevi", description: "Stranica sa mojim zahtjevima" }]
    ]));
    const [filtered, setFiltered] = useState([]);
    useEffect(() => {
        const arr = [];
        [...componentsMap.keys()].map(value => {
            if (hasRole(componentsMap.get(value).roles)) {
                arr.push(value);
            }
        })
        var chunk_size = 4;

        const groups = arr.map(function (e, i) {
            return i % chunk_size === 0 ? arr.slice(i, i + chunk_size) : null;
        }).filter(function (e) { return e; });

        setFiltered(groups);
        console.log(filtered);
    }, []);

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
        <Container>
            {
                filtered.map((value, idx) => {
                    return (
                        <Row key={idx}>
                            {
                                value.map((item, index) => {
                                    console.log(componentsMap.get(item));
                                    return (
                                        <Col md={3} xs={12} key={index}>
                                            <Card className="mt-2">
                                                <Card.Body>
                                                    <Card.Title>{componentsMap.get(item).title}</Card.Title>
                                                    <Card.Text>
                                                        {componentsMap.get(item).description}
                                                    </Card.Text>
                                                    <Button variant="primary" onClick={() => { history.push(componentsMap.get(item).link); }}>Idi</Button>
                                                </Card.Body>
                                            </Card>
                                        </Col>
                                    );
                                })
                            }
                        </Row>
                    );
                })
            }
        </Container>
    );
}

export default PanelCardContainer;
