package com.adrmanagement.adr.domain.service.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.adrmanagement.adr.domain.model.entity.AdrRecord;

@Repository
public interface AdrRecordRepository  {
	void save(AdrRecord adrRecord) throws Exception;
	AdrRecord findById(Long id) throws Exception;
//	List<AdrRecord> findByMemberId(Long memberId) throws Exception;
	List<AdrRecord> findAll(AdrRecord adrRecord) throws Exception;
}
