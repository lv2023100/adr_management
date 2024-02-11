package com.adrmanagement.team.domain.service.repository;

import java.util.List;

import com.adrmanagement.team.domain.model.entity.Team;

public interface TeamRepository {
	
	void save(Team team) throws Exception;
	Team findById(Long teamId) throws Exception;
	List<Team> findAll() throws Exception;

}
