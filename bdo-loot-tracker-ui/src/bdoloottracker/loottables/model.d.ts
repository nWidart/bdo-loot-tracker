import { Spot } from '../spots/model';
import { Item } from '../item/item';

export interface LootTable {
  id: number;
  item: Item;
  spot: Spot;
}
