import { createModel } from "@rematch/core";
import axios from 'axios';
import Cookies from 'js-cookie';

export type AuthenticationState = any;
export const authentication = createModel<AuthenticationState>({
  state: {
    token: '',
  },
  reducers: {
    setJwtToken: (state: AuthenticationState, payload: any) => {
      Cookies.set('jwtToken', payload.token);
      return {
        token: payload.token,
      }
    }
  },
  effects: dispatch => ({
    async login(payload) {
      const response = await axios.post('http://localhost:8081/authenticate', payload);
      dispatch.authentication.setJwtToken(response.data);
    }
  })
});
