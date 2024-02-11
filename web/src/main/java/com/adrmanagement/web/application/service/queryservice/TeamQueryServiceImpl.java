package com.adrmanagement.web.application.service.queryservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.adrmanagement.team.infrastructure.grpcservice.Team.TeamGrpcDto;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetResponse;
import com.adrmanagement.web.domain.service.grpcclient.TeamClient;
import com.adrmanagement.web.domain.service.queryservice.TeamQueryService;

@Service
public class TeamQueryServiceImpl implements TeamQueryService{
	
	@Autowired
	private TeamClient teamClient;

	@Override
	@Cacheable(value="team" ,key="#teamId")
	public TeamGetResponse getTeam(Long teamId) throws Exception {
		return teamClient.getTeam(teamId);
	}
	
	@Override
	public Boolean isExistTeamByMemberId(Long teamId ,Long memberId) throws Exception {
		TeamGetResponse teamGetResponse = teamClient.getTeam(teamId);
		if(CollectionUtils.isEmpty(teamGetResponse.getMemberIdListList())) {
			return false;
		}
		return teamGetResponse.getMemberIdListList().contains(memberId);
	}
	
	@Override
	@Cacheable(value="teams")
	public List<TeamGrpcDto> getTeamList() throws Exception {
		return teamClient.getTeamList().getTeamListList();
	}

}
