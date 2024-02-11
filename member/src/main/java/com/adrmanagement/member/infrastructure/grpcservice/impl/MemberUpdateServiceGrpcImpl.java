package com.adrmanagement.member.infrastructure.grpcservice.impl;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adrmanagement.common.enums.GenericResponseEnum;
import com.adrmanagement.common.infrastructure.grpcservice.GenericResponseOuterClass.GenericResponse;
import com.adrmanagement.common.member.domain.commands.MemberUpdateCommand;
import com.adrmanagement.member.domain.service.commandservice.MemberUpdateCommandService;
import com.adrmanagement.member.domain.service.queryservice.MemberGetQueryService;
import com.adrmanagement.member.infrastructure.grpcservice.MemberUpdateRequest;
import com.adrmanagement.member.infrastructure.grpcservice.MemberUpdateServiceGrpc.MemberUpdateServiceImplBase;
import com.adrmanagement.member.infrastructure.grpcservice.assmbler.MemberUpdateAssembler;

import io.grpc.stub.StreamObserver;
@GRpcService
public class MemberUpdateServiceGrpcImpl extends MemberUpdateServiceImplBase{
	
	Logger log = LoggerFactory.getLogger(MemberUpdateServiceGrpcImpl.class);
	
	@Autowired
	private MemberUpdateCommandService memberUpdateCommandService;
	
	@Autowired
	private MemberGetQueryService memberGetQueryService;
	
	@Override
	public void update(MemberUpdateRequest memberUpdateRequest, StreamObserver<GenericResponse> responseObserver) {
		try {
			MemberUpdateCommand memberUpdateCommand = MemberUpdateAssembler.rquestToCommand(memberUpdateRequest);
			memberUpdateCommandService.update(memberUpdateCommand);
			responseObserver.onNext(GenericResponseEnum.success());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			responseObserver.onNext(GenericResponseEnum.error());
			responseObserver.onCompleted();
		}
		
		
	}

}
