package com.adrmanagement.adr.domain.service.event.handler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.adrmanagement.adr.domain.model.entity.AdrRecord;
import com.adrmanagement.adr.domain.model.entity.AdrRecordItem;
import com.adrmanagement.adr.domain.service.repository.AdrRecordItemRepository;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordItemPublishEvent;
import com.adrmanagement.common.util.SequenceGenerator;

public class AdrRecordItemEventHandlerTest {
	
	@InjectMocks
    private AdrRecordItemEventHandler eventHandler;
	
	@Mock
	private AdrRecordItemRepository adrRecordItemRepositoryMock;
	
	@BeforeEach
    public void setUp() {
		MockitoAnnotations.openMocks(this);
    }
	
	@Test
    public void adrRecordCreateEvent() throws Exception {
		Long adrId = SequenceGenerator.genId();
		Long adrItemId = SequenceGenerator.genId();
		AdrRecordItemPublishEvent itemPublishEvent = new AdrRecordItemPublishEvent();
		itemPublishEvent.setAdrId(adrId);
		itemPublishEvent.setAdrItemId(adrItemId);
        eventHandler.on(itemPublishEvent);
        verify(adrRecordItemRepositoryMock, times(1)).save(any(AdrRecordItem.class));
    }

}
