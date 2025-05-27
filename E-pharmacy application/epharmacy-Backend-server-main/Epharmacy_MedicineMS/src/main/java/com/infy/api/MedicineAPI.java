package com.infy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.MedicineDTO;
import com.infy.exception.EPharmacyException;
import com.infy.service.MedicineService;

@RestController
@RequestMapping(value= "medicine-api")
@Validated 
@CrossOrigin
public class MedicineAPI {
	
	@Autowired
	private MedicineService medicineService;
	
	@GetMapping(value = "/medicines/pageNumber/{pageNumber}/pageSize/{pageSize}")
	public ResponseEntity<List<MedicineDTO>> getAllMedicines(@PathVariable Integer pageNumber,@PathVariable Integer pageSize) throws EPharmacyException {
		List<MedicineDTO> allmedicines= medicineService.getAllMedicines(pageNumber, pageSize);
		return new ResponseEntity<>(allmedicines, HttpStatus.OK);
	}
	
	@GetMapping(value = "/medicines/{medicineId}")
	public ResponseEntity<MedicineDTO> getMedicineById(@PathVariable Integer medicineId) throws EPharmacyException {
	    MedicineDTO medicine = medicineService.getMedicineById(medicineId);
	    if (medicine == null) {
	        throw new EPharmacyException("Medicine with given id is not found.");
	    }
	    return new ResponseEntity<>(medicine, HttpStatus.OK);
	}

	@GetMapping(value = "/medicines/category/{categoryName}")
	public ResponseEntity<List<MedicineDTO>> category(@PathVariable String categoryName) throws EPharmacyException {
	    List<MedicineDTO> medicines = medicineService.getMedicinesByCategory(categoryName);
	    if (medicines.isEmpty()) {
	        throw new EPharmacyException("No medicine found with given category.");
	    }
	    return new ResponseEntity<>(medicines, HttpStatus.OK);
	}

//	@PutMapping(value = "/medicines/update-stock/medicine/{medicineId}")
//	public ResponseEntity<String> modifyQuantityOfMedicineInStock(@PathVariable Integer medicineId, @RequestBody Integer orderedQuantity) throws EPharmacyException {
//	    boolean isUpdated = medicineService.updateMedicineStock(medicineId, orderedQuantity);
//	    if (!isUpdated) {
//	        throw new EPharmacyException("Invalid medicineId or stock not available.");
//	    }
//	    return new ResponseEntity<>("Stock updated successfully.", HttpStatus.OK);
//	}
}
