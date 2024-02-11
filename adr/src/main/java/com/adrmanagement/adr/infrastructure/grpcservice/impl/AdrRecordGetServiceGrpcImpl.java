package com.adrmanagement.adr.infrastructure.grpcservice.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adrmanagement.adr.domain.model.aggregate.AdrRecordAggregate;
import com.adrmanagement.adr.domain.model.axon.eventstore.AdrRecordEventStore;
import com.adrmanagement.adr.domain.model.entity.AdrRecord;
import com.adrmanagement.adr.domain.service.queryservice.AdrRecordQueryService;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordEventStoreResponse;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordGetRequest;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordGetResponse;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordGetServiceGrpc.AdrRecordGetServiceImplBase;
import com.adrmanagement.common.adr.domain.query.AdrRecordGetQuery;
import com.adrmanagement.common.util.ProtoBeanUtils;

import io.grpc.stub.StreamObserver;

@GRpcService
public class AdrRecordGetServiceGrpcImpl extends AdrRecordGetServiceImplBase{
	
	Logger log = LoggerFactory.getLogger(AdrRecordGetServiceGrpcImpl.class);
	
	@Autowired
	private AdrRecordQueryService adrRecordGetQueryService;


	@Override
	public void getAdr(AdrRecordGetRequest adrRecordGetRequest, StreamObserver<AdrRecordGetResponse> responseObserver) {
		try {
			AdrRecordGetQuery adrRecordGetQuery = new AdrRecordGetQuery();
			adrRecordGetQuery.setAdrId(adrRecordGetRequest.getAdrId());
			AdrRecordAggregate adrRecordAggregate = adrRecordGetQueryService.getAdrRecord(adrRecordGetQuery);
			var adrRecordGetResponseBuilder = AdrRecordGetResponse.newBuilder();
			ProtoBeanUtils.toProtoBean(adrRecordGetResponseBuilder, adrRecordAggregate);
			List<AdrRecordEventStore> adrRecordEventStoreLilst =  adrRecordGetQueryService.getAdrRecordEventList(adrRecordGetQuery.getAdrId());
			List<AdrRecordEventStoreResponse> adrRecordEventStoreResponseList = new ArrayList<AdrRecordEventStoreResponse>();
			adrRecordEventStoreLilst.stream().forEach(adrRecordEventStore->{
				var adrRecordEventStoreResponse = AdrRecordEventStoreResponse.newBuilder();
				try {
					ProtoBeanUtils.toProtoBean(adrRecordEventStoreResponse, adrRecordEventStore);
					adrRecordEventStoreResponseList.add(adrRecordEventStoreResponse.build());
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			});
			adrRecordGetResponseBuilder.addAllAdrRecordEventStoreList(adrRecordEventStoreResponseList);
			responseObserver.onNext(adrRecordGetResponseBuilder.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			responseObserver.onCompleted();
		}

	}

}
