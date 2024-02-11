package com.adrmanagement.team.application.service.commandservice;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrmanagement.team.domain.model.entity.Team;
import com.adrmanagement.team.domain.service.command.TeamCreateCommand;
import com.adrmanagement.team.domain.service.commandservice.TeamCreateCommandService;
import com.adrmanagement.team.domain.service.repository.TeamRepository;
import com.adrmanagement.team.domain.service.repository.factory.TeamFactory;
@Service
public class TeamCreateCommandServiceImpl implements TeamCreateCommandService{
	
	@Autowired
	private TeamRepository teamRepository;

	@Override
	public void create(TeamCreateCommand teamCreateCommand) throws Exception {
		Team team = TeamFactory.teamCreateCommandToTeam(teamCreateCommand);
		teamRepository.save(team);
		
	}

}
