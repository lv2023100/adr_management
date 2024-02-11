package com.adrmanagement.adr.domain.model.aggregate;


import java.time.LocalDateTime;
import java.util.Arrays;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import com.adrmanagement.common.adr.domain.commands.AdrRecordCreateCommand;
import com.adrmanagement.common.adr.domain.commands.AdrRecordPublishCommand;
import com.adrmanagement.common.adr.domain.commands.AdrRecordUpdateCommand;
import com.adrmanagement.common.adr.domain.enums.AdrRecordProgressStatus;
import com.adrmanagement.common.adr.domain.enums.AdrRecordStatus;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordCreateEvent;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordPublishEvent;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordUpdateEvent;
import com.adrmanagement.common.util.SequenceGenerator;
//@SpringBootTest
public class AdrRecordAggregateTest {
	
	private FixtureConfiguration<AdrRecordAggregate> fixture;
	
	@BeforeEach
	public void setUp() {
	    fixture = new AggregateTestFixture<>(AdrRecordAggregate.class);
	}
	
	@Test
    public void adrRecordCreateCommand() {
		AdrRecordCreateCommand adrRecordCreateCommand = new AdrRecordCreateCommand();
		LocalDateTime now = LocalDateTime.now();
		adrRecordCreateCommand.setProgressStatus(AdrRecordProgressStatus.getAdrRecordProgressStatus("Proposed"));
		adrRecordCreateCommand.setAdrId(SequenceGenerator.genId());
		adrRecordCreateCommand.setCreateTime(now);
		adrRecordCreateCommand.setUpdateTime(now);
		adrRecordCreateCommand.setTitle("testTitle");
		adrRecordCreateCommand.setContext("testContext");
		adrRecordCreateCommand.setConsequences("tesetConsequences");
		adrRecordCreateCommand.setDecision("testDecision");
		adrRecordCreateCommand.setDecisionMemberIdList(Arrays.asList(1L,2L));
		adrRecordCreateCommand.setEditorMemberId(1L);
		adrRecordCreateCommand.setRationale("testRationale");
		adrRecordCreateCommand.setTeamId(1L);
		
		AdrRecordCreateEvent expectEvents = new AdrRecordCreateEvent();
		BeanUtils.copyProperties(adrRecordCreateCommand, expectEvents);
		expectEvents.setStatus(AdrRecordStatus.CREATED);
		
		fixture.givenNoPriorActivity().when(adrRecordCreateCommand)
		.expectSuccessfulHandlerExecution()
		.expectEvents(expectEvents);
	}
	
	@Test
    public void adrRecordPublishCommand() {
		Long adrId = SequenceGenerator.genId();
		AdrRecordCreateEvent adrRecordCreateEvent = new AdrRecordCreateEvent();
		adrRecordCreateEvent.setAdrId(adrId);
		
		AdrRecordPublishCommand adrRecordPublishCommand = new AdrRecordPublishCommand();
		BeanUtils.copyProperties(adrRecordCreateEvent, adrRecordPublishCommand);
		AdrRecordPublishEvent expectEvents = new AdrRecordPublishEvent();
		expectEvents.setAdrId(adrRecordPublishCommand.getAdrId());
		expectEvents.setStatus(AdrRecordStatus.PUBLISHED);
		fixture
		.given(adrRecordCreateEvent)
		.when(adrRecordPublishCommand)
		.expectSuccessfulHandlerExecution()
		.expectEvents(expectEvents);
	}
	
	@Test
    public void adrRecordUpdateCommand() {
		Long adrId = SequenceGenerator.genId();
		AdrRecordCreateEvent adrRecordCreateEvent = new AdrRecordCreateEvent();
		adrRecordCreateEvent.setAdrId(adrId);
		
		LocalDateTime now = LocalDateTime.now();
		AdrRecordUpdateCommand adrRecordUpdateCommand = new AdrRecordUpdateCommand();
		adrRecordUpdateCommand.setUpdateTime(now);
		adrRecordUpdateCommand.setTitle("testTitle");
		adrRecordUpdateCommand.setContext("testContext");
		adrRecordUpdateCommand.setConsequences("tesetConsequences");
		adrRecordUpdateCommand.setDecision("testDecision");
		adrRecordUpdateCommand.setDecisionMemberIdList(Arrays.asList(1L,2L));
		adrRecordUpdateCommand.setEditorMemberId(1L);
		adrRecordUpdateCommand.setRationale("testRationale");
		adrRecordUpdateCommand.setAdrId(adrId);
		
		AdrRecordUpdateEvent expectEvents = new AdrRecordUpdateEvent();
		BeanUtils.copyProperties(adrRecordUpdateCommand, expectEvents);
		expectEvents.setStatus(AdrRecordStatus.UNPUBLISHED);
		
		fixture.given(adrRecordCreateEvent)
		.when(adrRecordUpdateCommand)
		.expectSuccessfulHandlerExecution()
		.expectEvents(expectEvents);
		
	}

}
