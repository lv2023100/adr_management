package com.adrmanagement.web.domain.service.grpcclient;

import org.springframework.stereotype.Component;

import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordCreateRequest;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordGetResponse;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemGetListResponse;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordUpdateRequest;

@Component
public interface AdrRecordClient {
	
	public AdrRecordGetResponse getAdrRecord(Long adrId) throws Exception;
	public void update(AdrRecordUpdateRequest adrRecordUpdateRequest) throws Exception;
	public void create(AdrRecordCreateRequest adrRecordCreateRequest) throws Exception;
	public AdrRecordItemGetListResponse getAdrRecordItemList(Long teamId) throws Exception;

}
