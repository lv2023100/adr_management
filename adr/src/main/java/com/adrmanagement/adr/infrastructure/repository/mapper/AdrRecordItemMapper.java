package com.adrmanagement.adr.infrastructure.repository.mapper;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adrmanagement.adr.domain.model.entity.AdrRecordItem;
@Repository
public interface AdrRecordItemMapper extends JpaRepository<AdrRecordItem, Long>{
	
	@Query(value = "SELECT ari FROM AdrRecordItem ari where ari.adrId= :adrId")
	Optional<AdrRecordItem> findByAdrId(@Param("adrId") Long adrId);
	
	@Query(value = "SELECT ari FROM AdrRecordItem ari where ari.editorMemberId= :editorMemberId")
	List<AdrRecordItem> findByEditorMemberId(@Param("editorMemberId") Long editorMemberId);

}
