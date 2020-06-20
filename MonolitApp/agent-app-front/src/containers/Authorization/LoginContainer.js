import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { loginUser } from '../../store/user/actions';
import LoginPage from '../../components/Authorization/LoginPage';

const LoginContainer = () => {

    const dispatch = useDispatch();
    const [validated, setValidated] = useState(false);

    const handleLogin = (event) => {
        event.preventDefault();
        const form = event.target;
        const data = new FormData(event.target);

        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            dispatch(
                loginUser({
                    "username": data.get('username'),
                    "password": data.get('password')
                })
            );
            setValidated(false);
        }
    };

    return (
        <LoginPage onSubmit={handleLogin} validated={validated} />
    )

}

export default LoginContainer;
