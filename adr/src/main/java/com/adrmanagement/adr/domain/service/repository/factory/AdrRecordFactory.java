package com.adrmanagement.adr.domain.service.repository.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.adrmanagement.adr.domain.model.aggregate.AdrRecordAggregate;
import com.adrmanagement.adr.domain.model.entity.AdrDecisionMember;
import com.adrmanagement.adr.domain.model.entity.AdrRecord;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordCreateEvent;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordUpdateEvent;

public class AdrRecordFactory {

	public static AdrRecord adrRecordCreateEventToAdrRecord(AdrRecordCreateEvent adrRecordCreateEvent) {
		AdrRecord adrRecord = new AdrRecord();
		BeanUtils.copyProperties(adrRecordCreateEvent, adrRecord);
		List<AdrDecisionMember> adrDecisionMemberList = new ArrayList<AdrDecisionMember>();
		if (!CollectionUtils.isEmpty(adrRecordCreateEvent.getDecisionMemberIdList())) {
			adrDecisionMemberList = adrRecordCreateEvent.getDecisionMemberIdList().stream().map(decisionMemberId -> {
				AdrDecisionMember adrDecisionMember = new AdrDecisionMember();
				adrDecisionMember.setAdrRecord(adrRecord);
				adrDecisionMember.setMemberId(decisionMemberId);
				return adrDecisionMember;
			}).collect(Collectors.toList());
		}
		adrRecord.setAdrDecisionMemberList(adrDecisionMemberList);
		return adrRecord;
	}

	public static AdrRecord adrRecordUpdateEventToAdrRecord(AdrRecordUpdateEvent adrRecordUpdateEvent,
			AdrRecord adrRecord) {
		adrRecord.setContext(adrRecordUpdateEvent.getContext());
		adrRecord.setTitle(adrRecordUpdateEvent.getTitle());
		adrRecord.setDecision(adrRecordUpdateEvent.getDecision());
		adrRecord.setEditorMemberId(adrRecordUpdateEvent.getEditorMemberId());
		adrRecord.setRationale(adrRecordUpdateEvent.getRationale());
		adrRecord.setConsequences(adrRecordUpdateEvent.getConsequences());
		adrRecord.setUpdateTime(adrRecordUpdateEvent.getUpdateTime());
		adrRecord.setStatus(adrRecordUpdateEvent.getStatus());
		adrRecord.setProgressStatus(adrRecordUpdateEvent.getProgressStatus());
		List<AdrDecisionMember> adrDecisionMemberList = new ArrayList<AdrDecisionMember>();
		if (!CollectionUtils.isEmpty(adrRecordUpdateEvent.getDecisionMemberIdList())) {

			adrDecisionMemberList = adrRecordUpdateEvent.getDecisionMemberIdList().stream().map(decisionMemberId -> {
				AdrDecisionMember adrDecisionMember = new AdrDecisionMember();
				adrDecisionMember.setAdrRecord(adrRecord);
				adrDecisionMember.setMemberId(decisionMemberId);
				return adrDecisionMember;
			}).collect(Collectors.toList());
			if (!CollectionUtils.isEmpty(adrRecord.getAdrDecisionMemberList())) {
				adrRecord.getAdrDecisionMemberList().clear();
				
			}
			adrRecord.getAdrDecisionMemberList().addAll(adrDecisionMemberList);

		}
		return adrRecord;
	}

	public static AdrRecordAggregate adrRecordToAdrRecordAggregate(AdrRecord adrRecord) {
		AdrRecordAggregate adrRecordAggregate = new AdrRecordAggregate();
		BeanUtils.copyProperties(adrRecord, adrRecordAggregate);
		List<Long> decisionMemberList = new ArrayList<Long>();
		if (!CollectionUtils.isEmpty(adrRecord.getAdrDecisionMemberList())) {
			decisionMemberList = adrRecord.getAdrDecisionMemberList().stream().map(AdrDecisionMember::getMemberId)
					.collect(Collectors.toList());
		}
		adrRecordAggregate.setDecisionMemberIdList(decisionMemberList);
		return adrRecordAggregate;
	}

}
