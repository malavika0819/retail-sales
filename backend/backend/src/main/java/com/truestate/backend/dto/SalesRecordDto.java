package com.truestate.backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SalesRecordDto {

    private Long id;

    // Customer fields
    private String customerId;
    private String customerName;
    private String phoneNumber;
    private String gender;
    private Integer age;
    private String customerRegion;
    private String customerType;

    // Product fields
    private String productId;
    private String productName;
    private String brand;
    private String productCategory;
    private String tags;

    // Sales fields
    private Integer quantity;
    private Double pricePerUnit;
    private Double discountPercentage;
    private Double totalAmount;
    private Double finalAmount;

    // Operational fields
    private LocalDate date;
    private String paymentMethod;
    private String orderStatus;
    private String deliveryType;
    private String storeId;
    private String storeLocation;
    private String salespersonId;
    private String employeeName;
}
