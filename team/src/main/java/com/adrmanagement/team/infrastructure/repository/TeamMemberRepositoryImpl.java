package com.adrmanagement.team.infrastructure.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adrmanagement.team.domain.model.entity.Team;
import com.adrmanagement.team.domain.model.entity.TeamMember;
import com.adrmanagement.team.domain.service.repository.TeamMemberRepository;
import com.adrmanagement.team.infrastructure.repository.mapper.TeamMemberMapper;

@Repository
public class TeamMemberRepositoryImpl implements TeamMemberRepository{
	
	@Autowired
	private TeamMemberMapper teamMemberMapper;

	@Override
	public void deleteByTeamId(Long teamId) throws Exception {
		teamMemberMapper.deleteByTeamId(teamId);
		
	}

	@Override
	public void addMemberList(List<Long> memberIdList, Team team) throws Exception {
		memberIdList.stream()
	    .forEach(memberId -> {
	        TeamMember teamMember = new TeamMember();
	        teamMember.setMemberId(memberId);
	        teamMember.setTeam(team);
	        teamMemberMapper.save(teamMember);
	    });
		
	}
	
	@Override
	public List<TeamMember> findByMemberId(Long memberId) throws Exception {
		List<TeamMember> teamMemberList = teamMemberMapper.findByMemberId(memberId);
		return teamMemberList;
	}

}
