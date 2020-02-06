import * as React from 'react';
import SpotListContainer from '../spots/components/SpotListContainer';
import LoginForm from '../auth/components/LoginForm';
import { Homepage } from '../homepage/components/Homepage';
import LogoutComponent from '../auth/components/LogoutComponent';

const routes = {
  homepage: {
    path: '/',
    exact: true,
    component: () => <Homepage/>, // eslint-disable-line react/display-name
  },
  spots: {
    path: '/spots',
    exact: true,
    component: () => <SpotListContainer/>, // eslint-disable-line react/display-name
  },
  login: {
    path: '/login',
    exact: true,
    component: () => <LoginForm/>
  },
  logout: {
    path: '/logout',
    exact: true,
    component: () => <LogoutComponent/>,
  },
};

export { routes };
