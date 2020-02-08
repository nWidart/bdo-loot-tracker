import { createModel } from '@rematch/core';
import axios from 'axios';
import { Session } from './model';

interface SessionState {
  sessions: Session[];
  currentSession: Session;
}
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
      dispatch.sessions.setSessions(response.data);
    },
    async createSession(payload) {
      const response = await axios.post('/api/run/sessions', payload);
      dispatch.sessions.setCurrentSession(response.data);
    },
    async findSessionById(sessionId) {
      const response = await axios.get(`/api/run/sessions/${sessionId}`);
      return dispatch.sessions.setCurrentSession(response.data);
    }
  })
});
