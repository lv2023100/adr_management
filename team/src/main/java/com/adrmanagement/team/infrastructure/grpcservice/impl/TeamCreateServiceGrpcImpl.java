package com.adrmanagement.team.infrastructure.grpcservice.impl;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adrmanagement.common.enums.GenericResponseEnum;
import com.adrmanagement.common.infrastructure.grpcservice.GenericResponseOuterClass.GenericResponse;
import com.adrmanagement.team.domain.service.command.TeamCreateCommand;
import com.adrmanagement.team.domain.service.commandservice.TeamCreateCommandService;
import com.adrmanagement.team.infrastructure.grpcservice.TeamCreateRequest;
import com.adrmanagement.team.infrastructure.grpcservice.TeamCreateServiceGrpc.TeamCreateServiceImplBase;
import com.adrmanagement.team.infrastructure.grpcservice.assmbler.TeamCreateAssembler;

import io.grpc.stub.StreamObserver;
@GRpcService
public class TeamCreateServiceGrpcImpl extends TeamCreateServiceImplBase{
	
	Logger log = LoggerFactory.getLogger(TeamCreateServiceGrpcImpl.class);
	
	@Autowired
	private TeamCreateCommandService teamCreateCommandService;
	
	@Override
	public void create(TeamCreateRequest teamCreateRequest, StreamObserver<GenericResponse> responseObserver) {
		try {
			TeamCreateCommand teamCreateCommand = TeamCreateAssembler.rquestToCommand(teamCreateRequest);
			teamCreateCommandService.create(teamCreateCommand);
			responseObserver.onNext(GenericResponseEnum.success());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			responseObserver.onNext(GenericResponseEnum.error());
			responseObserver.onCompleted();
		}
		
		
	}

}
