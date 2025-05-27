package com.infy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.infy.entity.Medicine;

import java.util.List;

public interface MedicineRepository extends PagingAndSortingRepository<Medicine, Integer> {

    @Query("SELECT m FROM Medicine m WHERE m.category = :category")
    List<Medicine> findByCategory(@Param("category") String category);

    @Query("UPDATE Medicine m SET m.stock = m.stock - :orderedQuantity WHERE m.medicineId = :medicineId AND m.stock >= :orderedQuantity")
    int updateStock(@Param("medicineId") Integer medicineId, @Param("orderedQuantity") Integer orderedQuantity);

    Page<Medicine> findAll(Pageable pageable);
}
