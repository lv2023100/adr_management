package com.adrmanagement.web.application.service.queryservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordGetResponse;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemGetListResponse;
import com.adrmanagement.web.domain.service.grpcclient.AdrRecordClient;
import com.adrmanagement.web.domain.service.queryservice.AdrRecordQueryService;
@Service
public class AdrRecordQueryServiceImpl implements AdrRecordQueryService{
	
	@Autowired
	private AdrRecordClient adrRecordGetClient;

	@Override
	@Cacheable(value="adr", key="#adrId")
	public AdrRecordGetResponse getAdrRecord(Long adrId) throws Exception {
		return adrRecordGetClient.getAdrRecord(adrId);
	}

	@Override
	@Cacheable(value="adrItems", key="#teamId")
	public AdrRecordItemGetListResponse getAdrRecordItemList(Long teamId) throws Exception {
		return adrRecordGetClient.getAdrRecordItemList(teamId);
	}

}
