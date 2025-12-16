package com.truestate.backend.controller;

import com.truestate.backend.dto.PagedResponse;
import com.truestate.backend.dto.SalesQueryRequest;
import com.truestate.backend.dto.SalesRecordDto;
import com.truestate.backend.service.SalesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "http://localhost:5173")
public class SalesController {

    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    // Handles GET /api/sales
    @GetMapping
    public PagedResponse<SalesRecordDto> getSales(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,

            @RequestParam(required = false) String search,
            @RequestParam(required = false) List<String> regions,
            @RequestParam(required = false) List<String> genders,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) List<String> categories,
            @RequestParam(required = false) List<String> tags,
            @RequestParam(required = false) List<String> paymentMethods,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate,

            @RequestParam(defaultValue = "orderDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir
    ) {
        SalesQueryRequest req = new SalesQueryRequest();
        req.setPage(page);
        req.setSize(size);
        req.setSearch(search);
        req.setRegions(regions);
        req.setGenders(genders);
        req.setMinAge(minAge);
        req.setMaxAge(maxAge);
        req.setCategories(categories);
        req.setTags(tags);
        req.setPaymentMethods(paymentMethods);
        req.setStartDate(startDate);
        req.setEndDate(endDate);
        req.setSortBy(sortBy);
        req.setSortDir(sortDir);

        return salesService.getSales(req);
    }
}
