package com.adrmanagement.adr.domain.model.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "adr_decision_member")
public class AdrDecisionMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adrDecisionMemberId;
	
	private Long memberId;
	
	@ManyToOne()
    @JoinColumn(name = "adrId")
    private AdrRecord adrRecord;
	


	public AdrRecord getAdrRecord() {
		return adrRecord;
	}

	public void setAdrRecord(AdrRecord adrRecord) {
		this.adrRecord = adrRecord;
	}
	
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getAdrDecisionMemberId() {
		return adrDecisionMemberId;
	}

	public void setAdrDecisionMemberId(Long adrDecisionMemberId) {
		this.adrDecisionMemberId = adrDecisionMemberId;
	}






}
