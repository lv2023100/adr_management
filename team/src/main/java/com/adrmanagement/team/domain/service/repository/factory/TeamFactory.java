package com.adrmanagement.team.domain.service.repository.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.adrmanagement.team.domain.model.aggregate.TeamAggregate;
import com.adrmanagement.team.domain.model.entity.Team;
import com.adrmanagement.team.domain.model.entity.TeamMember;
import com.adrmanagement.team.domain.service.command.TeamCreateCommand;
import com.adrmanagement.team.domain.service.command.TeamUpdateCommand;

public class TeamFactory {

	public static Team teamCreateCommandToTeam(TeamCreateCommand teamCreateCommand) {
		Team team = new Team();
		BeanUtils.copyProperties(teamCreateCommand, team);
		List<TeamMember> teamMemberList = new ArrayList<TeamMember>();
		if (!CollectionUtils.isEmpty(teamCreateCommand.getMemberIdList())) {
			teamMemberList = teamCreateCommand.getMemberIdList().stream().map(memberId -> {
				TeamMember teamMember = new TeamMember();
				teamMember.setMemberId(memberId);
				teamMember.setTeam(team);
				return teamMember;
			}).collect(Collectors.toList());
		}
		team.setTeamMemberList(teamMemberList);
		return team;
	}

	public static Team teamUpdateCommandToTeam(TeamUpdateCommand teamUpdateCommand, Team team) {
		team.setName(teamUpdateCommand.getName());
		team.setUpdateTime(teamUpdateCommand.getUpdateTime());
		return team;
	}

	public static TeamAggregate teamToAggregate(Team team) {
		TeamAggregate teamAggregate = new TeamAggregate();
		BeanUtils.copyProperties(team, teamAggregate);
		List<Long> memberIdList = new ArrayList<Long>();
		if (!CollectionUtils.isEmpty(team.getTeamMemberList())) {
			memberIdList = team.getTeamMemberList().stream().map(TeamMember::getMemberId).collect(Collectors.toList());
		}
		teamAggregate.setMemberIdList(memberIdList);
		return teamAggregate;
	}

	public static List<TeamAggregate> teamMemberToAggregateList(List<TeamMember> teamMemberList) {
		List<TeamAggregate> teamAggregateList = teamMemberList.stream().map(teamMember -> {
			TeamAggregate teamAggregate = new TeamAggregate();
			BeanUtils.copyProperties(teamMember.getTeam(), teamAggregate);
			return teamAggregate;
		}).collect(Collectors.toList());
		return teamAggregateList;
	}

}
