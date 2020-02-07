import { createModel } from '@rematch/core';
import axios from 'axios';
import { Session } from './model';

export type SessionState = Session[];
export const sessions = createModel<SessionState>({
  state: [],
  reducers: {
    setSessions: (state: SessionState, sessions: Session[]) => {
      return {
        ...state,
        sessions,
      };
    },
    setCurrentSession: (state: SessionState, currentSession: Session) => {
      return {
        ...state,
        currentSession,
      };
    },
  },
  effects: dispatch => ({
    async getSessions() {
      const response = await axios.get('/api/run/sessions');
      dispatch.spots.setSessions(response.data);
    },
    async createSession(payload) {
      console.log(payload);
      const response = await axios.post('/api/run/sessions', payload);
      dispatch.sessions.setCurrentSession(response.data);
    },
  })
});
