package com.example.checkout.pricing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, Character> {

    @Query("SELECT p FROM Pricing p WHERE p.sku = ?1")
    Optional<Pricing> findPricingBySku(Character sku);

}
