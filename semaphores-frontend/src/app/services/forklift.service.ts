import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Forklift} from "../models/forklift.model";
import {catchError, tap} from "rxjs/operators";
import {Observable, of} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class ForkliftService {
  private forkliftsUrl = 'http://localhost:8080/restApi/forklifts';

  constructor(private http: HttpClient) { }

  getForklift(serialNumber: string): Observable<Forklift> {
    const url = `${this.forkliftsUrl}/${serialNumber}`;
    return this.http.get<Forklift>(url).pipe(
      tap(_ => this.log(`fetched concert id=${serialNumber}`)),
      catchError(this.handleError<Forklift>(`getForklift id=${serialNumber}`))
    );
  }

  getForklifts(): Observable<Forklift[]> {
    return this.http.get<Forklift[]>(this.forkliftsUrl);
  }

  addForklift(forklift: Forklift, warehouseId: string): Observable<Forklift> {
    const url = `${this.forkliftsUrl}/${warehouseId}`;
    return this.http.post<Forklift>(url, forklift, httpOptions).pipe(
      tap(forkliftAdded => this.log(`added forklift serial number=${forkliftAdded.serialNumber}`)),
      catchError(this.handleError<Forklift>(`addForklift`))
    )
  }

  deleteForklift(forklift: Forklift | number): Observable<Forklift> {
    const serialNumber = typeof forklift === 'number' ? forklift : forklift.serialNumber;
    const url = `${this.forkliftsUrl}/${serialNumber}`;
    return this.http.delete<Forklift>(url, httpOptions).pipe(
      tap(_ => this.log(`deleted forklift id=${serialNumber}`)),
      catchError(this.handleError<Forklift>('deleteForklift'))
    );
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
