package com.adrmanagement.team.application.service.commandservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.adrmanagement.team.domain.model.entity.Team;
import com.adrmanagement.team.domain.service.command.TeamUpdateCommand;
import com.adrmanagement.team.domain.service.commandservice.TeamUpdateCommandService;
import com.adrmanagement.team.domain.service.repository.TeamMemberRepository;
import com.adrmanagement.team.domain.service.repository.TeamRepository;
import com.adrmanagement.team.domain.service.repository.factory.TeamFactory;
@Service
public class TeamUpdateCommandServiceImpl implements TeamUpdateCommandService{
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private TeamMemberRepository teamMemberRepository;

	@Override
	public void update(TeamUpdateCommand teamUpdateCommand) throws Exception {
		Team team = teamRepository.findById(teamUpdateCommand.getTeamId());
		
		team = TeamFactory.teamUpdateCommandToTeam(teamUpdateCommand, team);
		teamRepository.save(team);
		teamMemberRepository.deleteByTeamId(teamUpdateCommand.getTeamId());
		if(!CollectionUtils.isEmpty(teamUpdateCommand.getMemberIdList())) {
			teamMemberRepository.addMemberList(teamUpdateCommand.getMemberIdList(), team);
		}
	}

	

}
