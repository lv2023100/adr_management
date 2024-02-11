package com.adrmanagement.team.infrastructure.grpcservice.impl;

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
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListByMemberIdRequest;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListByMemberIdResponse;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListByMemberIdServiceGrpc.TeamGetListByMemberIdServiceImplBase;

import io.grpc.stub.StreamObserver;

@GRpcService
public class TeamGetListByMemberIdServiceImpl extends TeamGetListByMemberIdServiceImplBase {

	Logger log = LoggerFactory.getLogger(TeamGetListByMemberIdServiceImpl.class);

	@Autowired
	private TeamGetQueryService teamGetQueryService;

	@Override
	public void get(TeamGetListByMemberIdRequest teamGetListByMemberIdRequest,
			StreamObserver<TeamGetListByMemberIdResponse> responseObserver) {
		var teamGetListByMemberIdResponse = TeamGetListByMemberIdResponse.newBuilder();
		try {
			List<TeamAggregate> teamAggregateList = teamGetQueryService
					.getTeamListByMemberId(teamGetListByMemberIdRequest.getMemberId());
			List<TeamGrpcDto> teamList = teamAggregateList.stream().map(teamAggregate -> {
				var teamBuilder = TeamGrpcDto.newBuilder();
				try {
					ProtoBeanUtils.toProtoBean(teamBuilder, teamAggregate);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
				return teamBuilder.build();
			}).collect(Collectors.toList());
			teamGetListByMemberIdResponse.addAllTeamList(teamList);
			teamGetListByMemberIdResponse.setGenericResponse(GenericResponseEnum.success());
			responseObserver.onNext(teamGetListByMemberIdResponse.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			teamGetListByMemberIdResponse.setGenericResponse(GenericResponseEnum.error());
			responseObserver.onCompleted();

		}

	}

}
