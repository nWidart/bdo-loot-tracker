import { createModel } from '@rematch/core';
import axios from 'axios';
import { SaveRunPayload } from './model';

export interface RunState {
  runs: [];
}

export const runs = createModel<RunState>({
  state: [],
  reducers: {},
  effects: dispatch => ({
    async saveRun(payload: SaveRunPayload) {
      const response = await axios.post('/api/run/runs', payload);
    }
  })
});
