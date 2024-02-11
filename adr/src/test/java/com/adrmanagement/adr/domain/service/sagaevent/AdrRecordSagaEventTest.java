package com.adrmanagement.adr.domain.service.sagaevent;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.axonframework.test.saga.SagaTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

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
import com.adrmanagement.common.util.SequenceGenerator;

public class AdrRecordSagaEventTest {

	SagaTestFixture<AdrRecordSagaEvent> fixture;

	private AdrRecordItemRepository adrRecordItemRepositoryMock;

	@BeforeEach
	public void setUp() {
		fixture = new SagaTestFixture<>(AdrRecordSagaEvent.class);
		adrRecordItemRepositoryMock = mock(AdrRecordItemRepository.class);
		fixture.registerResource(adrRecordItemRepositoryMock);
	}

	@Test
	public void adrRecordCreateEvent() {
		Long adrId = SequenceGenerator.genId();
		AdrRecordCreateEvent adrRecordCreateEvent = new AdrRecordCreateEvent();
		adrRecordCreateEvent.setAdrId(adrId);
		adrRecordCreateEvent.setStatus(AdrRecordStatus.CREATED);

		AdrRecordItemPublishCommand expectCommand = new AdrRecordItemPublishCommand();
		expectCommand.setAdrId(adrId);
		expectCommand.setStatus(AdrRecordStatus.CREATED);
		fixture.givenNoPriorActivity().whenAggregate(adrId.toString()).publishes(adrRecordCreateEvent)
				.expectActiveSagas(1).expectDispatchedCommands(expectCommand);
	}

	@Test
	public void adrRecordUpdateEvent() throws Exception {
		Long adrId = SequenceGenerator.genId();
		Long adrItemId = SequenceGenerator.genId();
		AdrRecordUpdateEvent adrRecordUpdateEvent = new AdrRecordUpdateEvent();
		adrRecordUpdateEvent.setAdrId(adrId);
		adrRecordUpdateEvent.setStatus(AdrRecordStatus.UNPUBLISHED);
		
		AdrRecordItem adrRecordItem = new AdrRecordItem();
		adrRecordItem.setAdrId(adrId);
		adrRecordItem.setAdrItemId(adrItemId);

        when(adrRecordItemRepositoryMock.findByAdrId(adrId)).thenReturn(adrRecordItem);

        AdrRecordItemRepublishCommand adrRecordItemRepublishCommand = new AdrRecordItemRepublishCommand();
        BeanUtils.copyProperties(adrRecordItem, adrRecordItemRepublishCommand);
		BeanUtils.copyProperties(adrRecordUpdateEvent, adrRecordItemRepublishCommand);
        fixture.givenNoPriorActivity()
		.whenAggregate(adrId.toString()).publishes(adrRecordUpdateEvent)
				.expectActiveSagas(1)
				.expectDispatchedCommands(adrRecordItemRepublishCommand);

	}

	@Test
	public void adrRecordItemPublishEvent() {

		Long adrItemId = SequenceGenerator.genId();
		Long adrId = SequenceGenerator.genId();

		AdrRecordCreateEvent adrRecordCreateEvent = new AdrRecordCreateEvent();
		adrRecordCreateEvent.setAdrId(adrId);
		adrRecordCreateEvent.setStatus(AdrRecordStatus.CREATED);

		AdrRecordItemPublishEvent adrRecordItemPublishEvent = new AdrRecordItemPublishEvent();
		adrRecordItemPublishEvent.setAdrItemId(adrItemId);
		adrRecordItemPublishEvent.setAdrId(adrId);

		AdrRecordPublishCommand expectCommand = new AdrRecordPublishCommand();
		expectCommand.setAdrId(adrRecordItemPublishEvent.getAdrId());
		fixture.givenAPublished(adrRecordCreateEvent).whenAggregate(adrItemId.toString())
				.publishes(adrRecordItemPublishEvent).expectActiveSagas(1).expectDispatchedCommands(expectCommand);
	}

	@Test
	public void adrRecordPublishEvent() {

		Long adrItemId = SequenceGenerator.genId();
		Long adrId = SequenceGenerator.genId();

		AdrRecordCreateEvent adrRecordCreateEvent = new AdrRecordCreateEvent();
		adrRecordCreateEvent.setAdrId(adrId);
		adrRecordCreateEvent.setStatus(AdrRecordStatus.CREATED);

		AdrRecordItemPublishEvent adrRecordItemPublishEvent = new AdrRecordItemPublishEvent();
		adrRecordItemPublishEvent.setAdrItemId(adrItemId);
		adrRecordItemPublishEvent.setAdrId(adrId);

		AdrRecordPublishEvent adrRecordPublishEvent = new AdrRecordPublishEvent();
		adrRecordPublishEvent.setAdrId(adrId);
		adrRecordPublishEvent.setStatus(AdrRecordStatus.PUBLISHED);

		fixture.givenAPublished(adrRecordCreateEvent).andThenAPublished(adrRecordItemPublishEvent)
				.whenAggregate(adrItemId.toString()).publishes(adrRecordPublishEvent).expectActiveSagas(0);
	}

}
