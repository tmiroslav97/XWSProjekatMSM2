import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Navbar, Nav, Button, Form, FormControl } from 'react-bootstrap';
import { history } from '../index';
import { tokenSelector } from '../store/user/selectors';
import { signOut } from '../store/user/actions';
import jwt_decode from 'jwt-decode';

const NavBar = () => {
    const token = useSelector(tokenSelector);
    const dispatch = useDispatch();
    var role = null;
    if (token != null) {
        role = jwt_decode(token).role;
    }

    const handleSignOut = () => {
        dispatch(
            signOut()
        );
    };

    return (
        <Navbar collapseOnSelect bg="light" expand="lg">
            <Navbar.Brand onClick={() => { history.push('/') }}>Rent A Car</Navbar.Brand>
            <Navbar.Toggle aria-controls="responsive-navbar-nav" />
            <Navbar.Collapse id="responsive-navbar-nav">
                <Nav className="mr-auto">
                    <Nav.Link onClick={() => { history.push('/') }}>Početna stranica</Nav.Link>
                </Nav>
                <Nav className="ml-auto">
                    {token != null && <Nav.Link href="#" onClick={() => { history.push('/panel') }}>Radni panel</Nav.Link>}
                    {token == null && <Nav.Link href="#" onClick={() => { history.push('/login') }}>Prijava</Nav.Link>}
                    {token == null && <Nav.Link href="#" onClick={() => { history.push('/sign-up') }}>Registracija</Nav.Link>}
                    {token != null && <Nav.Link href="#" onClick={() => handleSignOut()}>Odjavi se</Nav.Link>}
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    );
}

export default NavBar;