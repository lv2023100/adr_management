package com.adrmanagement.team.infrastructure.grpcservice.impl;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adrmanagement.common.enums.GenericResponseEnum;
import com.adrmanagement.common.infrastructure.grpcservice.GenericResponseOuterClass.GenericResponse;
import com.adrmanagement.team.domain.service.command.TeamUpdateCommand;
import com.adrmanagement.team.domain.service.commandservice.TeamUpdateCommandService;
import com.adrmanagement.team.infrastructure.grpcservice.TeamUpdateRequest;
import com.adrmanagement.team.infrastructure.grpcservice.TeamUpdateServiceGrpc.TeamUpdateServiceImplBase;
import com.adrmanagement.team.infrastructure.grpcservice.assmbler.TeamUpdateAssembler;

import io.grpc.stub.StreamObserver;
@GRpcService
public class TeamUpdateServiceGrpcImpl extends TeamUpdateServiceImplBase{
	
	Logger log = LoggerFactory.getLogger(TeamUpdateServiceGrpcImpl.class);
	
	@Autowired
	private TeamUpdateCommandService teamUpdateCommandService;
	
	@Override
	public void update(TeamUpdateRequest teamUpdateRequest, StreamObserver<GenericResponse> responseObserver) {
		try {
			TeamUpdateCommand teamUpdateCommand = TeamUpdateAssembler.rquestToCommand(teamUpdateRequest);
			teamUpdateCommandService.update(teamUpdateCommand);
			responseObserver.onNext(GenericResponseEnum.success());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			responseObserver.onNext(GenericResponseEnum.error());
			responseObserver.onCompleted();
		}
		
		
	}

}
