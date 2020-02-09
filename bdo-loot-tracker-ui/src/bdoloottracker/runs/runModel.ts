import { createModel } from '@rematch/core';
import axios from 'axios';
import { SaveRunPayload } from './model';

export interface RunState {
  runs: [];
}

export const runs = createModel<RunState>({
  state: [],
  reducers: {
    setRuns: (state: RunState, runs: any) => {
      return {
        ...state,
        runs,
      };
    }
  },
  effects: dispatch => ({
    async saveRun(payload: SaveRunPayload) {
      const response = await axios.post('/api/run/runs', payload);
    },
    async getRunsForSession(payload: any) {
      const response = await axios.get(`/api/run/runs/by-session/${payload}`);
      dispatch.runs.setRuns(response.data);
    },
  })
});
