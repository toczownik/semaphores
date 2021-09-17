import { Component, OnInit } from '@angular/core';
import {Forklift} from "../models/forklift.model";
import {ForkliftService} from "../services/forklift.service";
import {Semaphore} from "../models/semaphore.model";
import {SemaphoreService} from "../services/semaphore.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  forkliftList: Forklift[];
  semaphoreList: Semaphore[];

  constructor(private forkliftService: ForkliftService, private semaphoreService: SemaphoreService) {
    this.forkliftList = [];
    this.semaphoreList = [];
  }

  ngOnInit(): void {
    this.forkliftService.getForklifts().subscribe(forkliftList => this.forkliftList = forkliftList);
    this.semaphoreService.getAllSemaphores().subscribe(semaphoreList => this.semaphoreList = semaphoreList);
  }

  addForklift(serialNumber: string, warehouseId: string): void {
    let forklift = new Forklift(Number(serialNumber), 0, 0);
    this.forkliftService.addForklift(forklift, warehouseId);
  }

  addSemaphore(x: string, y: string, width: string, height: string, warehouseId: string) {
    let semaphore = new Semaphore(Number(x), Number(y), Number(width), Number(height));
    this.semaphoreService.addSemaphore(semaphore, warehouseId);
  }

  deleteForklift(forklift: Forklift): void {
    this.forkliftList = this.forkliftList.filter(f => f != forklift);
    this.forkliftService.deleteForklift(forklift).subscribe();
  }

  deleteSemaphore(semaphore: Semaphore): void {
    this.semaphoreList = this.semaphoreList.filter(s => s != semaphore);
    this.semaphoreService.deleteSemaphore(semaphore).subscribe();
  }

}
