package com.adrmanagement.common.adr.domain.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class AdrRecordItemPublishByMemberIdCommand {
	@TargetAggregateIdentifier
	private Long memberId;
	
	private String editorMemberName;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getEditorMemberName() {
		return editorMemberName;
	}

	public void setEditorMemberName(String editorMemberName) {
		this.editorMemberName = editorMemberName;
	}


}
