import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Employee } from '../model/employee';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';


@Injectable()
export class EmployeeService {
  private employeesUrl: string;

  constructor(private http: HttpClient) {
    this.employeesUrl = 'http://localhost:8080/employees';
  }

  public findAll(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.employeesUrl);
  }

  public saveEmployee(employeeData: any): Observable<any> {
    return this.http.post(this.employeesUrl, employeeData).pipe(
      catchError((error: any) => {
        if (error instanceof HttpErrorResponse && error.status === 400) {
          return throwError(error.error);
        } else {
          return throwError('Saving error.');
        }
      })
    );
  }

  public getEmployeeById(employeeId: number): Observable<Employee> {
    const url = `${this.employeesUrl}/${employeeId}`;
    return this.http.get<Employee>(url);
  }

  public updateEmployee(employeeData: any): Observable<any> {
    return this.http.put(this.employeesUrl, employeeData).pipe(
      catchError((error: any) => {
        if (error instanceof HttpErrorResponse && error.status === 400) {
          return throwError(error.error);
        } else {
          return throwError('Update error.');
        }
      })
    );
  }

  public deleteEmployee(employeeId: number): Observable<any> {
    const url = `${this.employeesUrl}/${employeeId}`;
    return this.http.delete(url);
  }
}
