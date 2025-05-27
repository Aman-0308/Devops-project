import { CustomerCard } from "./card";
import { Customer } from "./customer";
import { CustomerAddress } from "./customerAddress";
import { DeliveryStatus } from "./deliveryStatus";
import { OrderedMedicine } from "./orderMedicine";
import { OrderStatus } from "./orderStatus";

export class Order {
    orderId!: number;
    cancelReason!:string;
    discountPercent!:number;
    orderValueBeforeDiscount!:number;
    orderValueAfterDiscount!:number;
    customer!: Customer;
    orderstatus!: OrderStatus;
    orderDate!: Date;
    orderedMedicines!: OrderedMedicine[];
    deliveryAddress!: CustomerAddress;
    deliveryStatus!:DeliveryStatus;
    deliveryDate!:Date;
    card!:CustomerCard;
}