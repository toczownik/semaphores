import { Component, OnInit } from '@angular/core';
import {ForkliftService} from "./forklift.service";
import {Forklift} from "./forklift.model";

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
  providers: [ForkliftService]
})

export class MapComponent implements OnInit {
  forkliftList: Forklift[];


  constructor(private forkliftService: ForkliftService) {
    this.forkliftList = [];
  }

  createChart(): void {

  }

  getForklifts(): void {
    this.forkliftService.getForklifts().subscribe(forkliftList => this.forkliftList = forkliftList);
  }

  delete(forklift: Forklift): void {
    this.forkliftList = this.forkliftList.filter(f => f != forklift);
    this.forkliftService.deleteForklift(forklift).subscribe();
  }


  ngOnInit(): void {
    this.getForklifts();

  }

}
