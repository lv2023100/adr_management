package com.adrmanagement.team.infrastructure.grpcservice.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adrmanagement.common.enums.GenericResponseEnum;
import com.adrmanagement.common.util.ProtoBeanUtils;
import com.adrmanagement.team.domain.model.aggregate.TeamAggregate;
import com.adrmanagement.team.domain.service.queryservice.TeamGetQueryService;
import com.adrmanagement.team.infrastructure.grpcservice.Team.TeamGrpcDto;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListRequest;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListResponse;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListServiceGrpc.TeamGetListServiceImplBase;

import io.grpc.stub.StreamObserver;

@GRpcService
public class TeamGetListServiceGrpcImpl extends TeamGetListServiceImplBase {

	Logger log = LoggerFactory.getLogger(TeamGetListServiceGrpcImpl.class);

	@Autowired
	private TeamGetQueryService teamGetQueryService;

	@Override
	public void get(TeamGetListRequest teamGetListRequest, StreamObserver<TeamGetListResponse> responseObserver) {
		var teamGetListResponse = TeamGetListResponse.newBuilder();
		try {
			List<TeamAggregate> teamAggregateList = teamGetQueryService.getTeamList();
			List<TeamGrpcDto> teamList = teamAggregateList.stream().map(teamAggregate -> {
				var teamBuilder = TeamGrpcDto.newBuilder();
				try {
					ProtoBeanUtils.toProtoBean(teamBuilder, teamAggregate);
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
				return teamBuilder.build();
			}).collect(Collectors.toList());

			teamGetListResponse.addAllTeamList(teamList);
			teamGetListResponse.setGenericResponse(GenericResponseEnum.success());
			responseObserver.onNext(teamGetListResponse.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			teamGetListResponse.setGenericResponse(GenericResponseEnum.error());
			responseObserver.onCompleted();

		}

	}

}
