import * as React from 'react';
import { RematchDispatch, RematchRootState } from '@rematch/core';
import { models } from '../../../app/store';
import { connect } from 'react-redux';
import { withRouter } from 'react-router';
import { RunProjection } from '../model';
import { Link } from 'react-router-dom';

class RunList extends React.Component<any, any> {
  componentDidMount() {
    this.props.findSessionById(this.props.match.params.sessionId);
    this.props.getRunsForSession(this.props.match.params.sessionId);
  }

  render() {
    return (
      <div className="container">
        <div className="row mb-3">
          <div className="col">
            <h4>
              Runs
              ({this.props.session && this.props.session.spotId})
            </h4>
          </div>
          <div className="col">
            <Link to={`/app/session/${this.props.match.params.sessionId}/run/new`}
                  className="btn btn-primary float-right">Add run</Link>
          </div>
        </div>
        <div className="row">
          <div className="col">
            <table className="table table-striped table-hover">
              <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Date</th>
                <th scope="col">Total silver</th>
                <th scope="col">Total silver after tax</th>
                <th></th>
              </tr>
              </thead>
              <tbody>
              {this.props.runs && this.props.runs.map((run: RunProjection) => (
                <tr>
                  <th scope="row">{run.runId}</th>
                  <td>{run.createdAt}</td>
                  <td>{new Intl.NumberFormat('en-EN').format(run.totalSilver)}</td>
                  <td>{new Intl.NumberFormat('en-EN').format(run.totalAfterTax)}</td>
                  <td></td>
                </tr>
              ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state: RematchRootState<models>) => ({
  session: state.sessions.currentSession,
  runs: state.runs.runs,
});

const mapDispatchToProps = (dispatch: RematchDispatch<models>) => ({
  findSessionById: (sessionId: any) => dispatch.sessions.findSessionById(sessionId),
  getRunsForSession: (sessionId: any) => dispatch.runs.getRunsForSession(sessionId),
});

export default withRouter(connect(mapStateToProps, mapDispatchToProps as any)(RunList));
