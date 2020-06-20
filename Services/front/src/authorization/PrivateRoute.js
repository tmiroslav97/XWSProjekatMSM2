import React from 'react';
import { Route, Redirect } from 'react-router-dom';

const PrivateRoute = ({ component: Component, token, hasRightRole, accessRole = null, ...rest }) => {
    
    return (
        <Route
            {...rest}
            render={(props) => token && hasRightRole(accessRole) ? (<Component {...props} {...rest}/>) : (<Redirect to="/page-not-found" />)}
        />
    );
};

export default PrivateRoute;