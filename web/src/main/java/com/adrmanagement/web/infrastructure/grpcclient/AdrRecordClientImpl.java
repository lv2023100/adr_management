package com.adrmanagement.web.infrastructure.grpcclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordCreateRequest;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordCreateServiceGrpc.AdrRecordCreateServiceBlockingStub;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordGetRequest;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordGetResponse;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordGetServiceGrpc.AdrRecordGetServiceBlockingStub;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemGetListRequest;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemGetListResponse;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemGetListServiceGrpc.AdrRecordItemGetListServiceBlockingStub;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordUpdateRequest;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordUpdateServiceGrpc.AdrRecordUpdateServiceBlockingStub;
import com.adrmanagement.web.domain.service.grpcclient.AdrRecordClient;

@Component
public class AdrRecordClientImpl implements AdrRecordClient{
	
	@Autowired
	private  AdrRecordGetServiceBlockingStub adrRecordGetServiceBlockingStub;
	
	@Autowired
	private AdrRecordUpdateServiceBlockingStub adrRecordUpdateServiceBlockingStub;
	
	@Autowired
	private AdrRecordCreateServiceBlockingStub adrRecordCreateServiceBlockingStub;
	
	@Autowired
	private AdrRecordItemGetListServiceBlockingStub adrRecordItemGetListServiceBlockingStub;

	@Override
	public AdrRecordGetResponse getAdrRecord(Long adrId) throws Exception {
		var adrRecordGetRequestBuild= AdrRecordGetRequest.newBuilder()
				.setAdrId(adrId);
		return adrRecordGetServiceBlockingStub.getAdr(adrRecordGetRequestBuild.build());
		
	}
	
	@Override
	public AdrRecordItemGetListResponse getAdrRecordItemList(Long teamId) throws Exception {
		var adrRecordItemGetListRequestBuild= AdrRecordItemGetListRequest.newBuilder()
				.setTeamId(teamId);
		return adrRecordItemGetListServiceBlockingStub.getAdrList(adrRecordItemGetListRequestBuild.build());
		
	}

	@Override
	public void update(AdrRecordUpdateRequest adrRecordUpdateRequest) throws Exception {
		adrRecordUpdateServiceBlockingStub.update(adrRecordUpdateRequest);
	}

	@Override
	public void create(AdrRecordCreateRequest adrRecordCreateRequest) throws Exception {
		adrRecordCreateServiceBlockingStub.create(adrRecordCreateRequest);
		
	}

}
