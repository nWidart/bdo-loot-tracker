export interface LootTable {
  items: SimpleItem[],
}

export interface SimpleItem {
  itemId: number;
  name: string;
}
