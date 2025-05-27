import { CardType } from "./cardType";
import { Customer } from "./customer";

export class CustomerCard {
    cardId!: string;
    nameOnCard!: string;
    cvv!: string;
    expiryDate!: Date;
    cardType!: CardType;
    customer!: Customer;
}