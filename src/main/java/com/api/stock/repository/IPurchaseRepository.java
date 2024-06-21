package com.api.stock.repository;

import com.api.stock.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.List;

@Repository
public interface IPurchaseRepository extends JpaRepository<Purchase, Long> {
    @Override
    @Query("SELECT p FROM Purchase p WHERE p.id = :id")
    Optional<Purchase> findById(@Param("id") Long id);

    @Query("SELECT p FROM Purchase p")
    List<Purchase> findAll();

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Purchase p WHERE p.id = :id")
    boolean existsById(@Param("id") Long id);

    @Query("DELETE FROM Purchase p WHERE p.id = :id")
    void deleteById(@Param("id") Long id);

    @Query("SELECT p FROM Purchase p WHERE p.createdAt BETWEEN :startDate AND :endDate")
    List<Purchase> findPurchasesByPeriod(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT SUM(p.quantity) FROM Purchase p WHERE p.createdAt BETWEEN :startDate AND :endDate")
    Long getTotalSalesByPeriod(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
