package com.api.stock.repository;

import com.api.stock.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Override
    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Optional<Product> findById(@Param("id") Long id);

    @Query("SELECT p FROM Product p")
    List<Product> findAll();

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Product p WHERE p.id = :id")
    boolean existsById(@Param("id") Long id);

    @Query("DELETE FROM Product p WHERE p.id = :id")
    void deleteById(@Param("id") Long id);

    @Query("SELECT p.product, SUM(p.quantity) as totalQuantity FROM Purchase p GROUP BY p.product ORDER BY totalQuantity DESC")
    List<Object[]> findTopSellingProducts();

    @Query("SELECT SUM(p.amount) FROM Product p")
    Long getTotalItemsInStock();
}
