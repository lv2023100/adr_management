package com.adrmanagement.common.adr.domain.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class AdrRecordPublishCommand {
	
	
	
	@TargetAggregateIdentifier
	private Long adrId;

	public Long getAdrId() {
		return adrId;
	}

	public void setAdrId(Long adrId) {
		this.adrId = adrId;
	}




}
