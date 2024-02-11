package com.adrmanagement.adr.domain.service.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.adrmanagement.adr.domain.model.entity.AdrRecordItem;

@Repository
public interface AdrRecordItemRepository  {
	void save(AdrRecordItem adrRecordItem) throws Exception;
	AdrRecordItem findByAdrId(Long adrId)  throws Exception;
	List<AdrRecordItem> findByEditorMemberId(Long editorMemberId);
	public List<AdrRecordItem> findAll(AdrRecordItem adrRecordItem) throws Exception;
}
