package com.adrmanagement.team.application.service.queryservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrmanagement.team.domain.model.aggregate.TeamAggregate;
import com.adrmanagement.team.domain.model.entity.Team;
import com.adrmanagement.team.domain.model.entity.TeamMember;
import com.adrmanagement.team.domain.service.query.TeamGetQuery;
import com.adrmanagement.team.domain.service.queryservice.TeamGetQueryService;
import com.adrmanagement.team.domain.service.repository.TeamMemberRepository;
import com.adrmanagement.team.domain.service.repository.TeamRepository;
import com.adrmanagement.team.domain.service.repository.factory.TeamFactory;
@Service
public class TeamQueryServiceImpl implements TeamGetQueryService{
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private TeamMemberRepository teamMemberRepository;

	@Override
	public TeamAggregate getTeam(TeamGetQuery teamGetQuery) throws Exception {
		Team team= teamRepository.findById(teamGetQuery.getTeamId());
		TeamAggregate teamAggregate = TeamFactory.teamToAggregate(team);
		return teamAggregate;
	}
	
	@Override
	public List<TeamAggregate> getTeamListByMemberId(Long memberId) throws Exception {
		List<TeamMember> teamMemberList = teamMemberRepository.findByMemberId(memberId);
		List<TeamAggregate> teamAggregateList = TeamFactory.teamMemberToAggregateList(teamMemberList);
		return teamAggregateList;
	}
	
	@Override
	public List<TeamAggregate> getTeamList() throws Exception {
		List<Team> teamList = teamRepository.findAll();
		List<TeamAggregate> teamAggregateList = teamList.stream()
			    .map(TeamFactory::teamToAggregate)
			    .collect(Collectors.toList());
		return teamAggregateList;
	}
	
	

}
