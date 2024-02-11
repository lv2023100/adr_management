package com.adrmanagement.adr.infrastructure.repository.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adrmanagement.adr.domain.model.entity.AdrDecisionMember;
@Repository
public interface AdrDecisionMemberMapper extends JpaRepository<AdrDecisionMember, Long> {
	
	@Modifying
	@Query("delete from AdrDecisionMember adm where adm.adrRecord.adrId=:adrId")
	void deleteByAdrId(@Param("adrId") Long adrId);

}
