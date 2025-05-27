package com.infy.dto;
//Write the necessary annotations to validate the fields 
public class OrderedMedicineDTO {
	private Integer orderedMedicineId;
	private Integer orderedQuantity;
	private Double orderSubtotal;
	private MedicineDTO medicine;
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
	public MedicineDTO getMedicine() {
		return medicine;
	}
	public void setMedicine(MedicineDTO medicine) {
		this.medicine = medicine;
	}



}
