package com.adrmanagement.web.domain.service.queryservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adrmanagement.team.infrastructure.grpcservice.TeamGetResponse;
import com.adrmanagement.team.infrastructure.grpcservice.Team.TeamGrpcDto;
@Service
public interface TeamQueryService {
	
	public TeamGetResponse getTeam(Long teamId) throws Exception;
	public List<TeamGrpcDto> getTeamList() throws Exception;
	public Boolean isExistTeamByMemberId(Long teamId ,Long memberId) throws Exception;

}
