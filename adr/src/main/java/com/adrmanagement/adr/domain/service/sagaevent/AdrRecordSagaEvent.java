package com.adrmanagement.adr.domain.service.sagaevent;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.adrmanagement.adr.domain.model.entity.AdrRecordItem;
import com.adrmanagement.adr.domain.service.repository.AdrRecordItemRepository;
import com.adrmanagement.common.adr.domain.commands.AdrRecordItemPublishCommand;
import com.adrmanagement.common.adr.domain.commands.AdrRecordItemRepublishCommand;
import com.adrmanagement.common.adr.domain.commands.AdrRecordPublishCommand;
import com.adrmanagement.common.adr.domain.enums.AdrRecordStatus;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordCreateEvent;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordItemPublishEvent;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordPublishEvent;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordUpdateEvent;

@Saga
public class AdrRecordSagaEvent {
	
	@Autowired
	private transient CommandGateway commandGateway;
	
	@Autowired
	private transient AdrRecordItemRepository adrRecordItemRepository;
	
	transient Logger log = LoggerFactory.getLogger(AdrRecordSagaEvent.class);
	

    
    @StartSaga
    @SagaEventHandler(associationProperty = "adrId")
    public void handle(AdrRecordCreateEvent adrRecordCreateEvent){
    	try {
    		log.info("AdrRecordCreateEvent saga handle AdrId:{}", adrRecordCreateEvent.getAdrId());
    		if(adrRecordCreateEvent.getStatus()!= null
    				&& adrRecordCreateEvent.getStatus().name().equals(AdrRecordStatus.CREATED.name())) {
    			AdrRecordItemPublishCommand adrRecordItemPublishCommand = new AdrRecordItemPublishCommand();
    			BeanUtils.copyProperties(adrRecordCreateEvent, adrRecordItemPublishCommand);
    			commandGateway.send(adrRecordItemPublishCommand);
    		}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
    	
    }
    
    @StartSaga
    @SagaEventHandler(associationProperty = "adrId")
    public void handle(AdrRecordUpdateEvent adrRecordUpdateEvent){
    	try {
    		log.info("AdrRecordUpdateEvent saga handle AdrId:{}", adrRecordUpdateEvent.getAdrId());
    		if(adrRecordUpdateEvent.getStatus()!= null
    				&& adrRecordUpdateEvent.getStatus().name().equals(AdrRecordStatus.UNPUBLISHED.name())) {
    			AdrRecordItem adrRecordItem = adrRecordItemRepository.findByAdrId(adrRecordUpdateEvent.getAdrId());
    			AdrRecordItemRepublishCommand adrRecordItemRepublishCommand = new AdrRecordItemRepublishCommand();
    			BeanUtils.copyProperties(adrRecordItem, adrRecordItemRepublishCommand);
    			BeanUtils.copyProperties(adrRecordUpdateEvent, adrRecordItemRepublishCommand);
    			adrRecordItemRepublishCommand.setAdrItemId(adrRecordItem.getAdrItemId());
    			commandGateway.send(adrRecordItemRepublishCommand);
    		}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
    	
    }
    
    @SagaEventHandler(associationProperty = "adrId")
    public void handle(AdrRecordItemPublishEvent adrRecordItemPublishEvent){
    	try {
    		log.info("AdrRecordItemPublishEvent saga handle AdrItemId:{} AdrId:{}",
    				adrRecordItemPublishEvent.getAdrItemId() , adrRecordItemPublishEvent.getAdrId());
    		AdrRecordPublishCommand adrRecordPublishCommand = new AdrRecordPublishCommand();
    		adrRecordPublishCommand.setAdrId(adrRecordItemPublishEvent.getAdrId());
    		commandGateway.send(adrRecordPublishCommand);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
    	
    }
    

    
    @SagaEventHandler(associationProperty = "adrId")
    @EndSaga
    public void handle(AdrRecordPublishEvent adrRecordPublishEvent){
    	log.info("AdrRecordPublishEvent saga handle AdrId:{}", adrRecordPublishEvent.getAdrId());
    }

}
