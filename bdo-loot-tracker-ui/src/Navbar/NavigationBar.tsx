import * as React from 'react';

const NavigationBar = () => (
  <nav className="bg-white shadow" role="navigation">
    <div className="container mx-auto p-4 flex flex-wrap items-center md:flex-no-wrap">
      <div className="mr-4 md:mr-8">
        <a href="/" rel="home">
          BDO Loot Tracker
        </a>
      </div>
      <div className="ml-auto md:hidden">
        <button className="flex items-center px-3 py-2 border rounded" type="button">
          <svg className="h-3 w-3" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
            <title>Menu</title>
            <path d="M0 3h20v2H0V3zm0 6h20v2H0V9zm0 6h20v2H0v-2z"/>
          </svg>
        </button>
      </div>
      <div className="w-full md:w-auto md:flex-grow md:flex md:items-center">
        <ul
          className="flex flex-col mt-4 -mx-4 pt-4 border-t md:flex-row md:items-center md:mx-0 md:ml-auto md:mt-0 md:pt-0 md:border-0">
          <li>
            <a className="block px-4 py-1 md:p-2 lg:px-4 text-purple-600" href="/" title="Active Link">Spots</a>
          </li>
          <li>
            <a className="block px-4 py-1 md:p-2 lg:px-4" href="/login" title="Link">Login</a>
          </li>
          <li>
            <a className="block px-4 py-1 md:p-2 lg:px-4" href="/logout" title="Link">Logout</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
);
export default NavigationBar;
