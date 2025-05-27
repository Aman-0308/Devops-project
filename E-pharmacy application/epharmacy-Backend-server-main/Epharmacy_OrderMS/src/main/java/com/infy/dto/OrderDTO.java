package com.infy.dto;

import java.time.LocalDateTime;
import java.util.List;
//Write the necessary annotations to validate the fields 
public class OrderDTO {
	private Integer orderId;
	private Double orderValueBeforeDiscount;
	private Double discountPercent;
	private Double orderValueAfterDiscount;
	private LocalDateTime deliveryDate;
	private CustomerDTO customer;
	private OrderStatus orderStatus;
	private LocalDateTime orderDate;
	private String cancelReason;
	private List<OrderedMedicineDTO> orderedMedicines;
	private CustomerAddressDTO deliveryAddress;
	private CardDTO card;
	private DeliveryStatus deliveryStatus;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Double getOrderValueBeforeDiscount() {
		return orderValueBeforeDiscount;
	}
	public void setOrderValueBeforeDiscount(Double orderValueBeforeDiscount) {
		this.orderValueBeforeDiscount = orderValueBeforeDiscount;
	}
	public Double getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}
	public Double getOrderValueAfterDiscount() {
		return orderValueAfterDiscount;
	}
	public void setOrderValueAfterDiscount(Double orderValueAfterDiscount) {
		this.orderValueAfterDiscount = orderValueAfterDiscount;
	}
	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	public CustomerDTO getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public List<OrderedMedicineDTO> getOrderedMedicines() {
		return orderedMedicines;
	}
	public void setOrderedMedicines(List<OrderedMedicineDTO> orderedMedicines) {
		this.orderedMedicines = orderedMedicines;
	}

	public CustomerAddressDTO getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(CustomerAddressDTO deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public CardDTO getCard() {
		return card;
	}
	public void setCard(CardDTO card) {
		this.card = card;
	}
	public DeliveryStatus getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

}
