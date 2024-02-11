package com.adrmanagement.adr.domain.model.aggregate;

import java.time.LocalDateTime;
import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.adrmanagement.common.adr.domain.commands.AdrRecordCreateCommand;
import com.adrmanagement.common.adr.domain.commands.AdrRecordPublishCommand;
import com.adrmanagement.common.adr.domain.commands.AdrRecordUpdateCommand;
import com.adrmanagement.common.adr.domain.enums.AdrRecordProgressStatus;
import com.adrmanagement.common.adr.domain.enums.AdrRecordStatus;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordCreateEvent;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordPublishEvent;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordUpdateEvent;
@Aggregate
public class AdrRecordAggregate {
	
	public AdrRecordAggregate(){}
	
	transient Logger log = LoggerFactory.getLogger(AdrRecordAggregate.class);
	
	@CommandHandler
    public AdrRecordAggregate(AdrRecordCreateCommand adrRecordCreateCommand){
		try {
			log.info("AdrRecordCreateCommand AdrId:{}", adrRecordCreateCommand.getAdrId());
			AdrRecordCreateEvent adrRecordCreateEvent = new AdrRecordCreateEvent();
			BeanUtils.copyProperties(adrRecordCreateCommand, adrRecordCreateEvent);
			adrRecordCreateEvent.setStatus(AdrRecordStatus.CREATED);
	        AggregateLifecycle.apply(adrRecordCreateEvent);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		
    }
	
	@EventSourcingHandler
    protected void on(AdrRecordCreateEvent adrRecordCreateEvent){
		this.adrId =  adrRecordCreateEvent.getAdrId();
		this.teamId = adrRecordCreateEvent.getTeamId();
        this.title = adrRecordCreateEvent.getTitle();
        this.decisionMemberIdList = adrRecordCreateEvent.getDecisionMemberIdList();
        this.decision = adrRecordCreateEvent.getDecision();
        this.editorMemberId = adrRecordCreateEvent.getEditorMemberId();
        this.rationale = adrRecordCreateEvent.getRationale();
        this.consequences = adrRecordCreateEvent.getConsequences();
        this.createTime = adrRecordCreateEvent.getCreateTime();
        this.progressStatus = adrRecordCreateEvent.getProgressStatus();
        
    }
	
	@CommandHandler
    public void handle(AdrRecordPublishCommand adrRecordPublishCommand){
		try {
			log.info("AdrRecordPublishCommand AdrId:{}", adrRecordPublishCommand.getAdrId());
			AdrRecordPublishEvent adrRecordPublishEvent = new AdrRecordPublishEvent(adrRecordPublishCommand.getAdrId(), AdrRecordStatus.PUBLISHED);
	        AggregateLifecycle.apply(adrRecordPublishEvent);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
    }
	
	@EventSourcingHandler
    protected void on(AdrRecordPublishEvent adrRecordPublishEvent){
		this.adrId =  adrRecordPublishEvent.getAdrId();
        this.status = adrRecordPublishEvent.getStatus();
    }
	
	@CommandHandler
    public void handle(AdrRecordUpdateCommand adrRecordUpdateCommand){
		try {
			log.info("AdrRecordUpdateCommand AdrId:{}", adrRecordUpdateCommand.getAdrId());
			AdrRecordUpdateEvent adrRecordUpdateEvent = new AdrRecordUpdateEvent();
			BeanUtils.copyProperties(adrRecordUpdateCommand, adrRecordUpdateEvent);
			adrRecordUpdateEvent.setStatus(AdrRecordStatus.UNPUBLISHED);
	        AggregateLifecycle.apply(adrRecordUpdateEvent);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
    }
	
	@EventSourcingHandler
    protected void on(AdrRecordUpdateEvent adrRecordUpdateEvent){
		BeanUtils.copyProperties(adrRecordUpdateEvent, this);
    }
	
	@AggregateIdentifier
	private Long adrId;
	
	private Long teamId;
	
	private AdrRecordStatus status;
	
	private AdrRecordProgressStatus progressStatus;
	
	/**上下文**/
	private String context;
	
	/**標題**/
	private String title;
	
	/**決策參與者**/
	private List<Long> decisionMemberIdList;
	
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
