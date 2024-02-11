package com.adrmanagement.adr.infrastructure.grpcservice.impl;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adrmanagement.adr.domain.service.commandservice.AdrRecordUpdateCommandService;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordUpdateRequest;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordUpdateServiceGrpc.AdrRecordUpdateServiceImplBase;
import com.adrmanagement.adr.infrastructure.grpcservice.assmbler.AdrRecordUpdateAssembler;
import com.adrmanagement.common.adr.domain.commands.AdrRecordUpdateCommand;
import com.adrmanagement.common.enums.GenericResponseEnum;
import com.adrmanagement.common.infrastructure.grpcservice.GenericResponseOuterClass.GenericResponse;

import io.grpc.stub.StreamObserver;

@GRpcService
public class AdrRecordUpdateServiceGrpcImpl extends AdrRecordUpdateServiceImplBase {
	
	Logger log = LoggerFactory.getLogger(AdrRecordUpdateServiceGrpcImpl.class);

	@Autowired
	private AdrRecordUpdateCommandService adrRecordUpdateCommandService;

	@Override
	public void update(AdrRecordUpdateRequest adrRecordUpdateRequest, StreamObserver<GenericResponse> responseObserver) {
		try {
			AdrRecordUpdateCommand adrRecordUpdateCommand = AdrRecordUpdateAssembler
					.requestToCommand(adrRecordUpdateRequest);
			adrRecordUpdateCommandService.update(adrRecordUpdateCommand);
			responseObserver.onNext(GenericResponseEnum.success());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			responseObserver.onNext(GenericResponseEnum.error());
			responseObserver.onCompleted();
		}

	}

}
