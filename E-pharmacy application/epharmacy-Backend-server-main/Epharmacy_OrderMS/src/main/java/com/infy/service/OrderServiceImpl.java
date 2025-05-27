package com.infy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.infy.dto.CustomerDTO;
import com.infy.dto.OrderDTO;
import com.infy.entity.Order;
import com.infy.exception.EPharmacyException;
import com.infy.repository.OrderRepository;
import com.infy.repository.OrderedMedicineRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Value("${PLACE_ORDER_URL}")
    private String placeOrderUrl;

    @Value("${VIEW_ORDERS_URL}")
    private String viewOrdersUrl;

    @Value("${CANCEL_ORDER_URL}")
    private String cancelOrderUrl;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired 
    private OrderedMedicineRepository medicineRepository;
    
	@Override
	public List<OrderDTO> viewOrders(Integer customerId) throws EPharmacyException {
//	    // Validate customer login
//	    if (!isCustomerLoggedIn(customerId)) {
//	        throw new EPharmacyException("Customer is not logged in.");
//	    }
//
//	    // Fetch orders for the customer
//	    List<OrderDTO> orders = fetchOrdersByCustomerId(customerId);
//	    if (orders == null || orders.isEmpty()) {
//	        throw new EPharmacyException("No orders found for the customer.");
//	    }
//
//	    // Update real-time statuses for each order
//	    for (OrderDTO order : orders) {
//	        updateOrderAndDeliveryStatus(order);
//	    }
//
//	    return orders;
		Optional<Order> optional =  orderRepository.findById(customerId);
		List<OrderDTO> list = new ArrayList<OrderDTO>();
		return list;
	}

	@Override
	public String placeOrder(OrderDTO orderDTO) throws EPharmacyException {
	    // Validate customer login
//	    if (!isCustomerLoggedIn(orderDTO.getCustomer().getCustomerId())) {
//	        throw new EPharmacyException("Customer is not logged in.");
//	    }
//
//	    // Validate and select shipping address
//	    if (!isValidAddress(orderDTO.getDeliveryAddress().getAddressId())) {
//	        throw new EPharmacyException("Invalid shipping address.");
//	    }
//
//	    // Validate and select payment card
//	    if (!isValidCard(orderDTO.getCard().getCardId(), orderDTO.getCard().getCustomerId())) {
//	        throw new EPharmacyException("Invalid card details.");
//	    }
//
//	    // Validate CVV with Resilience4j circuit breaker
//	    if (!validateCVV(orderDTO.getCard().getCardId(), orderDTO.getCard().getCvv())) {
//	        throw new EPharmacyException("Payment failed due to invalid CVV.");
//	    }
//
//	    // Calculate discount based on order value
//	    double discount = calculateDiscount(orderDTO.getOrderValueBeforeDiscount());
//	    double finalOrderValue = orderDTO.getOrderValueBeforeDiscount() - discount;
//
//	    // Call Payment Microservice for payment
//	    boolean paymentSuccess = makePayment(orderDTO.getCard(), finalOrderValue);
//	    if (!paymentSuccess) {
//	        throw new EPharmacyException("Payment failed.");
//	    }
//
//	    // Save order details and return success message
//	    saveOrderDetails(orderDTO, finalOrderValue);
	    return "Order placed successfully. Expected delivery date: ";
	}

	@Override
	public void cancelOrder(Integer orderId, String reason) throws EPharmacyException {
	    // Fetch order details
//	    OrderDTO order = fetchOrderById(orderId);
//	    if (order == null) {
//	        throw new EPharmacyException("Order not found.");
//	    }
//
//	    // Check if order can be cancelled
//	    if (!order.getOrderStatus().equals("PROCESSING")) {
//	        throw new EPharmacyException("Order cannot be cancelled at this stage.");
//	    }
//
//	    // Update order status and delivery status
//	    order.setOrderStatus("CANCELLED");
//	    order.setDeliveryStatus("CANCELLED");
//	    saveOrder(order);
	}
}
