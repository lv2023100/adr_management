package com.adrmanagement.adr.infrastructure.axon.repository;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrmanagement.adr.domain.model.axon.eventstore.AdrRecordEventStore;
import com.adrmanagement.adr.domain.service.axon.repository.AdrRecordEventStoreRepository;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordCreateEvent;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordUpdateEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class AdrRecordEventStoreRepositoryImpl implements AdrRecordEventStoreRepository{
	
	@Autowired
	private EventStore eventStore;
	
	Logger log = LoggerFactory.getLogger(AdrRecordEventStoreRepositoryImpl.class);
	
	@Autowired
	ObjectMapper mapper;
	
	public List<AdrRecordEventStore> getEventStoreById(Long adrId) {
		DomainEventStream domainEventStream = eventStore.readEvents(adrId.toString());
		List<AdrRecordEventStore> adrRecordEventStoreList = new ArrayList<AdrRecordEventStore>();
		domainEventStream.asStream().filter(event -> event.getPayload() instanceof AdrRecordCreateEvent 
				|| event.getPayload() instanceof AdrRecordUpdateEvent).forEach(event -> {
					try {
						AdrRecordEventStore adrRecordEventStore = mapper
	            		.readValue(mapper.writeValueAsString(event.getPayload()), AdrRecordEventStore.class);
						adrRecordEventStore.setEventName(event.getPayloadType().getSimpleName());
			            adrRecordEventStoreList.add(adrRecordEventStore);
			        } catch (JsonProcessingException e) {
			        	log.error(e.getMessage(), e);
			        }
				});
		return adrRecordEventStoreList;
	}
}
