package com.adrmanagement.web.infrastructure.grpcclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adrmanagement.team.infrastructure.grpcservice.TeamCreateRequest;
import com.adrmanagement.team.infrastructure.grpcservice.TeamCreateServiceGrpc.TeamCreateServiceBlockingStub;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListByMemberIdRequest;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListByMemberIdResponse;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListByMemberIdServiceGrpc.TeamGetListByMemberIdServiceBlockingStub;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListRequest;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListResponse;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListServiceGrpc.TeamGetListServiceBlockingStub;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetRequest;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetResponse;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetServiceGrpc.TeamGetServiceBlockingStub;
import com.adrmanagement.team.infrastructure.grpcservice.TeamUpdateRequest;
import com.adrmanagement.team.infrastructure.grpcservice.TeamUpdateServiceGrpc.TeamUpdateServiceBlockingStub;
import com.adrmanagement.web.domain.service.grpcclient.TeamClient;

@Component
public class TeamClientImpl implements TeamClient{
	
	@Autowired
	private  TeamGetServiceBlockingStub teamGetServiceBlockingStub;
	
	@Autowired
	private  TeamUpdateServiceBlockingStub teamUpdateServiceBlockingStub;
	
	@Autowired
	private TeamCreateServiceBlockingStub teamCreateServiceBlockingStub;
	
	@Autowired
	private TeamGetListByMemberIdServiceBlockingStub teamGetListByMemberIdServiceBlockingStub;

	@Autowired
	private TeamGetListServiceBlockingStub teamGetListServiceBlockingStub;
	
	@Override
	public TeamGetResponse getTeam(Long teamId) throws Exception {
		var teamRequestBuild= TeamGetRequest.newBuilder()
				.setTeamId(teamId);
		return teamGetServiceBlockingStub.get(teamRequestBuild.build());
		
	}
	
	@Override
	public TeamGetListByMemberIdResponse getTeamListByMemberId(Long memberId) throws Exception {
		var teamRequestBuild= TeamGetListByMemberIdRequest.newBuilder()
				.setMemberId(memberId);
		return teamGetListByMemberIdServiceBlockingStub.get(teamRequestBuild.build());
		
	}
	
	@Override
	public TeamGetListResponse getTeamList() throws Exception {
		var teamRequestBuild= TeamGetListRequest.newBuilder();
		return teamGetListServiceBlockingStub.get(teamRequestBuild.build());
		
	}
	
	@Override
	public void update(TeamUpdateRequest teamUpdateRequest) throws Exception {
		teamUpdateServiceBlockingStub.update(teamUpdateRequest);
	}
	
	@Override
	public void create(TeamCreateRequest teamCreateRequest) throws Exception {
		teamCreateServiceBlockingStub.create(teamCreateRequest);
	}

}
