import { ResourceLoader } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerCartService } from './customer-cart/customer-cart.service';
import { CustomerService } from './customer.service';
import { CustomerCart } from './models/customerCart';
import { AuthService } from './service/auth.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  loggedInPerson: string | undefined;
  loggedInCustomerId!:number
  loggedIn: Boolean = false;
  cart: CustomerCart[] = [];
  role!: string;
  constructor(private router: Router, private auth: AuthService, private customerService: CustomerService) { }

  ngOnInit() {
    this.getCustomerCart();
    this.auth.sessionCustomer.subscribe(
      data => {
        this.loggedInPerson = data.customerName;
        this.loggedInCustomerId = data.customerId;
        if (this.loggedInPerson != null && this.loggedInPerson != undefined) {
          this.loggedIn = true;
          this.role = "Customer";
        }
      })
  }

  getCustomerCart() {

    this.customerService.updatedCartList.subscribe(cartList => this.cart = cartList)
    this.customerService.getCustomerCart(this.loggedInCustomerId).subscribe(
      cart => {
        this.cart = cart;
        sessionStorage.setItem("cart", JSON.stringify(this.cart));
        this.customerService.updateCartList(this.cart)
      }, err => {
        this.cart = [];
        this.customerService.updateCartList(this.cart);
        this.customerService.updatedCartList.subscribe(cartList => this.cart = cartList)
      }
    )
  }

  logout() {
    this.loggedIn = false;
    sessionStorage.clear();
    this.router.navigate(["/"]);

  }
}
