package com.adrmanagement.team.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import com.adrmanagement.team.domain.model.entity.Team;
import com.adrmanagement.team.domain.model.entity.TeamMember;
import com.adrmanagement.team.domain.service.repository.TeamRepository;
import com.adrmanagement.team.infrastructure.repository.mapper.TeamMapper;
import com.adrmanagement.team.infrastructure.repository.mapper.TeamMemberMapper;

@Repository
public class TeamRepositoryImpl implements TeamRepository{
	
	@Autowired
	TeamMapper teamMapper;
	
	@Autowired
	TeamMemberMapper teamMemberMapper;

	@Override
	public void save(Team team) throws Exception {
		teamMapper.save(team);
	}

	@Override
	public Team findById(Long teamId) throws Exception {
		Optional<Team> teamOptional = teamMapper.findById(teamId);
		if(!teamOptional.isEmpty()) {
			return teamOptional.get();
		}
		return null;
	}
	
	
	@Override
	public List<Team> findAll() throws Exception {
		return teamMapper.findAll();
	}

}
