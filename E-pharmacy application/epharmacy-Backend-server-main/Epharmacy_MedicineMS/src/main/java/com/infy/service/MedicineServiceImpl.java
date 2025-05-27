package com.infy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dto.MedicineDTO;
import com.infy.entity.Medicine;
import com.infy.exception.EPharmacyException;
import com.infy.repository.MedicineRepository;

@Service(value = "medicineService")
@Transactional
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Value("${GET_ALL_MEDICINES_URL}")
    private String getAllMedicinesUrl;

    @Value("${GET_MEDICINE_BY_ID_URL}")
    private String getMedicineByIdUrl;

    @Value("${GET_MEDICINES_BY_CATEGORY_URL}")
    private String getMedicinesByCategoryUrl;

    @Value("${UPDATE_MEDICINE_STOCK_URL}")
    private String updateMedicineStockUrl;

    @Override
    public List<MedicineDTO> getAllMedicines(Integer pageNumber, Integer pageSize) throws EPharmacyException {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Medicine> pageMedicine = medicineRepository.findAll(pageable);

        if (pageMedicine.isEmpty()) {
            throw new EPharmacyException("MedicineService.NO_MEDICINE_FOUND");
        }
        List<MedicineDTO> listOfMedicineDTO = new ArrayList<>();

        List<Medicine> medicine = pageMedicine.getContent();
        for (Medicine m : medicine) {
            MedicineDTO mDTO = new MedicineDTO();
            mDTO.setMedicineId(m.getMedicineId());
            mDTO.setMedicineName(m.getMedicineName());
            mDTO.setCategory(m.getCategory());
            mDTO.setDiscountPercent(m.getDiscountPercent());
            mDTO.setManufacturingDate(m.getManufacturingDate());
            mDTO.setExpiryDate(m.getExpiryDate());
            mDTO.setManufacturer(m.getManufacturer());
            mDTO.setPrice(m.getPrice());
            mDTO.setQuantity(m.getQuantity());

            listOfMedicineDTO.add(mDTO);
        }

        return listOfMedicineDTO;
    }

    @Override
    public List<MedicineDTO> getMedicinesByCategory(String category) throws EPharmacyException {
        List<Medicine> medicines = medicineRepository.findByCategory(category);
        if (medicines.isEmpty()) {
            throw new EPharmacyException("MedicineService.NO_MEDICINE_FOUND_WITH_CATEGORY");
        }
        List<MedicineDTO> medicineDTOs = new ArrayList<>();
        for (Medicine medicine : medicines) {
            MedicineDTO mDTO = new MedicineDTO();
            mDTO.setMedicineId(medicine.getMedicineId());
            mDTO.setMedicineName(medicine.getMedicineName());
            mDTO.setCategory(medicine.getCategory());
            mDTO.setDiscountPercent(medicine.getDiscountPercent());
            mDTO.setManufacturingDate(medicine.getManufacturingDate());
            mDTO.setExpiryDate(medicine.getExpiryDate());
            mDTO.setManufacturer(medicine.getManufacturer());
            mDTO.setPrice(medicine.getPrice());
            mDTO.setQuantity(medicine.getQuantity());
            medicineDTOs.add(mDTO);
        }
        return medicineDTOs;
    }

    @Override
    public MedicineDTO getMedicineById(Integer medicineId) throws EPharmacyException {
        Medicine medicine = medicineRepository.findById(medicineId).orElse(null);
        if (medicine == null) {
            throw new EPharmacyException("MedicineService.NO_MEDICINE_FOUND_WITH_ID");
        }
        MedicineDTO mDTO = new MedicineDTO();
        mDTO.setMedicineId(medicine.getMedicineId());
        mDTO.setMedicineName(medicine.getMedicineName());
        mDTO.setCategory(medicine.getCategory());
        mDTO.setDiscountPercent(medicine.getDiscountPercent());
        mDTO.setManufacturingDate(medicine.getManufacturingDate());
        mDTO.setExpiryDate(medicine.getExpiryDate());
        mDTO.setManufacturer(medicine.getManufacturer());
        mDTO.setPrice(medicine.getPrice());
        mDTO.setQuantity(medicine.getQuantity());
        return mDTO;
    }

    @Override
    public void updateMedicineQuantityAfterOrder(Integer medicineId, Integer orderedQuantity) throws EPharmacyException {
        int updatedRows = medicineRepository.updateStock(medicineId, orderedQuantity);
        if (updatedRows == 0) {
            throw new EPharmacyException("MedicineService.INVALID_MEDICINE_ID_OR_INSUFFICIENT_STOCK");
        }
    }

}
