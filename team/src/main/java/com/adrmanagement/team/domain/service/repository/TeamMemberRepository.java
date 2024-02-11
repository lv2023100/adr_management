package com.adrmanagement.team.domain.service.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.adrmanagement.team.domain.model.entity.Team;
import com.adrmanagement.team.domain.model.entity.TeamMember;
@Repository
public interface TeamMemberRepository {
	
	public void deleteByTeamId(Long teamId)throws Exception;
	void addMemberList(List<Long>memberIdList, Team team)throws Exception;
	public List<TeamMember> findByMemberId(Long memberId) throws Exception;

}
