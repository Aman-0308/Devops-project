import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-place-order',
  templateUrl: './place-order.component.html',
  styleUrls: ['./place-order.component.css']
})
export class PlaceOrderComponent implements OnInit {

  loggedInCustomerId!: string | null;
  selectedAddress: any;
  selectedCard: any;
  cvv: string = '';
  errorMessage: string = '';
  successMessage: string = '';

  constructor() { }

  ngOnInit(): void {
    this.loggedInCustomerId = sessionStorage.getItem("customerId");
  }

  makePayment() {
    if (!this.selectedAddress || !this.selectedCard) {
      this.errorMessage = 'Please select a shipping address and payment method.';
      return;
    }

    const paymentRequest = {
      customerId: this.loggedInCustomerId,
      addressId: this.selectedAddress.addressId,
      cardId: this.selectedCard.cardId,
      cvv: this.cvv
    };

    this.placeOrderService.placeOrder(paymentRequest).subscribe(
      response => {
        this.successMessage = 'Order placed successfully! Expected delivery: ' + response.deliveryDate;
        this.errorMessage = '';
      },
      error => {
        this.errorMessage = error.error.message || 'Failed to place order. Please try again.';
        this.successMessage = '';
      }
    );
  }

}
