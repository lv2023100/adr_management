package com.adrmanagement.team.infrastructure.grpcservice.impl;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.adrmanagement.common.enums.GenericResponseEnum;
import com.adrmanagement.team.domain.model.aggregate.TeamAggregate;
import com.adrmanagement.team.domain.service.command.TeamUpdateCommand;
import com.adrmanagement.team.domain.service.query.TeamGetQuery;
import com.adrmanagement.team.domain.service.queryservice.TeamGetQueryService;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetRequest;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetResponse;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetServiceGrpc.TeamGetServiceImplBase;
import com.adrmanagement.team.infrastructure.grpcservice.assmbler.TeamUpdateAssembler;

import io.grpc.stub.StreamObserver;

@GRpcService
public class TeamGetServiceGrpcImpl extends TeamGetServiceImplBase{
	
	Logger log = LoggerFactory.getLogger(TeamGetServiceGrpcImpl.class);
	
	@Autowired
	private TeamGetQueryService teamGetQueryService;
	
	@Override
	public void get(TeamGetRequest teamGetRequest, StreamObserver<TeamGetResponse> responseObserver) {
		try {
			TeamGetQuery teamGetQuery = new TeamGetQuery();
			teamGetQuery.setTeamId(teamGetRequest.getTeamId());
			TeamAggregate teamAggregate =  teamGetQueryService.getTeam(teamGetQuery);
			var teamGetResponseBulder = TeamGetResponse.newBuilder();
			BeanUtils.copyProperties(teamAggregate, teamGetResponseBulder);
			teamGetResponseBulder.addAllMemberIdList(teamAggregate.getMemberIdList());
			responseObserver.onNext(teamGetResponseBulder.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			responseObserver.onCompleted();
		}
		
		
	}

}
