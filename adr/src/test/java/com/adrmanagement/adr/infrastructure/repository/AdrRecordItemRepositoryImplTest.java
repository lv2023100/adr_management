package com.adrmanagement.adr.infrastructure.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.adrmanagement.adr.domain.model.entity.AdrRecordItem;
import com.adrmanagement.adr.domain.service.repository.AdrRecordItemRepository;
import com.adrmanagement.common.adr.domain.enums.AdrRecordProgressStatus;
import com.adrmanagement.common.util.SequenceGenerator;

@DataJpaTest
@Import({AdrRecordItemRepositoryImpl.class})
public class AdrRecordItemRepositoryImplTest{
	
	@Autowired
	private AdrRecordItemRepository adrRecordItemRepository;
	
	AdrRecordItem adrRecordItem1 = null;
	
	@BeforeEach
	public void setUp() throws Exception{
		Long ardId = SequenceGenerator.genId();
		Long ardItemId = SequenceGenerator.genId();
		AdrRecordItem adrRecordItem1 = new AdrRecordItem();
		adrRecordItem1.setAdrItemId(ardItemId);
		adrRecordItem1.setAdrId(ardId);
		adrRecordItem1.setEditorMemberId(1L);
		adrRecordItem1.setConsequences("The consequences of this decision...");
		adrRecordItem1.setContext("In a microservices architecture...");
		adrRecordItem1.setProgressStatus(AdrRecordProgressStatus.DRAFT);
		adrRecordItemRepository.save(adrRecordItem1);
		this.adrRecordItem1 = adrRecordItem1;
	}
	
	@Test
	public void findByAdrId()  throws Exception{
		AdrRecordItem adrRecordItem = adrRecordItemRepository.findByAdrId(adrRecordItem1.getAdrId());
		assertThat(adrRecordItem).isNotNull();
		assertThat(adrRecordItem.getProgressStatus()).isEqualTo(this.adrRecordItem1.getProgressStatus());
		assertThat(adrRecordItem.getStatus()).isEqualTo(this.adrRecordItem1.getStatus());
		assertThat(adrRecordItem.getContext()).isEqualTo(this.adrRecordItem1.getContext());
		assertThat(adrRecordItem.getConsequences()).isEqualTo(this.adrRecordItem1.getConsequences());
		assertThat(adrRecordItem.getAdrId()).isEqualTo(this.adrRecordItem1.getAdrId());
	}
	
	@Test
	void findByEditorMemberId(){
		List<AdrRecordItem> adrRecordItemList = adrRecordItemRepository.findByEditorMemberId(adrRecordItem1.getEditorMemberId());
		assertThat(adrRecordItemList).isNotEmpty();
		assertThat(adrRecordItemList.get(0).getProgressStatus()).isEqualTo(this.adrRecordItem1.getProgressStatus());
		assertThat(adrRecordItemList.get(0).getStatus()).isEqualTo(this.adrRecordItem1.getStatus());
		assertThat(adrRecordItemList.get(0).getContext()).isEqualTo(this.adrRecordItem1.getContext());
		assertThat(adrRecordItemList.get(0).getConsequences()).isEqualTo(this.adrRecordItem1.getConsequences());
		assertThat(adrRecordItemList.get(0).getAdrId()).isEqualTo(this.adrRecordItem1.getAdrId());
	}
	
	@Test
	void findAll() throws Exception {
		AdrRecordItem adrRecordItem = new AdrRecordItem();
		adrRecordItem.setAdrItemId(adrRecordItem1.getAdrItemId());
		adrRecordItem.setAdrId(adrRecordItem1.getAdrId());
		List<AdrRecordItem> adrRecordItemList = adrRecordItemRepository.findAll(adrRecordItem);
		assertThat(adrRecordItemList).isNotEmpty();
		assertThat(adrRecordItemList.get(0).getProgressStatus()).isEqualTo(this.adrRecordItem1.getProgressStatus());
		assertThat(adrRecordItemList.get(0).getStatus()).isEqualTo(this.adrRecordItem1.getStatus());
		assertThat(adrRecordItemList.get(0).getContext()).isEqualTo(this.adrRecordItem1.getContext());
		assertThat(adrRecordItemList.get(0).getConsequences()).isEqualTo(this.adrRecordItem1.getConsequences());
		assertThat(adrRecordItemList.get(0).getAdrId()).isEqualTo(this.adrRecordItem1.getAdrId());
	}


}
