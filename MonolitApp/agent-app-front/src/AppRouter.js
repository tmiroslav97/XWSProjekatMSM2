import React from 'react';
import { useSelector } from 'react-redux';
import jwt_decode from 'jwt-decode';
import { Route, Redirect, Switch } from 'react-router-dom';
import { tokenSelector } from './store/user/selectors';
import PrivateRoute from './authorization/PrivateRoute';
import HomePage from './containers/HomePage';
import LoginContainter from './containers/Authorization/LoginContainer';
import RegContainer from './containers/Authorization/RegContainer';
import AgentFirmHomePage from './components/AgentFirm/AgentFirmHomePage';
import CreateAdContainer from './containers/Ad/CreateAdContainer';
import AdListContainer from './containers/Ad/AdListContainer';
import AdDetailViewContainer from './containers/Ad/AdDetailViewContainer';
import PanelContainer from './containers/PanelContainer';

const AppRouter = () => {
  const token = useSelector(tokenSelector);

  function hasRightRole(accessRole) {

    if (!accessRole) {
      return true;
    }


    if (token != null) {
      const roles = jwt_decode(token).roles;
      const role = roles.filter(val => accessRole.includes(val));
      return role.length > 0;
    } else {
      return true;
    }

  }
  return (
    <Switch>
      <Route exact path="/" component={HomePage} />
      <Route exact path="/login" render={props => token == null ? (<LoginContainter {...props} />) : (<Redirect to="/" />)} />
      <Route exact path="/sign-up" render={props => token == null ? (<RegContainer {...props} />) : (<Redirect to="/" />)} />

      <PrivateRoute exact path="/agent-firm" component={AgentFirmHomePage}  token={token} hasRightRole={hasRightRole} accessRole={["ROLE_AGENT"]} />
      <PrivateRoute exact path="/agent-firm/create-ad" component={CreateAdContainer}  token={token} hasRightRole={hasRightRole} accessRole={["ROLE_AGENT", "ROLE_USER"]} />
      <PrivateRoute exact path="/agent-firm/ads" component={AdListContainer} />
      <Route exact path="/agent-firm/ad-detail-view/:ad" component={AdDetailViewContainer} />
      <PrivateRoute  path="/panel" component={PanelContainer}  token={token} hasRightRole={hasRightRole} accessRole={["ROLE_AGENT", "ROLE_USER", "ROLE_ADMIN"]} />

      <Route exact path="/page-not-found" component={() => <h1>Page not found!</h1>} />
      <Redirect from="*" to="/page-not-found" />
    </Switch>
  );
}

export default AppRouter;
