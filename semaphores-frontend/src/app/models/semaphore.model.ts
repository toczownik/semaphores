import {Warehouse} from "./warehouse.model";

export class Semaphore {
  id: number;
  x: number;
  y: number;
  width: number;
  height: number;
  warehouse: Warehouse;

  constructor(x: number, y: number, width: number, height: number) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
}
