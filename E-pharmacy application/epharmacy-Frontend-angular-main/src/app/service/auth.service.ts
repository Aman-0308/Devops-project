import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Customer } from '../models/customer';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    sessionCustomer: BehaviorSubject<Customer> = new BehaviorSubject<Customer>(new Customer());
    customer!: Customer;

    constructor(private http: HttpClient) {
        this.loadSessionCustomer();
    }

    loadSessionCustomer() {
        this.customer = new Customer();
        this.customer.customerName = "";
    }

    nextCustomer(data: Customer) {
        this.customer = data;
        this.sessionCustomer.next(this.customer);
    }
 

}
