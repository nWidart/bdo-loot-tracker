import * as React from 'react';
import CreateSessionForm from './CreateSessionForm';
import { RematchDispatch, RematchRootState } from '@rematch/core';
import { models } from '../../../app/store';
import { connect } from 'react-redux';

class CreateSessionComponent extends React.Component<any> {
  componentDidMount() {
    this.props.getSpots();
  }

  handleSubmit = async (payload: any) => {
    await this.props.createSession({
      ...payload,
      userId: this.props.user.id,
    });
    alert('session created');
  };

  render() {
    return <>
      <div className="container">
        <div className="row">
          <div className="col">
            <h4>Start a new session</h4>
            <CreateSessionForm handleSubmit={this.handleSubmit}/>
          </div>
        </div>
      </div>
    </>;
  }
}

const mapStateToProps = (state: RematchRootState<models>) => ({
  user: state.authentication.user,
});

const mapDispatchToProps = (dispatch: RematchDispatch<models>) => ({
  getSpots: () => dispatch.spots.getSpots(),
  createSession: (payload: any) => dispatch.sessions.createSession(payload),
});

export default connect(mapStateToProps, mapDispatchToProps as any)(CreateSessionComponent);
