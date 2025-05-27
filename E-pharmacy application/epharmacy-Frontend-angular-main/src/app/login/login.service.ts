import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Customer } from '../models/customer';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) { }

  loginCustomer(data:any):Observable<Customer>{
    var customer:Customer = new Customer(); 
    customer.customerEmailId = data.emailId;
    customer.password = data.password;
    return this.http.post<Customer>(environment.LoginUrl, customer)
  }



}
