package com.example.checkout.pricing;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PricingService {

    private final PricingRepository pricingRepository;

    @Autowired
    public PricingService(PricingRepository pricingRepository) {
        this.pricingRepository = pricingRepository;
    }

    public List<Pricing> getAllPricings() {
        return pricingRepository.findAll();
    }

    public Optional<Pricing> getPricing(Character sku) {
        return pricingRepository.findById(sku);
    }

    public void addNewPricing(Pricing pricing) {
        if (pricingRepository.existsById(pricing.getSku())) {
            throw new IllegalStateException("Pricing with SKU '" + pricing.getSku() + "' already exists");
        }
        pricingRepository.save(pricing);
    }

    public void deletePricing(Character sku) {
        boolean exists = pricingRepository.existsById(sku);
        if (!exists) {
            throw new IllegalStateException("Pricing with SKU '" + sku + "' does not exist");
        }
        pricingRepository.deleteById(sku);
    }

    @Transactional
    public void updatePricing(Character sku, Integer price) {
        Pricing pricing = pricingRepository.findById(sku)
                .orElseThrow(() -> new IllegalStateException(
                        "Pricing with SKU '" + sku + "' does not exist"));
        if (price > 0 && !Objects.equals(pricing.getPrice(), price)) {
            pricing.setPrice(price);
        }
    }
}
