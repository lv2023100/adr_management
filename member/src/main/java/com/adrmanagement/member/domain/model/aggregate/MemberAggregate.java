package com.adrmanagement.member.domain.model.aggregate;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.adrmanagement.common.adr.domain.commands.AdrRecordItemPublishByMemberIdCommand;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordCreateEvent;
import com.adrmanagement.common.adr.domain.service.events.AdrRecordUpdateByMemberIdEvent;
import com.adrmanagement.common.member.domain.enums.MemberPermissions;

public class MemberAggregate {
	
	private Long memberId;
	
	private String name;
	
	private MemberPermissions permission;
	
	private String phoneNumber="";
	
	private String email="";
	
	private String account="";
	
	private String password="";
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public MemberPermissions getPermission() {
		return permission;
	}

	public void setPermission(MemberPermissions permission) {
		this.permission = permission;
	}

}
