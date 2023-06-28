package com.example.checkout.scan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/scan")
public class ScanController {

    private final ScanService scanService;

    @Autowired
    public ScanController(ScanService scanService) {
        this.scanService = scanService;
    }

    @GetMapping
    public List<Scan> getScans() {
        return scanService.getAllScans();
    }

    @PostMapping
    public Scan addScan(@RequestBody Scan scan) {
        scanService.addScan(scan);
        return scan;
    }
}
