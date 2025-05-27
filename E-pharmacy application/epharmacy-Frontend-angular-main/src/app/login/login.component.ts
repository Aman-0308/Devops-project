import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { LoginService } from './login.service';
import { interval, Subscription } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  errorMessage: string = "";
  successMessage: string = "";
  failedAttempts: number = 0;
  circuitOpen: boolean = false;
  timerSubscription!: Subscription;
  remainingTime: number = 60;

  constructor(private fb: FormBuilder, private router: Router, private loginService: LoginService, private auth: AuthService) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      emailId: ['',[Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(7), Validators.maxLength(20)]],
      userRole: ['', [Validators.required]]
    });
  }

  customerLogin() {
    if (this.circuitOpen) {
      this.errorMessage = `Circuit is open. Please wait ${this.remainingTime} seconds.`;
      return;
    }

    this.loginService.loginCustomer(this.loginForm.value).subscribe(
      (response) => {
        this.auth.nextCustomer(response);
        sessionStorage.setItem("customerId", "" + response.customerId);
        this.router.navigate(['/']); // Redirect to home page
        this.failedAttempts = 0; // Reset failed attempts on success
      },
      (errorResponse) => {
        this.failedAttempts++;
        this.errorMessage = errorResponse.error.message || 'Invalid credentials. Please try again.';
        sessionStorage.clear();

        if (this.failedAttempts >= 3) {
          this.openCircuit();
        }
      }
    );
  }

  openCircuit() {
    this.circuitOpen = true;
    this.remainingTime = 60;
    this.timerSubscription = interval(1000).subscribe(() => {
      this.remainingTime--;
      if (this.remainingTime <= 0) {
        this.closeCircuit();
      }
    });
  }

  closeCircuit() {
    this.circuitOpen = false;
    this.failedAttempts = 0;
    this.errorMessage = "";
    if (this.timerSubscription) {
      this.timerSubscription.unsubscribe();
    }
  }

  getRegisterPage() {
    this.router.navigate(['/', 'register']);
  }
}
