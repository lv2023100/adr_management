package com.adrmanagement.common.adr.domain.service.events;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.adrmanagement.common.adr.domain.enums.AdrRecordStatus;

public class AdrRecordPublishEvent {

	public AdrRecordPublishEvent() {};


	public AdrRecordPublishEvent(Long adrId, AdrRecordStatus status) {
		super();
		this.adrId = adrId;
		this.status = status;
	}


	@TargetAggregateIdentifier
	private Long adrId;
	
	private AdrRecordStatus status;

	public AdrRecordStatus getStatus() {
		return status;
	}



	public void setStatus(AdrRecordStatus status) {
		this.status = status;
	}


	public Long getAdrId() {
		return adrId;
	}


	public void setAdrId(Long adrId) {
		this.adrId = adrId;
	}


	

}
