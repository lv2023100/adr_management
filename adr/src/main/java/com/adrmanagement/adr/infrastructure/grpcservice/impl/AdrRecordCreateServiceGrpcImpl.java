package com.adrmanagement.adr.infrastructure.grpcservice.impl;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adrmanagement.adr.domain.service.commandservice.AdrRecordCreateCommandService;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordCreateRequest;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordCreateServiceGrpc.AdrRecordCreateServiceImplBase;
import com.adrmanagement.adr.infrastructure.grpcservice.assmbler.AdrRecordCreateAssembler;
import com.adrmanagement.common.adr.domain.commands.AdrRecordCreateCommand;
import com.adrmanagement.common.enums.GenericResponseEnum;
import com.adrmanagement.common.infrastructure.grpcservice.GenericResponseOuterClass.GenericResponse;

import io.grpc.stub.StreamObserver;

@GRpcService
public class AdrRecordCreateServiceGrpcImpl extends AdrRecordCreateServiceImplBase {
	
	Logger log = LoggerFactory.getLogger(AdrRecordCreateServiceGrpcImpl.class);

	@Autowired
	private AdrRecordCreateCommandService adrRecordCreateCommandService;

	@Override
	public void create(AdrRecordCreateRequest adrRecordCreateRequest, StreamObserver<GenericResponse> responseObserver) {
		try {
			AdrRecordCreateCommand adrRecordCreateCommand = AdrRecordCreateAssembler
					.requestToCommand(adrRecordCreateRequest);
			adrRecordCreateCommandService.create(adrRecordCreateCommand);
			responseObserver.onNext(GenericResponseEnum.success());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			responseObserver.onNext(GenericResponseEnum.error());
			responseObserver.onCompleted();
		}

	}

}
