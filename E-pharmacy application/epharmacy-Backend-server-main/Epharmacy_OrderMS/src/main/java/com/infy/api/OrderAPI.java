package com.infy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infy.dto.OrderDTO;
import com.infy.exception.EPharmacyException;
import com.infy.service.OrderService;

@RequestMapping(value = "order-api")
public class OrderAPI {

	@Autowired
	private OrderService orderService;

	@GetMapping(value = "/order/view-orders/customer/{customerId}")
	ResponseEntity<List<OrderDTO>> viewOrders(@PathVariable Integer customerId) throws EPharmacyException {
		return new ResponseEntity<List<OrderDTO>>(orderService.viewOrders(customerId), HttpStatus.OK);
	}

	@PostMapping(value = "/order/place-order")
	ResponseEntity<String> placeOrder(@RequestBody OrderDTO orderDTO) throws EPharmacyException {
	    try {
	        String result = orderService.placeOrder(orderDTO);
	        return new ResponseEntity<>(result, HttpStatus.CREATED);
	    } catch (EPharmacyException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}

	@PutMapping(value = "/order/cancel-order/{orderId}")
	ResponseEntity<String> cancelOrder(@PathVariable Integer orderId, @RequestBody String reasonToCancel) throws EPharmacyException {
	    try {
	        orderService.cancelOrder(orderId, reasonToCancel);
	        return new ResponseEntity<>("Order cancelled successfully.", HttpStatus.OK);
	    } catch (EPharmacyException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}
}
