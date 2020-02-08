import * as React from 'react';
import { RematchDispatch, RematchRootState } from '@rematch/core';
import { models } from '../../../app/store';
import { connect } from 'react-redux';

class RunList extends React.Component<any, any> {
  componentDidMount() {

  }
  render() {
    return (
      <></>
    );
  }
}

const mapStateToProps = (state: RematchRootState<models>) => ({
  runs: state.runs.runs,
});

const mapDispatchToProps = (dispatch: RematchDispatch<models>) => ({
  getRuns: () => dispatch.runs.getRuns(),
});

export default connect(mapStateToProps, mapDispatchToProps as any)(RunList);
