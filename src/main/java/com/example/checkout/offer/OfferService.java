package com.example.checkout.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
    private final OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public Optional<Offer> getOffer(Character sku) {
        return offerRepository.findById(sku);
    }

    public void addOffer(Offer offer) {
        Optional<Offer> offerOptional = offerRepository.findById(offer.getSku());
        if (offerOptional.isPresent()) {
            throw new IllegalStateException("Pricing with SKU '" + offer.getSku() + "' already exists");
        }
        offerRepository.save(offer);
    }
}
