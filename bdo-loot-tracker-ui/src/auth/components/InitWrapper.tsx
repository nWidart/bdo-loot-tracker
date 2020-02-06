import React from 'react';
import { connect } from 'react-redux';
import { RematchDispatch } from '@rematch/core';
import { models } from '../../app/store';

class InitWrapper extends React.Component<any, any> {

  componentDidMount() {
    this.props.checkCookie();
  }

  render() {
    return this.props.children;
  }
}

const mapDispatchToProps = (dispatch: RematchDispatch<models>) => ({
  checkCookie: () => dispatch.authentication.checkCookie(),
});

export default connect(null, mapDispatchToProps as any)(InitWrapper);
