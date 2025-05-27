package com.infy.api;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infy.dto.CustomerCartDTO;
import com.infy.exception.EPharmacyException;
import com.infy.service.CustomerCartService;

@RestController
@RequestMapping(value = "/cart-api")
public class CartAPI {

    @Autowired
    private CustomerCartService customerCartService;
    
    // Temporary endpoint to test the service
    @GetMapping(value = "/temp123")
    public String getTempMsg() {
        return "Namaste Microservice";
    }

    // Add medicines to the cart
    @PostMapping(value = "/cart/add/medicine/{medicineId}/customer/{customerId}")
    public ResponseEntity<String> addMedicinesToCart(@RequestBody CustomerCartDTO customerCartDTO, 
                                                     @PathVariable Integer medicineId, 
                                                     @PathVariable Integer customerId)
            throws EPharmacyException {
        if (medicineId == null || customerId == null || customerCartDTO.getQuantity() == null || customerCartDTO.getQuantity() <= 0) {
            throw new EPharmacyException("Invalid medicineId, customerId, or quantity");
        }
        customerCartService.addMedicinesToCart(customerCartDTO, medicineId, customerId);
        return new ResponseEntity<>("Medicine added to cart successfully.", HttpStatus.CREATED);
    }

    // Retrieve medicines from the cart
    @GetMapping(value = "/cart/medicines/customer/{customerId}")
    public ResponseEntity<List<CustomerCartDTO>> getMedicinesFromCart(@PathVariable("customerId") Integer customerId)
            throws EPharmacyException {
        List<CustomerCartDTO> customerCartDTOs = customerCartService.getMedicinesFromCart(customerId);
        return new ResponseEntity<>(customerCartDTOs, HttpStatus.OK);
    }

//    // Update quantity of a medicine in the cart (Uncomment and implement the logic)
//    @PutMapping(value = "/cart/update/quantity/medicine/{medicineId}/customer/{customerId}")
//    public ResponseEntity<String> modifyQuantityOfMedicineInCart(@PathVariable Integer customerId, 
//                                                                  @PathVariable Integer medicineId, 
//                                                                  @RequestBody Integer quantity) 
//            throws EPharmacyException {
//        if (medicineId == null || customerId == null || quantity == null || quantity <= 0) {
//            throw new EPharmacyException("Invalid medicineId, customerId, or quantity");
//        }
//        customerCartService.modifyQuantityOfMedicineInCart(customerId, medicineId, quantity);
//        return new ResponseEntity<>("Quantity updated successfully.", HttpStatus.OK);
//    }

    // Delete a specific medicine from the cart
    @DeleteMapping(value = "/cart/delete/medicine/{medicineId}/customer/{customerId}")
    public ResponseEntity<String> deleteMedicineFromCart(@PathVariable Integer customerId, 
                                                         @PathVariable Integer medicineId) 
            throws EPharmacyException {
        if (medicineId == null || customerId == null) {
            throw new EPharmacyException("Invalid medicineId or customerId");
        }
        customerCartService.deleteMedicineFromCart(customerId, medicineId);
        return new ResponseEntity<>("Medicine deleted from cart successfully.", HttpStatus.OK);
    }

    // Delete all medicines from a customer's cart
    @DeleteMapping(value = "/cart/delete-medicines/customer/{customerId}")
    public ResponseEntity<String> deleteAllMedicinesFromCart(@PathVariable Integer customerId) 
            throws EPharmacyException {
        if (customerId == null) {
            throw new EPharmacyException("Invalid customerId");
        }
        customerCartService.deleteAllMedicinesFromCart(customerId);
        return new ResponseEntity<>("All the medicines successfully deleted from cart.", HttpStatus.OK);
    }
}
