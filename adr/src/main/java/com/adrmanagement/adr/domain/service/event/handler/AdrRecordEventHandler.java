package com.adrmanagement.adr.domain.service.event.handler;

import java.util.List;

import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.adrmanagement.adr.domain.model.entity.AdrRecord;
import com.adrmanagement.adr.domain.service.repository.AdrDecisionMemberRepository;
import com.adrmanagement.adr.domain.service.repository.AdrRecordRepository;
import com.adrmanagement.adr.domain.service.repository.factory.AdrRecordFactory;
import com.adrmanagement.common.adr.domain.commands.AdrRecordItemRepublishCommand;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordCreateEvent;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordPublishEvent;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordUpdateByMemberIdEvent;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordUpdateEvent;

@Component
public class AdrRecordEventHandler {
	
	Logger log = LoggerFactory.getLogger(AdrRecordEventHandler.class);
	
	@Autowired
	private AdrRecordRepository adrRecordRepository;
	
	
	@EventHandler
    public void on(AdrRecordCreateEvent adrRecordCreateEvent) {	
		try {
			log.info("AdrRecordCreateEvent handle AdrId:{}", adrRecordCreateEvent.getAdrId());
			AdrRecord adrRecord = AdrRecordFactory.adrRecordCreateEventToAdrRecord(adrRecordCreateEvent);
			adrRecordRepository.save(adrRecord);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		
	}
	
	@EventHandler
    public void on(AdrRecordPublishEvent adrRecordPublishEvent) {	
		try {
			log.info("AdrRecordPublishEvent handle AdrId:{}", adrRecordPublishEvent.getAdrId());
			AdrRecord adrRecord = adrRecordRepository.findById(adrRecordPublishEvent.getAdrId());
			adrRecord.setStatus(adrRecordPublishEvent.getStatus());
			adrRecordRepository.save(adrRecord);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		
	}
	
	@EventHandler
    public void on(AdrRecordUpdateEvent adrRecordUpdateEvent) {	
		try {
			log.info("AdrRecordUpdateEvent handle AdrId:{}", adrRecordUpdateEvent.getAdrId());
			AdrRecord adrRecord = adrRecordRepository.findById(adrRecordUpdateEvent.getAdrId());
			adrRecord = AdrRecordFactory.adrRecordUpdateEventToAdrRecord(adrRecordUpdateEvent, adrRecord);
			adrRecordRepository.save(adrRecord);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		
	}
	
//	@EventHandler
//    public void on(AdrRecordUpdateByMemberIdEvent adrRecordUpdateByMemberIdEvent){
//		try {
//			log.info("AdrRecordUpdateByMemberIdEvent handle memberId:{}", adrRecordUpdateByMemberIdEvent.getMemberId());
//			List<AdrRecord> adrRecordList= adrRecordRepository.findByMemberId(adrRecordUpdateByMemberIdEvent.getMemberId());
//			for(AdrRecord adrRecord : adrRecordList) {
//				AdrRecordItemRepublishCommand adrRecordItemRepublishCommand = new AdrRecordItemRepublishCommand();
//				BeanUtils.copyProperties(adrRecord, adrRecordItemRepublishCommand);
//			}
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//		}
//		
//	}

}
