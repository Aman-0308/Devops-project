import { Component, OnInit } from '@angular/core';
import { CustomerCart } from '../models/customerCart';
import { Medicine } from '../models/medicine';
import { AuthService } from '../service/auth.service';
import { MedicineService } from './medicine.service';

@Component({
  selector: 'app-medicine',
  templateUrl: './medicine.component.html',
  styleUrls: ['./medicine.component.css']
})
export class MedicineComponent implements OnInit {

  medicineListToDisplay: Medicine[] = [];

  constructor(private medicineService: MedicineService, private auth:AuthService) { }

  medicineList!: Medicine[];
  errorMessage!: string;
  successMessage!: string;
  searchText!: string;
  category!:string;
  viewDetails: boolean = false;
  selectedMedicine!: Medicine;
  actualPrice!:number;
  loggedIn:boolean = false;
  id!:string|null;
  pageNumber:number=0;
  medicineNames!: string[];
  displayLength=0;
  
  startIndex = 0;
  endIndex = 29;

  getMedicineListToDisplay(): Medicine[] {
    this.displayLength = this.medicineListToDisplay.length-1;
    if(this.medicineListToDisplay.length >= this.endIndex+1){
      return this.medicineListToDisplay.slice(this.startIndex, this.endIndex + 1);
    }
    return this.medicineListToDisplay.slice(this.startIndex, this.medicineListToDisplay.length);
  }

  onNext(): void {
    this.startIndex = this.endIndex + 1;
    this.endIndex = this.startIndex + 29;
  }

  onPrevious(): void {
    this.startIndex = this.startIndex - 30;
    this.endIndex = this.endIndex - 30;
  }


  ngOnInit(): void {
    this.id = sessionStorage.getItem("customerId");
    if(this.id!=null || this.id!=undefined){
      this.loggedIn=true;
    }
    this.getAllMedicines()
  }
  getAllMedicines() {
    this.medicineService.getAllMedicines(this.pageNumber).subscribe(
      medicines => {
        this.medicineList = medicines.map(medicine => {
          const expiryDate = new Date(medicine.expiryDate);
          const currentDate = new Date();
          const threeMonthsLater = new Date(currentDate);
          threeMonthsLater.setMonth(currentDate.getMonth() + 3);
          const sixMonthsLater = new Date(currentDate);
          sixMonthsLater.setMonth(currentDate.getMonth() + 6);

          if (expiryDate <= threeMonthsLater) {
            medicine.discountPercent = 30;
          } else if (expiryDate <= sixMonthsLater) {
            medicine.discountPercent = 20;
          }
          return medicine;
        });
        this.medicineListToDisplay = this.medicineList;
        this.medicineNames = this.medicineList.map(medicine => medicine.medicineName);
      },
      errorResponse => {
        this.errorMessage = errorResponse.error.message;
      }
    );
  }

  search() {
    if (this.searchText) {
      this.medicineListToDisplay = this.medicineList.filter(medicine =>
        medicine.medicineName.toLowerCase().includes(this.searchText.toLowerCase())
      );
      if (this.medicineListToDisplay.length === 0) {
        this.errorMessage = 'No medicines found matching your search.';
      } else {
        this.errorMessage = '';
      }
    } else {
      this.medicineListToDisplay = this.medicineList;
      this.errorMessage = '';
    }
  }

  categorise() {
    if (this.category) {
      this.medicineListToDisplay = this.medicineList.filter(medicine =>
        medicine.category.toLowerCase() === this.category.toLowerCase()
      );
      if (this.medicineListToDisplay.length === 0) {
        this.errorMessage = `No medicines found in the category: ${this.category}.`;
      } else {
        this.errorMessage = '';
      }
    } else {
      this.medicineListToDisplay = this.medicineList;
      this.errorMessage = '';
    }
  }

  setSelectedMedicine(medicine: Medicine) {
    this.viewDetails = true;
    this.selectedMedicine = medicine;
    this.actualPrice = (this.selectedMedicine.price * 100) / (100 - this.selectedMedicine.discountPercent);
    
  }

  addToCart() {
    if (!this.loggedIn) {
      this.errorMessage = 'Please log in to add items to the cart.';
      return;
    }

    const cartItem = {
      medicine: this.selectedMedicine,
      quantity: 1
    };

    this.customerService.getCustomerCart(this.id).subscribe(
      cart => {
        const existingItem = cart.find(item => item.medicine.medicineId === this.selectedMedicine.medicineId);
        if (existingItem) {
          this.errorMessage = 'This item is already in your cart.';
        } else {
          cart.push(cartItem);
          this.customerService.updateCartList(cart);
          this.successMessage = 'Item added to cart successfully!';
        }
      },
      error => {
        this.errorMessage = 'Failed to add item to cart. Please try again.';
      }
    );
  }

  sortLowToHigh() {
    this.medicineListToDisplay.sort((a, b) => a.price - b.price);
  }

  sortHighToLow() {
    this.medicineListToDisplay.sort((a, b) => b.price - a.price);
  }

  sortAtoZ() {
    this.medicineListToDisplay.sort((a, b) => a.medicineName.localeCompare(b.medicineName));
  }

  sortZtoA() {
    this.medicineListToDisplay.sort((a, b) => b.medicineName.localeCompare(a.medicineName));
  }

  goBack(){
    this.viewDetails = false;
    this.successMessage = "";
    this.errorMessage = "";
  }
  clear() {
    this.medicineListToDisplay = this.medicineList;
    this.searchText = "";
  }

}
