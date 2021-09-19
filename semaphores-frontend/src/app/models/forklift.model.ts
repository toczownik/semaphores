import {Warehouse} from "./warehouse.model";

export class Forklift {
  serialNumber: number;
  x: number;
  y: number;
  warehouse: Warehouse;

  constructor(serialNumber: number, x: number, y: number, warehouse: Warehouse) {
    this.serialNumber = serialNumber;
    this.x = x;
    this.y = y;
    this.warehouse = warehouse;
  }
}
