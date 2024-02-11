package com.adrmanagement.adr.infrastructure.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.adrmanagement.adr.domain.model.entity.AdrRecord;
import com.adrmanagement.adr.domain.model.entity.AdrRecordItem;
import com.adrmanagement.adr.domain.service.repository.AdrRecordRepository;
import com.adrmanagement.common.adr.domain.enums.AdrRecordProgressStatus;
import com.adrmanagement.common.adr.domain.enums.AdrRecordStatus;
import com.adrmanagement.common.util.SequenceGenerator;

@DataJpaTest
@Import({AdrRecordRepositoryImpl.class})
public class AdrRecordRepositoryImplTest {
	@Autowired
	private AdrRecordRepository adrRecordRepository;
	
	AdrRecord adrRecord1 = null;
	
	@BeforeEach
	public void setUp() throws Exception{
		Long ardId = SequenceGenerator.genId();
		AdrRecord adrRecord1 = new AdrRecord();
		adrRecord1.setAdrId(ardId);
		adrRecord1.setConsequences("The consequences of this decision...");
		adrRecord1.setContext("In a microservices architecture...");
		adrRecord1.setStatus(AdrRecordStatus.UNPUBLISHED);
		adrRecord1.setProgressStatus(AdrRecordProgressStatus.DRAFT);
		adrRecordRepository.save(adrRecord1);
		this.adrRecord1 = adrRecord1;
	}
	
	@Test
	void findById() throws Exception {
		AdrRecord adrRecord = adrRecordRepository.findById(adrRecord1.getAdrId());
		assertThat(adrRecord).isNotNull();
		assertThat(adrRecord.getProgressStatus()).isEqualTo(this.adrRecord1.getProgressStatus());
		assertThat(adrRecord.getStatus()).isEqualTo(this.adrRecord1.getStatus());
		assertThat(adrRecord.getContext()).isEqualTo(this.adrRecord1.getContext());
		assertThat(adrRecord.getConsequences()).isEqualTo(this.adrRecord1.getConsequences());
		assertThat(adrRecord.getAdrId()).isEqualTo(this.adrRecord1.getAdrId());
	}
	
	@Test
	void findAll() throws Exception {
		AdrRecord adrRecord = new AdrRecord();
		adrRecord.setAdrId(adrRecord1.getAdrId());
		List<AdrRecord> adrRecordList = adrRecordRepository.findAll(adrRecord);
		assertThat(adrRecordList).isNotEmpty();
		assertThat(adrRecordList.get(0).getProgressStatus()).isEqualTo(this.adrRecord1.getProgressStatus());
		assertThat(adrRecordList.get(0).getStatus()).isEqualTo(this.adrRecord1.getStatus());
		assertThat(adrRecordList.get(0).getContext()).isEqualTo(this.adrRecord1.getContext());
		assertThat(adrRecordList.get(0).getConsequences()).isEqualTo(this.adrRecord1.getConsequences());
		assertThat(adrRecordList.get(0).getAdrId()).isEqualTo(this.adrRecord1.getAdrId());
	}
	


}
