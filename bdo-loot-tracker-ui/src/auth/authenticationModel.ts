import { createModel } from '@rematch/core';
import axios from 'axios';
import Cookies from 'js-cookie';

export type AuthenticationState = any;
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
    }
  },
  effects: dispatch => ({
    async login(payload) {
      const response = await axios.post('http://localhost:8081/authenticate', payload);
      dispatch.authentication.setJwtToken(response.data);
    },
    async getCurrentUser() {
      const response = await axios.get('http://localhost:8081/api/me');
      dispatch.authentication.setCurrentUser(response.data);
    }
  })
});
