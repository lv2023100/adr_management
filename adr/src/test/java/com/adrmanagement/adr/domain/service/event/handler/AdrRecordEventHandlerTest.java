package com.adrmanagement.adr.domain.service.event.handler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.adrmanagement.adr.domain.model.entity.AdrRecord;
import com.adrmanagement.adr.domain.service.repository.AdrDecisionMemberRepository;
import com.adrmanagement.adr.domain.service.repository.AdrRecordRepository;
import com.adrmanagement.common.adr.domain.enums.AdrRecordStatus;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordCreateEvent;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordPublishEvent;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordUpdateEvent;
import com.adrmanagement.common.util.SequenceGenerator;
public class AdrRecordEventHandlerTest {
	
	@InjectMocks
    private AdrRecordEventHandler eventHandler;
	
	@Mock
	private AdrRecordRepository adrRecordRepositoryMock;
	
	@Mock
	private AdrDecisionMemberRepository adrDecisionMemberRepositoryMock; 
	
	@BeforeEach
    public void setUp() {
		MockitoAnnotations.openMocks(this);
    }
	
	@Test
    public void adrRecordCreateEvent() throws Exception {
		Long adrId = SequenceGenerator.genId();
		AdrRecordCreateEvent createEvent = new AdrRecordCreateEvent();
		createEvent.setAdrId(adrId);
        eventHandler.on(createEvent);
        verify(adrRecordRepositoryMock, times(1)).save(any(AdrRecord.class));
    }
	
	@Test
    public void adrRecordPublishEvent() throws Exception {
		Long adrId = SequenceGenerator.genId();
		AdrRecordPublishEvent publishEvent = new AdrRecordPublishEvent();
		publishEvent.setAdrId(adrId);
		publishEvent.setStatus(AdrRecordStatus.PUBLISHED);
		
		AdrRecord adrRecord = new AdrRecord();
		adrRecord.setAdrId(adrId);
		when(adrRecordRepositoryMock.findById(adrId)).thenReturn(adrRecord);
        eventHandler.on(publishEvent);

        verify(adrRecordRepositoryMock, times(1)).save(any(AdrRecord.class));
	}
	
	@Test
    public void adrRecordUpdateEvent() throws Exception {
		Long adrId = SequenceGenerator.genId();
		AdrRecordUpdateEvent updateEvent = new AdrRecordUpdateEvent();
		updateEvent.setAdrId(adrId);
		updateEvent.setDecisionMemberIdList(Arrays.asList(1L,2L,3L));
		AdrRecord adrRecord = new AdrRecord();
		adrRecord.setAdrId(adrId);
	    when(adrRecordRepositoryMock.findById(adrId)).thenReturn(adrRecord);
        eventHandler.on(updateEvent);
        
        verify(adrRecordRepositoryMock, times(1)).save(adrRecord);
        
	}

}
