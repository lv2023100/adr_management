package com.adrmanagement.adr.domain.service.axon.repository;

import java.util.List;

import com.adrmanagement.adr.domain.model.axon.eventstore.AdrRecordEventStore;

public interface AdrRecordEventStoreRepository {
	
	public List<AdrRecordEventStore> getEventStoreById(Long adrId);

}
