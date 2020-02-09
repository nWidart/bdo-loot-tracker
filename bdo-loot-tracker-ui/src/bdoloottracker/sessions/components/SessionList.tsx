import * as React from 'react';
import { connect } from 'react-redux';
import { RematchDispatch, RematchRootState } from '@rematch/core';
import { models } from '../../../app/store';
import { Session } from '../model';
import { Link } from 'react-router-dom';

class SessionList extends React.Component<any> {
  componentDidMount() {
    this.props.getSessions();
  }

  render() {
    return (
      <div className="container">
        <div className="row">
          <div className="col">
            <h4>Sessions</h4>
            <table className="table table-striped table-hover">
              <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Created at</th>
                <th scope="col">Spot</th>
                <th scope="col">Ap</th>
                <th scope="col">Dp</th>
                <th scope="col">Class</th>
                <th></th>
              </tr>
              </thead>
              <tbody>
              {this.props.sessions && this.props.sessions.map((session: Session) => (
                <tr key={session.id}>
                  <th scope="row">{session.id}</th>
                  <td>{new Date(session.createdAt).toDateString()}</td>
                  <td>{session.spotId}</td>
                  <td>{session.ap}</td>
                  <td>{session.dp}</td>
                  <td>{session.bdoClass}</td>
                  <td>
                    <div className="btn-group float-right">
                      <Link to={`/app/session/${session.id}/run/new`} className="btn btn-primary btn-sm">
                        Add run
                      </Link>
                      <Link to={`/app/session/${session.id}/runs`} className="btn btn-secondary btn-sm">
                        View runs
                      </Link>
                    </div>
                  </td>
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
  sessions: state.sessions.sessions,
});

const mapDispatchToProps = (dispatch: RematchDispatch<models>) => ({
  getSessions: () => dispatch.sessions.getSessions(),
});

export default connect(mapStateToProps, mapDispatchToProps as any)(SessionList);
