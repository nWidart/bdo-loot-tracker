import * as React from 'react';
import SpotListContainer from './spots/components/SpotListContainer';
import LoginForm from './auth/components/LoginForm';

const routes = {
  index: {
    path: '/',
    exact: true,
    component: () => <SpotListContainer/>, // eslint-disable-line react/display-name
  },
  login: {
    path: '/login',
    exact: true,
    component: () => <LoginForm/>
  }
};

export { routes };
