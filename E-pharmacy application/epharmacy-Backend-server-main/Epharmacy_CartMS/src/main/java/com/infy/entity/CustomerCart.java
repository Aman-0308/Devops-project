package com.infy.entity;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER_CART")
public class CustomerCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID")
    private Integer cartId;

    @Column(name = "CUSTOMER_ID")
    private Integer customerId;

    @Column(name = "MEDICINE_ID")
    private Integer medicineId;

    @Column(name = "QUANTITY")
    private Integer quantity;

    // Getters and Setters
    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}





































































//package com.infy.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "CUSTOMER_CART")
//public class CustomerCart {
//
//	@Id
//	@Column(name = "CART_ID")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer cartId;
//	private Integer customerId;
//	private Integer medicineId;
//	private Integer quantity;
//
//	public Integer getCartId() {
//		return cartId;
//	}
//
//	public void setCartId(Integer cartId) {
//		this.cartId = cartId;
//	}
//
//	public Integer getCustomerId() {
//		return customerId;
//	}
//
//	public void setCustomerId(Integer customerId) {
//		this.customerId = customerId;
//	}
//
//	public Integer getMedicineId() {
//		return medicineId;
//	}
//
//	public void setMedicineId(Integer medicineId) {
//		this.medicineId = medicineId;
//	}
//
//	public Integer getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(Integer quantity) {
//		this.quantity = quantity;
//	}
//}