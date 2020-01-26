import * as React from 'react';
import { values } from 'lodash';
import { Route, Switch } from 'react-router-dom';

interface ContentProps {
  routes: {};
}

export default function Content(props: ContentProps) {
  return (
    <Switch>
      {values(props.routes).map((route: any) => (
        <Route
          key={route.path}
          path={route.path}
          component={route.component}
          exact={route.exact}
        />
      ))}
    </Switch>
  );
}
