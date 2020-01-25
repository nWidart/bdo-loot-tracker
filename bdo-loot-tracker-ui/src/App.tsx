import React from 'react';
import NavigationBar from './Navbar/NavigationBar';
import Content from './app/Content';
import { routes } from './routes';

const App: React.FC = () => {
  return (
    <>
      <NavigationBar/>
      <div className="container mx-auto">
        <div className="flex mb-4">
          <Content routes={routes}/>
        </div>
      </div>
    </>
  );
};

export default App;
