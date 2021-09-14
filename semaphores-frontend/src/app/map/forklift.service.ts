import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Forklift} from "./forklift.model";
import {catchError, tap} from "rxjs/operators";
import {Observable, of} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class ForkliftService {
  private forkliftsUrl = 'http://localhost:8080/restApi/forklifts'
  public x = 10;

  constructor(private http: HttpClient) { }

  getForklift(id: string): Observable<Forklift> {
    const url = `${this.forkliftsUrl}/${id}`;
    return this.http.get<Forklift>(url).pipe(
      tap(_ => this.log(`fetched concert id=${id}`)),
      catchError(this.handleError<Forklift>(`getForklift id=${id}`))
    );
  }

  getForklifts(): Observable<Forklift[]> {
    return this.http.get<Forklift[]>(this.forkliftsUrl);
  }

  deleteForklift(forklift: Forklift | number): Observable<Forklift> {
    const id = typeof forklift === 'number' ? forklift : forklift.id;
    const url = `${this.forkliftsUrl}/${id}`;
    return this.http.delete<Forklift>(url, httpOptions).pipe(
      tap(_ => this.log(`deleted contact id=${id}`)),
      catchError(this.handleError<Forklift>('deleteContact'))
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
