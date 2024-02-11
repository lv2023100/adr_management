package com.adrmanagement.team.infrastructure.repository.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adrmanagement.team.domain.model.entity.Team;

public interface TeamMapper extends JpaRepository<Team, Long>{

}
