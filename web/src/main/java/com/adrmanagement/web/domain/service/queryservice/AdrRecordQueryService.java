package com.adrmanagement.web.domain.service.queryservice;

import org.springframework.stereotype.Service;

import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordGetResponse;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemGetListResponse;
@Service
public interface AdrRecordQueryService {
	
	public AdrRecordGetResponse getAdrRecord(Long adrId) throws Exception;
	public AdrRecordItemGetListResponse getAdrRecordItemList(Long teamId) throws Exception;

}
