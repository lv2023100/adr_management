package com.adrmanagement.adr.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import com.adrmanagement.adr.domain.model.entity.AdrRecord;
import com.adrmanagement.adr.domain.service.repository.AdrDecisionMemberRepository;
import com.adrmanagement.adr.domain.service.repository.AdrRecordRepository;
import com.adrmanagement.adr.infrastructure.repository.mapper.AdrDecisionMemberMapper;
import com.adrmanagement.adr.infrastructure.repository.mapper.AdrRecordMapper;

import jakarta.persistence.EntityNotFoundException;
@Repository
public class AdrRecordRepositoryImpl implements AdrRecordRepository{
	
	@Autowired
	private AdrRecordMapper adrRecordMapper;
	
	@Override
	public void save(AdrRecord adrRecord) throws Exception{
		adrRecordMapper.save(adrRecord);
	}

	@Override
	public AdrRecord findById(Long id)  throws Exception{

		return adrRecordMapper.findById(id).orElseThrow();

	}
//
//	@Override
//	public List<AdrRecord> findByMemberId(Long memberId) throws Exception {
//		return adrRecordMapper.findByMemberId(memberId);
//	}

	@Override
	public List<AdrRecord> findAll(AdrRecord adrRecord) throws Exception {
		Example<AdrRecord> adrRecordExmple = Example.of(adrRecord);
		return adrRecordMapper.findAll(adrRecordExmple);
	}
	


}
