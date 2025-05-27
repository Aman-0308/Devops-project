import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from 'src/environments/environment';
import { Customer } from '../models/customer';
import { CustomerAddress } from '../models/customerAddress';
import { Pharmacist } from '../models/pharmacist';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  constructor(private http: HttpClient) { }

  registerCustomer(data: any): Observable<string> {
    var cust: Customer = new Customer();
    cust.contactNumber = data.phoneNo;
    cust.customerEmailId = data.emailId;
    cust.customerName = data.fullName;
    cust.password = data.password;
    cust.gender = data.gender;
    cust.dateOfBirth = data.dateOfBirth;
    if (data.state != '') {
      var address: CustomerAddress = new CustomerAddress();
      address.state = data.state;
      address.addressLine1 = data.addressLine1;
      address.addressLine2 = data.addressLine2;
      address.area = data.area;
      address.city = data.city;
      address.pincode = data.pincode;
      address.addressName = data.addressName;

      cust.addressList.push(address);

    }
    return this.http.post(environment.registerCustomerUrl, cust, { responseType: "text" })
  }

}
