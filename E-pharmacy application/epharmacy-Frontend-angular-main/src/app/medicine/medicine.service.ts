import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CustomerCart } from '../models/customerCart';
import { Medicine } from '../models/medicine';

@Injectable({
  providedIn: 'root'
})
export class MedicineService {

  constructor(private http: HttpClient) { }

  getAllMedicines(pageNumber:number): Observable<Medicine[]> {
    return this.http.get<Medicine[]>(environment.getAllMedicinesUrl + pageNumber +"/pageSize/100");
  }


}
