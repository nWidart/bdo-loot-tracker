import * as React from 'react';
import { useState } from 'react';
import { Link } from 'react-router-dom';
import { RematchRootState } from '@rematch/core';
import { models } from '../../app/store';
import { connect } from 'react-redux';
import { Collapse, Nav, Navbar, NavbarToggler, NavItem } from 'reactstrap';

const NavigationBar = ({ user }: any) => {
  const [isOpen, setIsOpen] = useState(false);
  const toggle = () => setIsOpen(!isOpen);
  return (<Navbar color="dark" dark expand="md" className="mb-2">
    <div className="container">
      <div className="row">
        <Link to="/" className="navbar-brand">BDO Loot Tracker</Link>
        <NavbarToggler onClick={toggle}/>
        <Collapse isOpen={isOpen} navbar>
          <Nav className="pull-right" navbar>
            <NavItem>
              <Link to="/spots" className="nav-link">Spots</Link>
            </NavItem>
            {user === undefined && (
              <NavItem>
                <Link to="/login" className="nav-link">Login</Link>
              </NavItem>
            )}
            {user !== undefined && (
              <NavItem>
                <Link to="/logout" className="nav-link">Logout</Link>
              </NavItem>
            )}
          </Nav>
        </Collapse>
      </div>
    </div>
  </Navbar>);
};

const mapStateToProps = (state: RematchRootState<models>) => ({
  user: state.authentication.user,
});

export default connect(mapStateToProps as any)(NavigationBar);
