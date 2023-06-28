package com.example.checkout.scan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScanService {
    private final ScanRepository scanRepository;

    @Autowired
    public ScanService(ScanRepository scanRepository) {
        this.scanRepository = scanRepository;
    }

    public List<Scan> getAllScans() {
        return scanRepository.findAll();
    }

    public void addScan(Scan newScan) {
        Optional<Scan> scanOptional = scanRepository.findById(newScan.getSku());
        if (scanOptional.isPresent()) {
            Scan alreadyScanned = scanOptional.get();
            Scan total = new Scan(newScan.getSku(), newScan.getCount() + alreadyScanned.getCount());
            scanRepository.save(total);
        } else {
            scanRepository.save(newScan);
        }
    }
}
