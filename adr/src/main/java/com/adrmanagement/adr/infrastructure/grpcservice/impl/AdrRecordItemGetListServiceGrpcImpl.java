package com.adrmanagement.adr.infrastructure.grpcservice.impl;

import java.io.IOException;
import java.util.List;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adrmanagement.adr.domain.model.aggregate.AdrRecordItemAggregate;
import com.adrmanagement.adr.domain.service.queryservice.AdrRecordItemQueryService;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordGetResponse;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemGetListRequest;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemGetListResponse;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemGetListServiceGrpc.AdrRecordItemGetListServiceImplBase;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemGetResponse;
import com.adrmanagement.common.adr.domain.query.AdrRecordItemGetListQuery;
import com.adrmanagement.common.util.ProtoBeanUtils;

import io.grpc.stub.StreamObserver;
@GRpcService
public class AdrRecordItemGetListServiceGrpcImpl extends AdrRecordItemGetListServiceImplBase {
	
	Logger log = LoggerFactory.getLogger(AdrRecordItemGetListServiceGrpcImpl.class);
	
	@Autowired
	private AdrRecordItemQueryService adrRecordItemQueryService;


	@Override
	public void getAdrList(AdrRecordItemGetListRequest adrRecordGetListRequest, StreamObserver<AdrRecordItemGetListResponse> responseObserver) {
		try {
			AdrRecordItemGetListQuery adrRecordItemGetListQuery = new AdrRecordItemGetListQuery();
			adrRecordItemGetListQuery.setTeamId(adrRecordGetListRequest.getTeamId());
			List<AdrRecordItemAggregate> adrRecordItemAggregateList = adrRecordItemQueryService.getAdrRecordList(adrRecordItemGetListQuery);
			var adrRecordItemGetListResponseBuilder = AdrRecordItemGetListResponse.newBuilder();
			adrRecordItemAggregateList.stream()
		    .forEach(adrRecordItemAggregate -> {
		        var adrRecordItemGetResponseBuilder = AdrRecordItemGetResponse.newBuilder();
		        try {
					ProtoBeanUtils.toProtoBean(adrRecordItemGetResponseBuilder, adrRecordItemAggregate);
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
		        adrRecordItemGetListResponseBuilder.addAdrRecordItemGetResponse(adrRecordItemGetResponseBuilder);
		    });
			
			responseObserver.onNext(adrRecordItemGetListResponseBuilder.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			responseObserver.onCompleted();
		}

	}

}
