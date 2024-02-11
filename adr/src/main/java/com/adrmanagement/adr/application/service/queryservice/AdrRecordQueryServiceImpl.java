package com.adrmanagement.adr.application.service.queryservice;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrmanagement.adr.domain.model.aggregate.AdrRecordAggregate;
import com.adrmanagement.adr.domain.model.axon.eventstore.AdrRecordEventStore;
import com.adrmanagement.adr.domain.service.axon.repository.AdrRecordEventStoreRepository;
import com.adrmanagement.adr.domain.service.queryservice.AdrRecordQueryService;
import com.adrmanagement.common.adr.domain.query.AdrRecordGetQuery;

@Service
public class AdrRecordQueryServiceImpl implements AdrRecordQueryService{
	
	@Autowired
	private QueryGateway queryGateway;
	
	@Autowired
	private AdrRecordEventStoreRepository adrRecordEventStoreRepository;
	


	@Override
	public AdrRecordAggregate getAdrRecord(AdrRecordGetQuery adrRecordGetQuery) throws Exception{

		return queryGateway.query(adrRecordGetQuery, ResponseTypes.instanceOf(AdrRecordAggregate.class)).join();
		
	}
	
	@Override
	public List<AdrRecordEventStore> getAdrRecordEventList(Long adrId) throws Exception{
		return adrRecordEventStoreRepository.getEventStoreById(adrId);
	}
}
