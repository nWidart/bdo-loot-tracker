import { createModel } from '@rematch/core';
import axios from 'axios';
import Cookies from 'js-cookie';

export interface User {
  id: number;
  name: string;
  email: string;
  isAdmin: boolean;
}

export type AuthenticationState = { user: User, token: string };
export const authentication = createModel<AuthenticationState>({
  state: {
    token: '',
    user: undefined,
  },
  reducers: {
    setJwtToken: (state: AuthenticationState, payload: any) => {
      Cookies.set('jwtToken', payload.token);
      return {
        ...state,
        token: payload.token,
      };
    },
    setCurrentUser: (state: AuthenticationState, payload) => {
      return {
        ...state,
        user: payload,
      };
    },
    logout: () => {
      Cookies.remove('jwtToken');
      return {
        token: '',
        user: undefined,
      };
    },
  },
  effects: dispatch => ({
    async login(payload) {
      const response = await axios.post(process.env.REACT_APP_AUTH_BASE_URL + '/authenticate', payload);
      dispatch.authentication.setJwtToken(response.data);
    },
    async getCurrentUser() {
      const response = await axios.get(process.env.REACT_APP_AUTH_BASE_URL + '/api/me');
      dispatch.authentication.setCurrentUser(response.data);
    },
    async checkCookie(payload, rootState) {
      const token = Cookies.get('jwtToken');
      if (token === undefined || token === 'undefined') {
        return;
      }
      if (rootState.authentication.token !== '') {
        return;
      }
      const response = await axios.get(process.env.REACT_APP_AUTH_BASE_URL + '/api/me');
      dispatch.authentication.setCurrentUser(response.data);
      dispatch.authentication.setJwtToken({ token });
    },
  })
});
