package com.infy.service;

import java.util.List;

import com.infy.dto.MedicineDTO;
import com.infy.exception.EPharmacyException;

public interface MedicineService {
	List<MedicineDTO> getAllMedicines(Integer pageNumber,Integer pageSize) throws EPharmacyException;
	List<MedicineDTO> getMedicinesByCategory(String category)throws EPharmacyException;
	MedicineDTO getMedicineById(Integer medicineId) throws EPharmacyException;
	void updateMedicineQuantityAfterOrder(Integer medicineId, Integer orderedQuantity) throws EPharmacyException;
}
