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
      <div className="container mx-auto">
        <div className="flex mb-4">
          <Content routes={routes}/>
        </div>
      </div>
    </>
  );
};

export default App;
