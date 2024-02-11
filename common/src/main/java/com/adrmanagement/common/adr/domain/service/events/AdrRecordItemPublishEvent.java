package com.adrmanagement.common.adr.domain.service.events;

import java.time.LocalDateTime;
import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.adrmanagement.common.adr.domain.enums.AdrRecordProgressStatus;
import com.adrmanagement.common.adr.domain.enums.AdrRecordStatus;

public class AdrRecordItemPublishEvent {


	public AdrRecordItemPublishEvent() {}
	@TargetAggregateIdentifier
	private Long adrItemId;
	
	private Long adrId;
	
	private Long teamId;
	
	private AdrRecordProgressStatus progressStatus;
	
	private AdrRecordStatus status;
	
	/**上下文**/
	private String context;
	
	/**標題**/
	private String title;
	
	/**決策參與者**/
	private List<Integer> decisionMemberIdList;
	
	/**決策**/
	private String decision;
	
	/**編輯者**/
	private Long editorMemberId;
	
	/**編輯者名字**/
	private String editorMemberName;
	
	/**理由**/
	private String rationale;
	
	/**結果**/
	private String consequences;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Integer> getDecisionMemberIdList() {
		return decisionMemberIdList;
	}

	public void setDecisionMemberIdList(List<Integer> decisionMemberIdList) {
		this.decisionMemberIdList = decisionMemberIdList;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public Long getEditorMemberId() {
		return editorMemberId;
	}

	public void setEditorMemberId(Long editorMemberId) {
		this.editorMemberId = editorMemberId;
	}

	public String getRationale() {
		return rationale;
	}

	public void setRationale(String rationale) {
		this.rationale = rationale;
	}

	public String getConsequences() {
		return consequences;
	}

	public void setConsequences(String consequences) {
		this.consequences = consequences;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public AdrRecordStatus getStatus() {
		return status;
	}

	public void setStatus(AdrRecordStatus status) {
		this.status = status;
	}

	public String getEditorMemberName() {
		return editorMemberName;
	}

	public void setEditorMemberName(String editorMemberName) {
		this.editorMemberName = editorMemberName;
	}

	public Long getAdrItemId() {
		return adrItemId;
	}

	public void setAdrItemId(Long adrItemId) {
		this.adrItemId = adrItemId;
	}

	public Long getAdrId() {
		return adrId;
	}

	public void setAdrId(Long adrId) {
		this.adrId = adrId;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public AdrRecordProgressStatus getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(AdrRecordProgressStatus progressStatus) {
		this.progressStatus = progressStatus;
	}

	

}
