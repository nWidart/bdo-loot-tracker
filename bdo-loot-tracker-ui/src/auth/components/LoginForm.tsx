import React from 'react';
import { connect } from 'react-redux';
import { RematchDispatch, RematchRootState } from '@rematch/core';
import { Redirect } from 'react-router-dom';

import { models } from '../../store';

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

  updateUsername = (event: React.FormEvent<HTMLInputElement>) => {
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
      <div className="container mx-auto">
        <div className="flex justify-center px-6 my-12">
          <div className="w-full xl:w-3/4 lg:w-11/12 flex">
            <div className="w-full lg:w-1/2 bg-white p-5 rounded-lg">
              <h3 className="pt-4 text-2xl text-center">Welcome Back!</h3>
              <form className="px-8 pt-6 pb-8 mb-4 bg-white rounded" onSubmit={this.handleSubmit}>
                <div className="mb-4">
                  <label className="block mb-2 text-sm font-bold text-gray-700" htmlFor="username">
                    Username
                  </label>
                  <input
                    className="w-full px-3 py-2 text-sm leading-tight text-gray-700 border rounded shadow appearance-none focus:outline-none focus:shadow-outline"
                    id="username"
                    type="text"
                    value={this.state.username}
                    placeholder="Username"
                    onChange={this.updateUsername}
                  />
                </div>
                <div className="mb-4">
                  <label className="block mb-2 text-sm font-bold text-gray-700" htmlFor="password">
                    Password
                  </label>
                  <input
                    className="w-full px-3 py-2 mb-3 text-sm leading-tight text-gray-700 border border-red-500 rounded shadow appearance-none focus:outline-none focus:shadow-outline"
                    id="password"
                    type="password"
                    placeholder="******************"
                    value={this.state.password}
                    onChange={this.updateUsername}
                  />
                  <p className="text-xs italic text-red-500">Please choose a password.</p>
                </div>
                <div className="mb-4">
                  <input className="mr-2 leading-tight" type="checkbox" id="checkbox_id"/>
                  <label className="text-sm" htmlFor="checkbox_id">
                    Remember Me
                  </label>
                </div>
                <div className="mb-6 text-center">
                  <button
                    className="w-full px-4 py-2 font-bold text-white bg-blue-500 rounded-full hover:bg-blue-700 focus:outline-none focus:shadow-outline"
                    type="button"
                    onClick={this.handleSubmit}
                  >
                    Sign In
                  </button>
                </div>
                <hr className="mb-6 border-t"/>
                <div className="text-center">
                  <a
                    className="inline-block text-sm text-blue-500 align-baseline hover:text-blue-800"
                    href="./register.html"
                  >
                    Create an Account!
                  </a>
                </div>
                <div className="text-center">
                  <a
                    className="inline-block text-sm text-blue-500 align-baseline hover:text-blue-800"
                    href="./forgot-password.html"
                  >
                    Forgot Password?
                  </a>
                </div>
              </form>
            </div>
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
