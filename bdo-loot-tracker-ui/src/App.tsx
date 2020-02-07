import React from 'react';
import NavigationBar from './navbar/components/NavigationBar';
import Content from './app/components/Content';
import { routes } from './app/routes';
import InitWrapper from './auth/components/InitWrapper';

const App: React.FC = () => {
  return (
    <>
      <InitWrapper>
        <NavigationBar/>
      </InitWrapper>
      <Content routes={routes}/>
    </>
  );
};

export default App;
