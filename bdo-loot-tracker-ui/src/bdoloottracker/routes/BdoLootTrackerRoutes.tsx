import * as React from 'react';
import { Route, Switch } from 'react-router-dom';
import { values } from 'lodash';
import routes from './routes';

function BdoLootTrackerRoutes() {
  return (
    <>
      <Switch>
        {values(routes.bdoLootTracker.children).map((route) => {
          return (
            <Route
              key={route.path}
              path={route.path}
              component={route.component}
              exact={route.exact}
            />
          );
        })}
      </Switch>
    </>
  );
}

export default BdoLootTrackerRoutes;
