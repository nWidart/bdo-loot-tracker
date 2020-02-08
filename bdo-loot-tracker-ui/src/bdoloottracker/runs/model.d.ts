export interface SaveRunPayload {
  sessionId: string,
  comment: string,
  itemDrops: ItemDrop[],
}

export interface ItemDrop {
  itemId: number;
  total: number;
}
