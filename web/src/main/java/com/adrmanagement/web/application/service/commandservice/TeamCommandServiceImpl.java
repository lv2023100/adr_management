package com.adrmanagement.web.application.service.commandservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.adrmanagement.common.util.ProtoBeanUtils;
import com.adrmanagement.team.infrastructure.grpcservice.TeamCreateRequest;
import com.adrmanagement.team.infrastructure.grpcservice.TeamUpdateRequest;
import com.adrmanagement.web.domain.service.commandservice.TeamCommandService;
import com.adrmanagement.web.domain.service.grpcclient.TeamClient;
import com.adrmanagement.web.infrastructure.controller.component.TeamForm;

@Service
public class TeamCommandServiceImpl implements TeamCommandService{
	
	@Autowired
	private TeamClient teamClient;

	@Override
	@Caching(evict = {@CacheEvict(cacheNames = "team", key = "#teamForm.teamId"),
			 @CacheEvict(cacheNames = "teams", allEntries = true)})
	public void update(TeamForm teamForm) throws Exception {
		var teamUpdateRequestBuild = TeamUpdateRequest.newBuilder();
		ProtoBeanUtils.toProtoBean(teamUpdateRequestBuild, teamForm);
		teamClient.update(teamUpdateRequestBuild.build());
		
	}
	
	@Override
	@CacheEvict(cacheNames = "teams", allEntries = true)
	public void create(TeamForm teamForm) throws Exception {
		var teamCreateRequestBuild = TeamCreateRequest.newBuilder();
		ProtoBeanUtils.toProtoBean(teamCreateRequestBuild, teamForm);
		teamClient.create(teamCreateRequestBuild.build());
		
	}

}
