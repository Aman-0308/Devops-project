import { Component, OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  stateList: string[] = ["Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Delhi", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jammu Kashmir", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttarakhand", "Uttar Pradesh", "West Bengal"];
  registerForm!: FormGroup;

  constructor(private fb: FormBuilder, private registerService: RegisterService) { }

  ngOnInit() {
    this.registerForm = this.fb.group({
      fullName: ['', [Validators.required, Validators.pattern('^[a-zA-Z]+( [a-zA-Z]+)*$')]],
      emailId: ['', [Validators.required, Validators.email]],
      phoneNo: ['', [Validators.required, Validators.pattern('^[6-9]\d{9}$')]],
      password: ['', [Validators.required, Validators.minLength(7), Validators.maxLength(20),
        CustomValidators.patternValidator(/(?=.*[A-Z])/, { hasUpperCase: true }),
        CustomValidators.patternValidator(/(?=.*[a-z])/, { hasLowerCase: true }),
        CustomValidators.patternValidator(/(?=.*\d)/, { hasNumber: true }),
        CustomValidators.patternValidator(/(?=.*[@$!%*?&])/, { hasSpecialCharacter: true })
      ]],
      dateOfBirth: ['', [Validators.required, CustomValidators.validateDateOfBirth]],
      gender: ['', Validators.required],
      addressName: ['', Validators.required],
      addressLine1: ['', Validators.required],
      addressLine2: ['', Validators.required],
      area: ['', Validators.required],
      city: ['', Validators.required],
      state: ['', Validators.required],
      pincode: ['', [Validators.required, Validators.pattern('^\d{6}$')]]
    });
  }

  registerCustomer() {
    if (this.registerForm.valid) {
      this.registerService.registerCustomer(this.registerForm.value).subscribe(
        response => {
          this.successMessage = 'Registration successful!';
          this.errorMessage = '';
        },
        error => {
          this.errorMessage = error.error.message || 'Registration failed. Please try again.';
          this.successMessage = '';
        }
      );
    } else {
      this.errorMessage = 'Please fill all required fields correctly.';
      this.successMessage = '';
    }
  }

}
