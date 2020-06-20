import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { registerUser } from '../../store/user/actions';
import RegPage from '../../components/Authorization/RegPage';

const RegContainer = () => {
    const dispatch = useDispatch();
    const [validated, setValidated] = useState(false);

    const handleRegister = (event) => {
        event.preventDefault();
        const form = event.target;
        const data = new FormData(event.target);

        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            dispatch(
                registerUser({
                    "email": data.get('email'),
                    "password": data.get('password'),
                    "password2": data.get('password2'),
                    "firstName": data.get('firstName'),
                    "lastName": data.get('lastName')
                })
            );
            setValidated(false);
        }
    };



    return (
        <RegPage onSubmit={handleRegister} validated={validated} />
    );
}

export default RegContainer;