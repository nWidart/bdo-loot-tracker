import React from 'react';
import { connect } from 'react-redux';
import { RematchDispatch, RematchRootState } from '@rematch/core';
import { Redirect } from 'react-router-dom';

import { models } from '../../app/store';

interface LoginFormState {
  username: string;
  password: string;
}

class LoginForm extends React.Component<any, LoginFormState> {
  constructor(props: any) {
    super(props);
    this.state = {
      username: 'nwidart@example.com',
      password: '',
    };
  }

  updateField = (event: React.FormEvent<HTMLInputElement>) => {
    const value = event.currentTarget.id as 'username';
    this.setState({ [value]: event.currentTarget.value });
  };

  handleSubmit = async () => {
    await this.props.login(this.state);
    await this.props.getCurrentUser();
  };

  render() {
    if (this.props.currentUser) {
      return <Redirect to="/"/>;
    }
    return (
      <div className="container">
        <div className="row">
          <div className="col">
            <form onSubmit={this.handleSubmit}>
              <div className="form-group">
                <label htmlFor="email">Email address</label>
                <input type="email"
                       className="form-control"
                       id="email" aria-describedby="emailHelp"
                       value={this.state.username}
                       placeholder="Username"
                       onChange={this.updateField}/>
              </div>
              <div className="form-group">
                <label htmlFor="password">Password</label>
                <input type="password"
                       className="form-control"
                       id="password"
                       value={this.state.password}
                       onChange={this.updateField}/>
              </div>
              <button type="button" className="btn btn-primary" onClick={this.handleSubmit}>Submit</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state: RematchRootState<models>) => ({
  currentUser: state.authentication.user,
});

const mapDispatchToProps = (dispatch: RematchDispatch<models>) => ({
  login: (payload: any) => dispatch.authentication.login(payload),
  getCurrentUser: () => dispatch.authentication.getCurrentUser(),
});

export default connect(mapStateToProps, mapDispatchToProps as any)(LoginForm);
