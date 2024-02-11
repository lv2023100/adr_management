package com.adrmanagement.adr.domain.service.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.adrmanagement.adr.domain.model.entity.AdrRecord;

@Repository
public interface AdrDecisionMemberRepository {
	
	void deleteByAdrId(Long adrId)throws Exception;
	void addDecisionMemberList(List<Long>decisionMemberList, AdrRecord adrRecord)throws Exception;

}
