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
import org.springframework.beans.factory.annotation.Autowired;

import com.adrmanagement.adr.domain.model.entity.AdrRecord;
import com.adrmanagement.adr.domain.service.repository.AdrRecordRepository;
import com.adrmanagement.common.adr.domain.commands.AdrRecordItemPublishCommand;
import com.adrmanagement.common.adr.domain.commands.AdrRecordItemRepublishCommand;
import com.adrmanagement.common.adr.domain.enums.AdrRecordProgressStatus;
import com.adrmanagement.common.adr.domain.commands.AdrRecordItemPublishByMemberIdCommand;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordItemPublishEvent;
import com.adrmanagement.common.util.SequenceGenerator;

@Aggregate
public class AdrRecordItemAggregate {
	
	Logger log = LoggerFactory.getLogger(AdrRecordItemAggregate.class);

	public AdrRecordItemAggregate() {
	}

	@CommandHandler
	public AdrRecordItemAggregate(AdrRecordItemPublishCommand adrRecordItemPublishCommand) {
		try {
			Long adrItemId = SequenceGenerator.genId();
			log.info("AdrRecordItemPublishCommand AdrItemId:{} AdrId:{}"
					,adrItemId, adrRecordItemPublishCommand.getAdrId());
			AdrRecordItemPublishEvent adrRecordItemPublishEvent = new AdrRecordItemPublishEvent();
			BeanUtils.copyProperties(adrRecordItemPublishCommand, adrRecordItemPublishEvent);
			adrRecordItemPublishEvent.setAdrItemId(adrItemId);
			AggregateLifecycle.apply(adrRecordItemPublishEvent);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}

	}

	@EventSourcingHandler
	protected void on(AdrRecordItemPublishEvent adrRecordItemPublishEvent) {
		this.adrItemId = adrRecordItemPublishEvent.getAdrItemId();
		this.adrId = adrRecordItemPublishEvent.getAdrId();
		this.title = adrRecordItemPublishEvent.getTitle();
		this.decisionMemberIdList = adrRecordItemPublishEvent.getDecisionMemberIdList();
		this.decision = adrRecordItemPublishEvent.getDecision();
		this.editorMemberId = adrRecordItemPublishEvent.getEditorMemberId();
		this.editorMemberName = adrRecordItemPublishEvent.getEditorMemberName();
		this.rationale = adrRecordItemPublishEvent.getRationale();
		this.consequences = adrRecordItemPublishEvent.getConsequences();
		this.createTime = adrRecordItemPublishEvent.getCreateTime();
		this.teamId = adrRecordItemPublishEvent.getTeamId();

	}
	
	@CommandHandler
    public void handle(AdrRecordItemRepublishCommand adrRecordItemRepublishCommand){
		try {
			log.info("AdrRecordItemRepublishCommand AdrItemId:{} AdrId:{}"
					,adrRecordItemRepublishCommand.getAdrItemId(), adrRecordItemRepublishCommand.getAdrId());
			AdrRecordItemPublishEvent adrRecordItemPublishEvent = new AdrRecordItemPublishEvent();
			BeanUtils.copyProperties(adrRecordItemRepublishCommand, adrRecordItemPublishEvent);
			AggregateLifecycle.apply(adrRecordItemPublishEvent);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}

	@AggregateIdentifier
	private Long adrItemId;

	private Long adrId;
	
	private AdrRecordProgressStatus progressStatus;
	
	private Long teamId;

	/** 上下文 **/
	private String context;

	/** 標題 **/
	private String title;

	/** 決策參與者 **/
	private List<Integer> decisionMemberIdList;

	/** 決策 **/
	private String decision;

	/** 編輯者 **/
	private Long editorMemberId;

	/** 編輯者名字 **/
	private String editorMemberName;

	/** 理由 **/
	private String rationale;

	/** 結果 **/
	private String consequences;

	private LocalDateTime createTime;

	private LocalDateTime updateTime;

	/** 已被此決策取代 **/
	private Long replacedAdrId;

	/** 已被此決策取代理由 **/
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

	public String getEditorMemberName() {
		return editorMemberName;
	}

	public void setEditorMemberName(String editorMemberName) {
		this.editorMemberName = editorMemberName;
	}

	public Long getAdrId() {
		return adrId;
	}

	public void setAdrId(Long adrId) {
		this.adrId = adrId;
	}

	public Long getAdrItemId() {
		return adrItemId;
	}

	public void setAdrItemId(Long adrItemId) {
		this.adrItemId = adrItemId;
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
