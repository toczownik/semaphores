import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Warehouse} from "../models/warehouse.model";
import {catchError, tap} from "rxjs/operators";
import {Forklift} from "../models/forklift.model";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class WarehouseService {
  private warehouseUrl = 'http://localhost:8080/restApi/warehouse';

  constructor(private http: HttpClient) { }

  getWarehouse(id: number): Observable<Warehouse> {
    const url = `${this.warehouseUrl}/${id}`;
    return this.http.get<Warehouse>(url).pipe(
      tap(_ => this.log(`deleted warehouse id=${id}`)),
      catchError(this.handleError<Warehouse>('deletewarehouse'))
    )
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    }
  }

  private log(message: string) {
    console.log('ForkliftService' + message);
  }
}
