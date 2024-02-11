package com.adrmanagement.team.domain.service.queryservice;

import com.adrmanagement.team.domain.model.aggregate.TeamAggregate;
import com.adrmanagement.team.domain.service.query.TeamGetQuery;

public interface TeamMemberGetQueryService {
	
	public TeamAggregate getMember(TeamGetQuery teamGetQuery) throws Exception;

}
