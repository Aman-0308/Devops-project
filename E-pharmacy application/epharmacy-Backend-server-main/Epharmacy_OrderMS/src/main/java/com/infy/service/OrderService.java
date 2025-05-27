package com.infy.service;
import java.util.List;
import com.infy.dto.OrderDTO;
import com.infy.exception.EPharmacyException;


public interface OrderService {
	List<OrderDTO> viewOrders(Integer customerId) throws EPharmacyException;
	public String placeOrder(OrderDTO orderDTO) throws EPharmacyException;
	public void cancelOrder(Integer orderId,String reason) throws EPharmacyException;
}
