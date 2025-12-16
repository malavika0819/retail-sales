package com.truestate.backend.repository;

import com.truestate.backend.model.SalesRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SalesRecordRepository
        extends JpaRepository<SalesRecord, Long>, JpaSpecificationExecutor<SalesRecord> {
}
