package com.adrmanagement.adr.domain.service.queryservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adrmanagement.adr.domain.model.aggregate.AdrRecordAggregate;
import com.adrmanagement.adr.domain.model.axon.eventstore.AdrRecordEventStore;
import com.adrmanagement.common.adr.domain.query.AdrRecordItemGetListQuery;
import com.adrmanagement.common.adr.domain.query.AdrRecordGetQuery;

@Service
public interface AdrRecordQueryService {

	public AdrRecordAggregate getAdrRecord(AdrRecordGetQuery adrRecordGetQuery) throws Exception;
	public List<AdrRecordEventStore> getAdrRecordEventList(Long adrId) throws Exception;

}
