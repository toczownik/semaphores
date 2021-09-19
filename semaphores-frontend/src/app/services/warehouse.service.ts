import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Warehouse} from "../models/warehouse.model";
import {catchError, tap} from "rxjs/operators";
import {Forklift} from "../models/forklift.model";
import {Semaphore} from "../models/semaphore.model";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class WarehouseService {
  private warehouseUrl = 'http://localhost:8080/restApi/warehouses';

  constructor(private http: HttpClient) { }

  getWarehouse(id: number): Observable<Warehouse> {
    const url = `${this.warehouseUrl}/${id}`;
    return this.http.get<Warehouse>(url).pipe(
      tap(_ => this.log(`fetched warehouse id=${id}`)),
      catchError(this.handleError<Warehouse>('deleteWarehouse'))
    )
  }

  getWarehouses(): Observable<Warehouse[]> {
    return this.http.get<Warehouse[]>(this.warehouseUrl).pipe(
      tap(_ => this.log(`fetched warehouses`)),
      catchError(this.handleError<Warehouse[]>('getWarehouse'))
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
