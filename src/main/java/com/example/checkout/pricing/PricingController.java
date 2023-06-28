package com.example.checkout.pricing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/pricing")
public class PricingController {

    private final PricingService pricingService;

    @Autowired
    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @GetMapping
    public List<Pricing> getPricings() {
        return pricingService.getAllPricings();
    }

    @PostMapping
    public void addNewPricing(@RequestBody Pricing pricing) {
        pricingService.addNewPricing(pricing);
    }

    @DeleteMapping(path = "{sku}")
    public void deletePricing(@PathVariable("sku") Character sku) {
        pricingService.deletePricing(sku);
    }

    @PutMapping(path = "{sku}")
    public void updatePricing(@PathVariable("sku") Character sku,
                              @RequestParam Integer price) {
        pricingService.updatePricing(sku, price);
    }


}
