import { Component, OnInit } from '@angular/core';
import { CustomerCart } from '../models/customerCart';

@Component({
  selector: 'app-customer-cart',
  templateUrl: './customer-cart.component.html',
  styleUrls: ['./customer-cart.component.css']
})
export class CustomerCartComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  getCustomerCart() {
    this.customerService.getCustomerCart(this.loggedInCustomerId).subscribe(
      cart => {
        this.cart = cart;
        sessionStorage.setItem("cart", JSON.stringify(this.cart));
        this.customerService.updateCartList(this.cart);
      },
      err => {
        this.cart = [];
        this.customerService.updateCartList(this.cart);
        this.customerService.updatedCartList.subscribe(cartList => this.cart = cartList);
      }
    );
  }

  updateQuantity(operation: string, cart: CustomerCart) {
    if (operation === 'increase') {
      cart.quantity++;
    } else if (operation === 'decrease' && cart.quantity > 1) {
      cart.quantity--;
    }
    this.customerService.updateCartList(this.cart);
  }

  deleteMedicineFromCart(cart: CustomerCart) {
    const index = this.cart.indexOf(cart);
    if (index > -1) {
      this.cart.splice(index, 1);
    }
    this.customerService.updateCartList(this.cart);
  }

  deleteAllMedicinesFromCart() {
    this.cart = [];
    this.customerService.updateCartList(this.cart);
  }

}
