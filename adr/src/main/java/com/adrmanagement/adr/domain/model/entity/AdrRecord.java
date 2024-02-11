package com.adrmanagement.adr.domain.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.adrmanagement.common.adr.domain.enums.AdrRecordProgressStatus;
import com.adrmanagement.common.adr.domain.enums.AdrRecordStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "ADR_record")
public class AdrRecord {
	@Id
	private Long adrId;
	
	private Long teamId;
	
	@Enumerated(EnumType.STRING)
	private AdrRecordProgressStatus progressStatus;
	
	@Enumerated(EnumType.STRING)
	private AdrRecordStatus status;
	
	/**上下文**/
	@Column(length = 5000)
	private String context;
	
	/**標題**/
	@Column(length = 500)
	private String title;
	
	/**決策參與者**/
	@OneToMany(mappedBy="adrRecord", cascade = CascadeType.ALL ,orphanRemoval = true)
	private List<AdrDecisionMember> adrDecisionMemberList = new ArrayList<AdrDecisionMember>();
	
	/**決策**/
	@Column(length = 5000)
	private String decision;
	
	/**編輯者**/
	private Long editorMemberId;
	
	/**理由**/
	@Column(length = 5000)
	private String rationale;
	
	/**結果**/
	@Column(length = 5000)
	private String consequences;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;
	
	/**已被此決策取代**/
	private Long replacedAdrId;
	
	/**已被此決策取代理由**/
	@Column(length = 5000)
	private String replacedRationale;
	
	private Boolean deleted = false;


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

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

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

	public List<AdrDecisionMember> getAdrDecisionMemberList() {
		return adrDecisionMemberList;
	}

	public void setAdrDecisionMemberList(List<AdrDecisionMember> adrDecisionMemberList) {
		this.adrDecisionMemberList = adrDecisionMemberList;
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
