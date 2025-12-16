package com.truestate.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "retail_sales")
@Data
public class SalesRecord {

    @Id
    @Column(name = "transaction_id")
    private Long transactionId;

    // Customer fields
    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String gender;
    private Integer age;

    @Column(name = "customer_region")
    private String customerRegion;

    @Column(name = "customer_type")
    private String customerType;

    // Product fields
    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    private String brand;

    @Column(name = "product_category")
    private String productCategory;

    private String tags;

    // Sales fields
    private Integer quantity;

    @Column(name = "price_per_unit")
    private Double pricePerUnit;

    @Column(name = "discount_percentage")
    private Double discountPercentage;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "final_amount")
    private Double finalAmount;

    // Operational fields
    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "delivery_type")
    private String deliveryType;

    @Column(name = "store_id")
    private String storeId;

    @Column(name = "store_location")
    private String storeLocation;

    @Column(name = "salesperson_id")
    private String salespersonId;

    @Column(name = "employee_name")
    private String employeeName;
}
