import { createModel } from '@rematch/core';
import axios from 'axios';
import { LootTable } from './model';

export interface LootTableState {
  lootTable: LootTable;
}

export const lootTables = createModel<LootTableState>({
  state: [],
  reducers: {
    setLootTable: (state: LootTableState, lootTable: LootTable) => {
      return {
        lootTable,
      };
    }
  },
  effects: dispatch => ({
    async getLootTableForSpot(spotId: any) {
      const response = await axios.get(`${process.env.REACT_APP_API_GATEWAY_BASE_URL}/api/loot-table/loot-tables?spotId=${spotId}`);
      dispatch.lootTables.setLootTable(response.data);
    }
  })
});
