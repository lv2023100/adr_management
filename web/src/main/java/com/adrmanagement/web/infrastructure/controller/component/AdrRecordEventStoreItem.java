package com.adrmanagement.web.infrastructure.controller.component;

import java.time.LocalDateTime;
import java.util.List;

import com.adrmanagement.common.adr.domain.enums.AdrRecordProgressStatus;
import com.adrmanagement.common.adr.domain.enums.AdrRecordStatus;

public class AdrRecordEventStoreItem {
	
	private Long adrId;
	
	private String eventName;
	
	private AdrRecordProgressStatus progressStatus;
	
	private List<Long> decisionMemberIdList;
	
	private AdrRecordStatus status;
	
	private Long teamId;
	
	/**上下文**/
	private String context;
	
	/**標題**/
	private String title;
	
	/**決策**/
	private String decision;
	
	/**編輯者**/
	private Long editorMemberId;
	
	/**理由**/
	private String rationale;
	
	/**結果**/
	private String consequences;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;
	
	/**已被此決策取代**/
	private Long replacedAdrId;
	
	/**已被此決策取代理由**/
	private String replacedRationale;

	public Long getAdrId() {
		return adrId;
	}

	public void setAdrId(Long adrId) {
		this.adrId = adrId;
	}

	public AdrRecordProgressStatus getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(AdrRecordProgressStatus progressStatus) {
		this.progressStatus = progressStatus;
	}

	public AdrRecordStatus getStatus() {
		return status;
	}

	public void setStatus(AdrRecordStatus status) {
		this.status = status;
	}

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

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public Long getReplacedAdrId() {
		return replacedAdrId;
	}

	public void setReplacedAdrId(Long replacedAdrId) {
		this.replacedAdrId = replacedAdrId;
	}

	public String getReplacedRationale() {
		return replacedRationale;
	}

	public void setReplacedRationale(String replacedRationale) {
		this.replacedRationale = replacedRationale;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public List<Long> getDecisionMemberIdList() {
		return decisionMemberIdList;
	}

	public void setDecisionMemberIdList(List<Long> decisionMemberIdList) {
		this.decisionMemberIdList = decisionMemberIdList;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

}
