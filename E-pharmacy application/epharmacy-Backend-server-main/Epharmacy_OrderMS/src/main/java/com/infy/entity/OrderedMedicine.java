package com.infy.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderedMedicine {
	@Id
	private Integer orderedMedicineId;
	private Integer orderedQuantity;
	private Double orderSubtotal;
	private Integer medicineId;
	public Integer getOrderedMedicineId() {
		return orderedMedicineId;
	}
	public void setOrderedMedicineId(Integer orderedMedicineId) {
		this.orderedMedicineId = orderedMedicineId;
	}
	public Integer getOrderedQuantity() {
		return orderedQuantity;
	}
	public void setOrderedQuantity(Integer orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}
	public Double getOrderSubtotal() {
		return orderSubtotal;
	}
	public void setOrderSubtotal(Double orderSubtotal) {
		this.orderSubtotal = orderSubtotal;
	}
	public Integer getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	

}
