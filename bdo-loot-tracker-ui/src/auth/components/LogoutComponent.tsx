import React from 'react';
import { connect } from 'react-redux';
import { RematchDispatch } from '@rematch/core';
import { Redirect } from 'react-router';
import { models } from '../../app/store';

class LogoutComponent extends React.Component<any, any> {
  componentDidMount() {
    this.props.logout();
  }

  render() {
    return <Redirect to="/login"/>;
  }
}

const mapDispatchToProps = (dispatch: RematchDispatch<models>) => ({
  logout: () => dispatch.authentication.logout(),
});

export default connect(null, mapDispatchToProps as any)(LogoutComponent);
