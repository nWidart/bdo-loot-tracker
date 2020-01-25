import { createModel } from "@rematch/core";
import axios from 'axios';
import { Spot } from './model';

export type SpotState = Spot[];
export const spots = createModel<SpotState>({
  state: [],
  reducers: {
    setSpots: (state: SpotState, spots: Spot[]) => {
      return {
        spots,
      }
    }
  },
  effects: dispatch => ({
    async getSpots() {
      const response = await axios.get('/api/spot/spots');
      dispatch.spots.setSpots(response.data);
    }
  })
});
