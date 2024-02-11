package com.adrmanagement.adr.domain.model.aggregate;

import static org.mockito.Mockito.mockStatic;

import java.time.LocalDateTime;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;

import com.adrmanagement.common.adr.domain.commands.AdrRecordItemPublishCommand;
import com.adrmanagement.common.adr.domain.commands.AdrRecordItemRepublishCommand;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordItemPublishEvent;
import com.adrmanagement.common.util.SequenceGenerator;

public class AdrRecordItemAggregateTest {
	
	private FixtureConfiguration<AdrRecordItemAggregate> fixture;
	
	@BeforeEach
	public void setUp() {
	    fixture = new AggregateTestFixture<>(AdrRecordItemAggregate.class);
	}
	
	@Test
    public void adrRecordItemPublishCommand() {
		Long adrId = SequenceGenerator.genId();
		Long adrItemId = SequenceGenerator.genId();
		AdrRecordItemPublishCommand adrRecordItemPublishCommand = new AdrRecordItemPublishCommand();
		LocalDateTime now = LocalDateTime.now();
		adrRecordItemPublishCommand.setAdrId(adrId);
		adrRecordItemPublishCommand.setCreateTime(now);
		adrRecordItemPublishCommand.setTitle("testTitle");
		adrRecordItemPublishCommand.setContext("testContext");
		adrRecordItemPublishCommand.setConsequences("tesetConsequences");
		adrRecordItemPublishCommand.setDecision("testDecision");
		adrRecordItemPublishCommand.setEditorMemberId(1L);
		adrRecordItemPublishCommand.setRationale("testRationale");
		adrRecordItemPublishCommand.setTeamId(1L);
		adrRecordItemPublishCommand.setAdrItemId(adrItemId);
		
		AdrRecordItemPublishEvent expectEvents = new AdrRecordItemPublishEvent();
		BeanUtils.copyProperties(adrRecordItemPublishCommand, expectEvents);
		
		mockStatic(SequenceGenerator.class);
        Mockito.when(SequenceGenerator.genId()).thenReturn(adrItemId);

		
		fixture.givenNoPriorActivity().when(adrRecordItemPublishCommand)
		.expectSuccessfulHandlerExecution()
		.expectEvents(expectEvents);
	}
	
	@Test
    public void adrRecordItemRepublishCommand() {
		AdrRecordItemPublishEvent adrRecordItemPublishEvent = new AdrRecordItemPublishEvent();
		LocalDateTime now = LocalDateTime.now();
		adrRecordItemPublishEvent.setAdrId(SequenceGenerator.genId());
		adrRecordItemPublishEvent.setCreateTime(now);
		adrRecordItemPublishEvent.setTitle("testTitle");
		adrRecordItemPublishEvent.setContext("testContext");
		adrRecordItemPublishEvent.setConsequences("tesetConsequences");
		adrRecordItemPublishEvent.setDecision("testDecision");
		adrRecordItemPublishEvent.setEditorMemberId(1L);
		adrRecordItemPublishEvent.setRationale("testRationale");
		adrRecordItemPublishEvent.setTeamId(1L);
		adrRecordItemPublishEvent.setAdrItemId(SequenceGenerator.genId());
		
		AdrRecordItemRepublishCommand adrRecordItemRepublishCommand = new AdrRecordItemRepublishCommand();
		BeanUtils.copyProperties(adrRecordItemPublishEvent, adrRecordItemRepublishCommand);
		
		AdrRecordItemPublishEvent expectEvents = new AdrRecordItemPublishEvent();
		BeanUtils.copyProperties(adrRecordItemRepublishCommand, expectEvents);
		
		fixture.given(adrRecordItemPublishEvent).when(adrRecordItemRepublishCommand)
		.expectSuccessfulHandlerExecution()
		.expectEvents(expectEvents);
		
	}

}
