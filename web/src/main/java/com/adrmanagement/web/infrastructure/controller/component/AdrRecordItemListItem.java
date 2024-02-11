package com.adrmanagement.web.infrastructure.controller.component;

import com.adrmanagement.common.adr.domain.enums.AdrRecordProgressStatus;

public class AdrRecordItemListItem {
	
	private Long adrId;
	
	private String title;
	
	private AdrRecordProgressStatus progressStatus;
	
	private String updateTime;
	
	private String createTime;
	
	private String editorMemberName;

	public Long getAdrId() {
		return adrId;
	}

	public void setAdrId(Long adrId) {
		this.adrId = adrId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEditorMemberName() {
		return editorMemberName;
	}

	public void setEditorMemberName(String editorMemberName) {
		this.editorMemberName = editorMemberName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AdrRecordProgressStatus getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(AdrRecordProgressStatus progressStatus) {
		this.progressStatus = progressStatus;
	}

}
