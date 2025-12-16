package com.truestate.backend.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
public class SalesQueryRequest {

    private int page = 0;
    private int size = 10;

    // search
    private String search;

    // filters
    private List<String> regions;
    private List<String> genders;
    private Integer minAge;
    private Integer maxAge;
    private List<String> categories;
    private List<String> tags;
    private List<String> paymentMethods;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    // sorting
    private String sortBy = "date";   // date | quantity | customerName
    private String sortDir = "desc";  // asc | desc
}
