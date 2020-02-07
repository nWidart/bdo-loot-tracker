import * as React from 'react';
import { Link } from 'react-router-dom';
import { RematchRootState } from '@rematch/core';
import { models } from '../../app/store';
import { connect } from 'react-redux';

const NavigationBar = ({ user }: any) => (
  <nav className="bg-blue-100 shadow mb-4" role="navigation">
    <div className="container mx-auto p-4 flex flex-wrap items-center md:flex-no-wrap">
      <div className="mr-4 md:mr-8">
        <Link to="/">BDO Loot Tracker</Link>
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
            {user !== undefined && (
              <Link to="/session/new">
                <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full">
                  Start new session
                </button>
              </Link>
            )}
          </li>
          <li>
            <Link to="/spots" className="block px-4 py-1 md:p-2 lg:px-4 text-purple-600">Spots</Link>
          </li>
          {user === undefined && (
            <li>
              <Link to="/login" className="block px-4 py-1 md:p-2 lg:px-4">Login</Link>
            </li>
          )}
          {user !== undefined && (
            <li>
              <a className="block px-4 py-1 md:p-2 lg:px-4" href="/logout" title="Link">Logout</a>
            </li>
          )}

        </ul>
      </div>
    </div>
  </nav>
);

const mapStateToProps = (state: RematchRootState<models>) => ({
  user: state.authentication.user,
});

export default connect(mapStateToProps as any)(NavigationBar);
