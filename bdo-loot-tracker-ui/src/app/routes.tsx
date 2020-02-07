import * as React from 'react';
import LoginForm from '../auth/components/LoginForm';
import { Homepage } from '../homepage/components/Homepage';
import LogoutComponent from '../auth/components/LogoutComponent';
import bdoLootTrackerRoutes from '../bdoloottracker/routes/routes';

const routes = {
  homepage: {
    path: '/',
    exact: true,
    component: () => <Homepage/>, // eslint-disable-line react/display-name
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
  ...bdoLootTrackerRoutes,
};

export { routes };
