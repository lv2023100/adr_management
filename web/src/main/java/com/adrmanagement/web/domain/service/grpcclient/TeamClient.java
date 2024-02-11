package com.adrmanagement.web.domain.service.grpcclient;

import com.adrmanagement.team.infrastructure.grpcservice.TeamCreateRequest;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListByMemberIdResponse;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListResponse;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetResponse;
import com.adrmanagement.team.infrastructure.grpcservice.TeamUpdateRequest;

public interface TeamClient {
	
	public TeamGetResponse getTeam(Long teamId) throws Exception;
	public void update(TeamUpdateRequest teamUpdateRequest) throws Exception;
	public void create(TeamCreateRequest teamCreateRequest) throws Exception;
	public TeamGetListByMemberIdResponse getTeamListByMemberId(Long memberId) throws Exception;
	public TeamGetListResponse getTeamList() throws Exception;

}
