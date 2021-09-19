import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Semaphore} from "../models/semaphore.model";
import {Observable, of} from "rxjs";
import {catchError, tap} from "rxjs/operators";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class SemaphoreService {
  private semaphoresUrl = 'http://localhost:8080/restApi/semaphores';

  constructor(private http: HttpClient) {
  }

  getSemaphores(warehouseId: string): Observable<Semaphore[]> {
    const url = `${this.semaphoresUrl}/${warehouseId}`;
    return this.http.get<Semaphore[]>(url).pipe(
      tap(_ => this.log(`fetched semaphores for warehouse id=${warehouseId}`)),
      catchError(this.handleError<Semaphore[]>('getSemaphores'))
    );
  }

  getAllSemaphores(): Observable<Semaphore[]> {
    return this.http.get<Semaphore[]>(this.semaphoresUrl);
  }

  addSemaphore(semaphore: Semaphore): Observable<Semaphore> {
    return this.http.post<Semaphore>(this.semaphoresUrl, semaphore, httpOptions).pipe(
      tap((semaphoreAdded: Semaphore) => this.log(`added semaphore id=${semaphoreAdded.id}`)),
      catchError(this.handleError<Semaphore>('addSemaphore'))
    )
  }

  deleteSemaphore(semaphore: Semaphore): Observable<Semaphore> {
    const url = `${this.semaphoresUrl}/${semaphore.id}`;
    return this.http.delete<Semaphore>(url, httpOptions).pipe(
      tap(_ => this.log(`deleted semaphore id=${semaphore.id}`)),
      catchError(this.handleError<Semaphore>('deleteSemaphore'))
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
