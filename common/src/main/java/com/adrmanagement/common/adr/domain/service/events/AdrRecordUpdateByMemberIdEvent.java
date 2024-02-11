package com.adrmanagement.common.adr.domain.service.events;

public class AdrRecordUpdateByMemberIdEvent {
	
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
