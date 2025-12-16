package com.truestate.backend.service.impl;

import com.truestate.backend.dto.PagedResponse;
import com.truestate.backend.dto.SalesQueryRequest;
import com.truestate.backend.dto.SalesRecordDto;
import com.truestate.backend.model.SalesRecord;
import com.truestate.backend.repository.SalesRecordRepository;
import com.truestate.backend.service.SalesService;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

    private final SalesRecordRepository salesRecordRepository;

    public SalesServiceImpl(SalesRecordRepository salesRecordRepository) {
        this.salesRecordRepository = salesRecordRepository;
    }

    @Override
    public PagedResponse<SalesRecordDto> getSales(SalesQueryRequest req) {
        if (req == null) {
            req = new SalesQueryRequest();
        }

        int page = Math.max(req.getPage(), 0);
        int size = req.getSize() <= 0 ? 10 : req.getSize();

        Sort sort = "asc".equalsIgnoreCase(req.getSortDir())
                ? Sort.by(mapSortField(req.getSortBy())).ascending()
                : Sort.by(mapSortField(req.getSortBy())).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<SalesRecord> spec = buildSpecification(req);

        Page<SalesRecord> resultPage = salesRecordRepository.findAll(spec, pageable);

        List<SalesRecordDto> content = resultPage.getContent()
                .stream()
                .map(this::toDto)
                .toList();

        return new PagedResponse<>(
                content,
                resultPage.getNumber(),
                resultPage.getSize(),
                resultPage.getTotalElements(),
                resultPage.getTotalPages(),
                resultPage.hasNext(),
                resultPage.hasPrevious()
        );
    }
private Specification<SalesRecord> buildSpecification(SalesQueryRequest r) {
    Specification<SalesRecord> spec = (root, query, cb) -> cb.conjunction();

    Specification<SalesRecord> search = searchSpec(r.getSearch());
    if (search != null) spec = spec.and(search);

    Specification<SalesRecord> region = listIn("customerRegion", r.getRegions());
    if (region != null) spec = spec.and(region);

    Specification<SalesRecord> gender = listIn("gender", r.getGenders());
    if (gender != null) spec = spec.and(gender);

    Specification<SalesRecord> age = ageRange(r.getMinAge(), r.getMaxAge());
    if (age != null) spec = spec.and(age);

    Specification<SalesRecord> category = listIn("productCategory", r.getCategories());
    if (category != null) spec = spec.and(category);

    Specification<SalesRecord> tagSpec = tagsContains(r.getTags());
    if (tagSpec != null) spec = spec.and(tagSpec);

    Specification<SalesRecord> payment = listIn("paymentMethod", r.getPaymentMethods());
    if (payment != null) spec = spec.and(payment);

    Specification<SalesRecord> date = dateRange(r.getStartDate(), r.getEndDate());
    if (date != null) spec = spec.and(date);

    return spec;
}


    private Specification<SalesRecord> searchSpec(String search) {
        if (search == null || search.isBlank()) return null;
        String like = "%" + search.toLowerCase() + "%";
        return (root, query, cb) -> cb.or(
                cb.like(cb.lower(root.get("customerName")), like),
                cb.like(cb.lower(root.get("phoneNumber")), like)
        );
    }

    private Specification<SalesRecord> listIn(String field, List<String> values) {
        if (values == null || values.isEmpty()) return null;
        return (root, query, cb) -> root.get(field).in(values);
    }

    private Specification<SalesRecord> ageRange(Integer minAge, Integer maxAge) {
        if (minAge == null && maxAge == null) return null;
        return (root, query, cb) -> {
            if (minAge != null && maxAge != null) {
                return cb.between(root.get("age"), minAge, maxAge);
            } else if (minAge != null) {
                return cb.greaterThanOrEqualTo(root.get("age"), minAge);
            } else {
                return cb.lessThanOrEqualTo(root.get("age"), maxAge);
            }
        };
    }

    private Specification<SalesRecord> tagsContains(List<String> tags) {
        if (tags == null || tags.isEmpty()) return null;
        return (root, query, cb) -> {
            String pattern = "%" + String.join("%", tags) + "%";
            return cb.like(cb.lower(root.get("tags")), pattern.toLowerCase());
        };
    }

    private Specification<SalesRecord> dateRange(LocalDate start, LocalDate end) {
        if (start == null && end == null) return null;
        return (root, query, cb) -> {
            if (start != null && end != null) {
                return cb.between(root.get("date"), start, end);
            } else if (start != null) {
                return cb.greaterThanOrEqualTo(root.get("date"), start);
            } else {
                return cb.lessThanOrEqualTo(root.get("date"), end);
            }
        };
    }

    private String mapSortField(String sortBy) {
        if (sortBy == null) return "date";
        return switch (sortBy) {
            case "quantity" -> "quantity";
            case "customerName" -> "customerName";
            default -> "date"; // newest first
        };
    }

    private SalesRecordDto toDto(SalesRecord e) {
        SalesRecordDto dto = new SalesRecordDto();

        dto.setId(e.getTransactionId());

        dto.setCustomerId(e.getCustomerId());
        dto.setCustomerName(e.getCustomerName());
        dto.setPhoneNumber(e.getPhoneNumber());
        dto.setGender(e.getGender());
        dto.setAge(e.getAge());
        dto.setCustomerRegion(e.getCustomerRegion());
        dto.setCustomerType(e.getCustomerType());

        dto.setProductId(e.getProductId());
        dto.setProductName(e.getProductName());
        dto.setBrand(e.getBrand());
        dto.setProductCategory(e.getProductCategory());
        dto.setTags(e.getTags());

        dto.setQuantity(e.getQuantity());
        dto.setPricePerUnit(e.getPricePerUnit());
        dto.setDiscountPercentage(e.getDiscountPercentage());
        dto.setTotalAmount(e.getTotalAmount());
        dto.setFinalAmount(e.getFinalAmount());

        dto.setDate(e.getOrderDate());
        dto.setPaymentMethod(e.getPaymentMethod());
        dto.setOrderStatus(e.getOrderStatus());
        dto.setDeliveryType(e.getDeliveryType());
        dto.setStoreId(e.getStoreId());
        dto.setStoreLocation(e.getStoreLocation());
        dto.setSalespersonId(e.getSalespersonId());
        dto.setEmployeeName(e.getEmployeeName());

        return dto;
    }
}
