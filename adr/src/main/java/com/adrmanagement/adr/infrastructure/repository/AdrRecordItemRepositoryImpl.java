package com.adrmanagement.adr.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.adrmanagement.adr.domain.model.entity.AdrRecordItem;
import com.adrmanagement.adr.domain.service.repository.AdrRecordItemRepository;
import com.adrmanagement.adr.infrastructure.repository.mapper.AdrRecordItemMapper;

@Repository
public class AdrRecordItemRepositoryImpl implements AdrRecordItemRepository{
	
	@Autowired
	private AdrRecordItemMapper adrRecordItemMapper;
	
	@Override
	public void save(AdrRecordItem adrRecordItem) throws Exception{
		adrRecordItemMapper.save(adrRecordItem);
	}
	
	@Override
	public AdrRecordItem findByAdrId(Long adrId)  throws Exception{
		try {
			return adrRecordItemMapper.findByAdrId(adrId).orElseThrow();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<AdrRecordItem> findByEditorMemberId(Long editorMemberId) {
		List<AdrRecordItem> adrRecordItem = adrRecordItemMapper.findByEditorMemberId(editorMemberId);
		return adrRecordItem;
	}
	
	@Override
	public List<AdrRecordItem> findAll(AdrRecordItem adrRecordItem) throws Exception {
		Example<AdrRecordItem> adrRecordItemExmple = Example.of(adrRecordItem);
		return adrRecordItemMapper.findAll(adrRecordItemExmple);
	}


}
