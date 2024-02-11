package com.adrmanagement.adr.infrastructure.grpcservice.impl;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adrmanagement.adr.domain.service.commandservice.AdrRecordItemPublishCommandService;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemPublishByMemberIdRequest;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemPublishServiceGrpc.AdrRecordItemPublishServiceImplBase;
import com.adrmanagement.common.adr.domain.commands.AdrRecordItemPublishByMemberIdCommand;
import com.adrmanagement.common.enums.GenericResponseEnum;
import com.adrmanagement.common.infrastructure.grpcservice.GenericResponseOuterClass.GenericResponse;

import io.grpc.stub.StreamObserver;

@GRpcService
public class AdrRecordItemPublishServiceGrpImpl extends AdrRecordItemPublishServiceImplBase{
	
	Logger log = LoggerFactory.getLogger(AdrRecordItemPublishServiceGrpImpl.class);
	
	@Autowired
	private AdrRecordItemPublishCommandService adrRecordItemPublishCommandService;
	
	@Override
	public void publishByMemberId(AdrRecordItemPublishByMemberIdRequest adrRecordItemPublishByMemberIdRequest, StreamObserver<GenericResponse> responseObserver) {
		try {
			AdrRecordItemPublishByMemberIdCommand adrRecordItemPublishByMemberIdCommand = new AdrRecordItemPublishByMemberIdCommand();
			adrRecordItemPublishByMemberIdCommand.setMemberId(adrRecordItemPublishByMemberIdRequest.getMemberId());
			adrRecordItemPublishByMemberIdCommand.setEditorMemberName(adrRecordItemPublishByMemberIdRequest.getName());
			adrRecordItemPublishCommandService.publishByMemberId(adrRecordItemPublishByMemberIdCommand);
			responseObserver.onNext(GenericResponseEnum.success());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			responseObserver.onNext(GenericResponseEnum.error());
			responseObserver.onCompleted();
		}
		
	}

}
