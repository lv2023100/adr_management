package com.adrmanagement.team.infrastructure.repository.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adrmanagement.team.domain.model.entity.TeamMember;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface TeamMemberMapper extends JpaRepository<TeamMember, Long> {
	
	@Modifying
	@Query("delete from TeamMember tm where tm.team.teamId=:teamId")
	void deleteByTeamId(@Param("teamId") Long teamId);
	
	@Query("select tm from TeamMember tm where tm.memberId=:memberId")
	List<TeamMember> findByMemberId(@Param("memberId") Long memberId);

}
