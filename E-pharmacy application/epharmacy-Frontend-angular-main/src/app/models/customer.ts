import { CustomerCard } from "./card";
import { CustomerAddress } from "./customerAddress";
import { PrimePlans } from "./plans";

export class Customer {
    customerId!: number;
    customerName!: string;
    customerEmailId!: string;
    contactNumber!: string;
    password!: string;
    gender!:string;
    dateOfBirth!:Date;
    addressList: Array<CustomerAddress> = [];
    plan!: PrimePlans ;
    planExpiryDate!:Date;
    healthCoins!:number;
}