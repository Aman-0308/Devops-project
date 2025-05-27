import { Time } from "@angular/common";
import { CustomerCard } from "./card";

export class Payment{
    paymentId!:number;
    paymentTime!:Time;
    amount!:number;
    card!: CustomerCard;
    customerId!:number;

    
}