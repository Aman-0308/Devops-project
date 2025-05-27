import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CustomerHomeService {

  constructor(private http: HttpClient) { }

  // Fetch customer profile details
  getProfile(customerId: string): Observable<any> {
    return this.http.get(`${environment.customerLoginUrl}/${customerId}`);
  }

  // Update customer profile details
  updateProfile(customerId: string, profileData: any): Observable<any> {
    return this.http.put(`${environment.customerLoginUrl}/${customerId}`, profileData);
  }

  // Upgrade membership
  upgradeMembership(customerId: string, plan: string): Observable<any> {
    return this.http.post(`${environment.customerLoginUrl}/${customerId}/upgrade`, { plan });
  }

  // Change password
  changePassword(customerId: string, currentPassword: string, newPassword: string): Observable<any> {
    return this.http.post(`${environment.customerLoginUrl}/${customerId}/change-password`, {
      currentPassword,
      newPassword
    });
  }
}
