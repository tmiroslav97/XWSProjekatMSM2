import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Navbar, Nav, Button, Form, FormControl } from 'react-bootstrap';
import { history } from '../index';
import { tokenSelector } from '../store/user/selectors';
import { signOut } from '../store/user/actions';
import jwt_decode from 'jwt-decode';
import AppsIcon from '@material-ui/icons/Apps';
import LocalMallIcon from '@material-ui/icons/LocalMall';

const NavBar = () => {
    const token = useSelector(tokenSelector);
    const dispatch = useDispatch();
    var roles = null;
    if (token != null) {
        roles = jwt_decode(token).roles;
    }

    const handleSignOut = () => {
        dispatch(
            signOut()
        );
    };

    return (
        <Navbar collapseOnSelect bg="light" expand="lg">
            <Navbar.Brand onClick={() => { history.push('/') }}> <img
                alt=""
                src="/freeLogo.svg"
                width="30"
                height="30"
                className="d-inline-block align-top"
            />{' '}Rent A Car{' '}</Navbar.Brand>
            <Navbar.Toggle aria-controls="responsive-navbar-nav" />
            <Navbar.Collapse id="responsive-navbar-nav">
                <Nav className="mr-auto">
                    <Nav.Link onClick={() => { history.push('/') }}>{' '}Početna stranica</Nav.Link>
                </Nav>
                <Nav className="ml-auto">
                    {token != null && roles.includes('ROLE_USER') && <Nav.Link href="#" onClick={() => { history.push('/cart') }}><LocalMallIcon/>Korpa</Nav.Link>}
                    {token != null && <Nav.Link href="#" onClick={() => { history.push('/panel') }}><AppsIcon/> Radni panel</Nav.Link>}
                    {token == null && <Nav.Link href="#" onClick={() => { history.push('/login') }}>Prijava</Nav.Link>}
                    {token == null && <Nav.Link href="#" onClick={() => { history.push('/sign-up') }}>Registracija</Nav.Link>}
                    {token != null && <Nav.Link href="#" onClick={() => handleSignOut()}>Odjavi se</Nav.Link>}
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    );
}

export default NavBar;