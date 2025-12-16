package com.truestate.backend.service;

import com.truestate.backend.dto.PagedResponse;
import com.truestate.backend.dto.SalesQueryRequest;
import com.truestate.backend.dto.SalesRecordDto;

public interface SalesService {

    PagedResponse<SalesRecordDto> getSales(SalesQueryRequest request);
}
