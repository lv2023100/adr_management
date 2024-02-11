package com.adrmanagement.team.domain.service.queryservice;

import java.util.List;

import com.adrmanagement.team.domain.model.aggregate.TeamAggregate;
import com.adrmanagement.team.domain.service.query.TeamGetQuery;

public interface TeamGetQueryService {
	
	public TeamAggregate getTeam(TeamGetQuery teamGetQuery) throws Exception;
	public List<TeamAggregate> getTeamListByMemberId(Long memberId) throws Exception;
	public List<TeamAggregate> getTeamList() throws Exception;

}
