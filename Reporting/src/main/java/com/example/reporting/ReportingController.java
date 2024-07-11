package com.example.reporting;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reporting")
public class ReportingController {

    @Autowired
    private ReportingService reportingService;

    @GetMapping("/top-selling-products")
    public List<Product> getTopSellingProducts() {
        return reportingService.getTopSellingProducts();
    }
}
