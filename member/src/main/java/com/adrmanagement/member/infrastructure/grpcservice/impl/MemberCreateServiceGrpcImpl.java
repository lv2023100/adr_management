package com.adrmanagement.member.infrastructure.grpcservice.impl;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adrmanagement.common.enums.GenericResponseEnum;
import com.adrmanagement.common.infrastructure.grpcservice.GenericResponseOuterClass.GenericResponse;
import com.adrmanagement.common.member.domain.commands.MemberCreateCommand;
import com.adrmanagement.member.domain.service.commandservice.MemberCreateCommandService;
import com.adrmanagement.member.domain.service.queryservice.MemberGetQueryService;
import com.adrmanagement.member.infrastructure.grpcservice.MemberCreateRequest;
import com.adrmanagement.member.infrastructure.grpcservice.MemberCreateServiceGrpc.MemberCreateServiceImplBase;
import com.adrmanagement.member.infrastructure.grpcservice.assmbler.MemberCreateAssembler;

import io.grpc.stub.StreamObserver;
@GRpcService
public class MemberCreateServiceGrpcImpl extends MemberCreateServiceImplBase{
	
	Logger log = LoggerFactory.getLogger(MemberCreateServiceGrpcImpl.class);
	
	@Autowired
	private MemberCreateCommandService memberCreateCommandService;
	
	@Autowired
	private MemberGetQueryService memberGetQueryService;
	
	@Override
	public void create(MemberCreateRequest memberCreateRequest, StreamObserver<GenericResponse> responseObserver) {
		try {
			if(memberGetQueryService.isDuplicateAccount(memberCreateRequest.getAccount())) {
				responseObserver.onNext(GenericResponseEnum.duplicateAccount());
			}else {
				MemberCreateCommand memberCreateCommand = MemberCreateAssembler.rquestToCommand(memberCreateRequest);
				memberCreateCommandService.create(memberCreateCommand);
				responseObserver.onNext(GenericResponseEnum.success());
			}
			
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			responseObserver.onNext(GenericResponseEnum.error());
			responseObserver.onCompleted();
		}
		
		
	}

}
