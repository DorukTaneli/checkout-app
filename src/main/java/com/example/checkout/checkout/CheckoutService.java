package com.example.checkout.checkout;

import com.example.checkout.offer.Offer;
import com.example.checkout.offer.OfferService;
import com.example.checkout.pricing.Pricing;
import com.example.checkout.pricing.PricingService;
import com.example.checkout.scan.Scan;
import com.example.checkout.scan.ScanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckoutService {

    private final PricingService pricingService;
    private final OfferService offerService;
    private final ScanService scanService;

    public CheckoutService(PricingService pricingService,
                           OfferService offerService,
                           ScanService scanService) {
        this.pricingService = pricingService;
        this.offerService = offerService;
        this.scanService = scanService;
    }

    public int checkout() {
        int total = 0;
        List<Scan> scanList = scanService.getAllScans();

        for(Scan scan : scanList) {
            total += calculateCost(scan);
            total -= calculateDiscount(scan);
        }

        return total;
    }

    private int calculateCost(Scan scan){
        Optional<Pricing> pricingOptional = pricingService.getPricing(scan.getSku());
        if (pricingOptional.isEmpty()) {
            throw new IllegalStateException("Trying to checkout with SKU: '" +
                    scan.getSku() + "' without Pricing");
        }
        return scan.getCount() * pricingOptional.get().getPrice();
    }
    private int calculateDiscount(Scan scan){
        Optional<Offer> offerOptional = offerService.getOffer(scan.getSku());
        if (offerOptional.isPresent()){
            Offer offer = offerOptional.get();
            int offerCount = scan.getCount() / offer.getCount();
            return offerCount * offer.getDiscount();
        } else {
            return 0;
        }
    }
}
