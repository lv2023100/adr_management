package com.adrmanagement.web.infrastructure.controller.component;

import java.util.ArrayList;
import java.util.List;

import com.adrmanagement.common.adr.domain.enums.AdrRecordProgressStatus;

public class AdrRecordForm {
	
	private Long adrId;
	
	private Long teamId;
	
	private AdrRecordProgressStatus progressStatus; 
	
	/**上下文**/
	private String context;
	
	/**標題**/
	private String title;
	
	/**決策參與者**/
	private List<Long> decisionMemberIdList = new ArrayList<Long>();
	
	/**決策**/
	private String decision;
	
	/**理由**/
	private String rationale;
	
	/**結果**/
	private String consequences;
	
	private String createTime;
	
	private String updateTime;
	
	

	public Long getAdrId() {
		return adrId;
	}

	public void setAdrId(Long adrId) {
		this.adrId = adrId;
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

	public List<Long> getDecisionMemberIdList() {
		return decisionMemberIdList;
	}

	public void setDecisionMemberIdList(List<Long> decisionMemberIdList) {
		this.decisionMemberIdList = decisionMemberIdList;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
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

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public AdrRecordProgressStatus getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(AdrRecordProgressStatus progressStatus) {
		this.progressStatus = progressStatus;
	}

}
