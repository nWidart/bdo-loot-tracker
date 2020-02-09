export interface SaveRunPayload {
  sessionId: string,
  comment: string,
  itemDrops: ItemDrop[],
}

export interface ItemDrop {
  itemId: number;
  total: number;
}

export interface RunProjection {
  runId: number;
  createdAt: string;
  comment: string;
  runDrops: RunDrop[];
  totalSilver: number;
  totalAfterTax: number;
}

export interface RunDrop {
  currentPrice: number;
  total: number;
  itemName: string;
  itemId: number;
}
