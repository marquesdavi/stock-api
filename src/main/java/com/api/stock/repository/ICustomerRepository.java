package com.api.stock.repository;

import com.api.stock.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    @Query("SELECT c FROM Customer c WHERE c.id = :id")
    Optional<Customer> findById(@Param("id") Long id);

    @Query("SELECT c FROM Customer c")
    List<Customer> findAll();

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.id = :id")
    boolean existsById(@Param("id") Long id);

    @Query("DELETE FROM Customer c WHERE c.id = :id")
    void deleteById(@Param("id") Long id);
}
