package com.adrmanagement.adr.infrastructure.repository.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adrmanagement.adr.domain.model.entity.AdrRecord;
import com.adrmanagement.adr.domain.model.entity.AdrRecordItem;
@Repository
public interface AdrRecordMapper extends JpaRepository<AdrRecord, Long>{
	
	@Query(value = "SELECT ar FROM AdrRecord ar where ar.editorMemberId= :memberId")
	List<AdrRecord> findByMemberId(@Param("memberId") Long memberId);
	
	@Query(value = "SELECT ar FROM AdrRecord ar where ar.teamId= :teamId")
	List<AdrRecord> findByTeamId(@Param("teamId") Long teamId);

}
