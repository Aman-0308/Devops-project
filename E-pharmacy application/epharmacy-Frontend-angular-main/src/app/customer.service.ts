import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { CustomerCart } from './models/customerCart';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  
  constructor(private http:HttpClient) { }
  private cartList = new Subject<CustomerCart[]>();
  updatedCartList = this.cartList.asObservable();

  updateCartList(cartList: CustomerCart[]) {
    this.cartList.next(cartList);
  }

  getCustomerCart(customerId: any) {
    let url: string = environment.getCustomerCartUrl + customerId + '/medicines';
    return this.http.get<CustomerCart[]>(url);
  }
}
