package com.adrmanagement.member.domain.model.entity;

import java.time.LocalDateTime;

import com.adrmanagement.common.adr.domain.enums.AdrRecordProgressStatus;
import com.adrmanagement.common.member.domain.enums.MemberPermissions;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "member")
public class Member {
	
	@Id
	private Long memberId;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private MemberPermissions permission;
	
	private String phoneNumber;
	
	private String email;
	
	private String account;
	
	private String password;
	
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
