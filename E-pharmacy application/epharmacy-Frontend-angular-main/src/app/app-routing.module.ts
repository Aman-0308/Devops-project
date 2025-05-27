import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { CustomerCartComponent } from './customer-cart/customer-cart.component';
import { CustomerComponent } from './customer/customer.component';
import { LoginComponent } from './login/login.component';
import { MedicineComponent } from './medicine/medicine.component';
import { PlaceOrderComponent } from './place-order/place-order.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  { path : '', redirectTo: 'medicine', pathMatch: "full" },
  { path : 'medicine', component: MedicineComponent },
  { path : 'login', component:LoginComponent},
  { path : 'register', component:RegisterComponent},
  { path : 'customerCart', component:CustomerCartComponent},
  { path : 'placeOrder', component:PlaceOrderComponent},
  { path : 'customerDetails', component:CustomerComponent},
  { path : 'changePassword', component:ChangePasswordComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
