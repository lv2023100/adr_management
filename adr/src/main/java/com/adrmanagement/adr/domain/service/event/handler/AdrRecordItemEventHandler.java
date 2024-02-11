package com.adrmanagement.adr.domain.service.event.handler;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adrmanagement.adr.domain.model.aggregate.AdrRecordItemAggregate;
import com.adrmanagement.adr.domain.model.entity.AdrRecordItem;
import com.adrmanagement.adr.domain.service.repository.AdrRecordItemRepository;
import com.adrmanagement.common.adr.domain.query.AdrRecordItemGetListQuery;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordItemPublishEvent;

@Component
public class AdrRecordItemEventHandler {
	
	Logger log = LoggerFactory.getLogger(AdrRecordItemEventHandler.class);
	
	@Autowired
	private AdrRecordItemRepository adrRecordItemRepository;
	
	@EventHandler
    public void on(AdrRecordItemPublishEvent adrRecordItemPublishEvent) {	
		try {
			log.info("AdrRecordItemPublishEvent handle AdrItemId:{} AdrId:{}"
					,adrRecordItemPublishEvent.getAdrItemId(), adrRecordItemPublishEvent.getAdrId());
			AdrRecordItem adrRecordItem = new AdrRecordItem();
			BeanUtils.copyProperties(adrRecordItemPublishEvent, adrRecordItem);
			adrRecordItemRepository.save(adrRecordItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
