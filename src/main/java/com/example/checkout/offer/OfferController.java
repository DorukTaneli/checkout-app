package com.example.checkout.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/offer")
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public List<Offer> getOffers() {
        return offerService.getAllOffers();
    }

    @PostMapping
    public Offer addOffer(@RequestBody Offer offer) {
        offerService.addOffer(offer);
        return offer;
    }
}
