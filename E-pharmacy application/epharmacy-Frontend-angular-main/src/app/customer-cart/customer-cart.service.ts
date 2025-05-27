import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

interface CartItem {
  cartId?: number;
  customerId: number;
  medicine: {
    medicineId: number;
    name: string;
    price: number;
  };
  quantity: number;
}

@Injectable({
  providedIn: 'root'
})
export class CustomerCartService {

  constructor(private http: HttpClient) { }

  // Add medicine to cart
  addToCart(customerId: number, medicineId: number, quantity: number): Observable<void> {
    const payload = { quantity };
    return this.http.post<void>(`${environment.getCustomerCartUrl}/cart/${customerId}/${medicineId}`, payload);
  }

  // View cart contents
  getCart(customerId: number): Observable<CartItem[]> {
    return this.http.get<CartItem[]>(`${environment.getCustomerCartUrl}/cart/${customerId}`);
  }

  // Update quantity of an item in the cart
  updateQuantity(customerId: number, medicineId: number, quantity: number): Observable<void> {
    return this.http.put<void>(`${environment.getCustomerCartUrl}/cart/${customerId}/${medicineId}`, { quantity });
  }

  // Delete a specific medicine from the cart
  deleteMedicine(customerId: number, medicineId: number): Observable<void> {
    return this.http.delete<void>(`${environment.getCustomerCartUrl}/cart/${customerId}/${medicineId}`);
  }

  // Delete all medicines from the cart
  deleteAllMedicines(customerId: number): Observable<void> {
    return this.http.delete<void>(`${environment.getCustomerCartUrl}/cart/${customerId}`);
  }
}
