import { Component, OnInit } from '@angular/core';
import {ForkliftService} from "../services/forklift.service";
import {Forklift} from "../models/forklift.model";
import {Semaphore} from "../models/semaphore.model";
import {SemaphoreService} from "../services/semaphore.service";

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
  providers: [ForkliftService]
})

export class MapComponent implements OnInit {
  forkliftList: Forklift[];
  semaphoreList: Semaphore[];

  constructor(private forkliftService: ForkliftService, private semaphoreService: SemaphoreService) {
    this.forkliftList = [];
    this.semaphoreList = [];
  }

  getForklifts(): void {
    this.forkliftService.getForklifts().subscribe(forkliftList => this.forkliftList = forkliftList);
  }

  getSemaphores(): void {
    this.semaphoreService.getSemaphores('1').subscribe(semaphoreList => this.semaphoreList = semaphoreList);
  }

  ngOnInit(): void {
    this.getForklifts();
    this.getSemaphores();
  }

}
