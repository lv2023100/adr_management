package com.adrmanagement.web.infrastructure.security;

import java.util.List;

import com.adrmanagement.common.member.domain.enums.MemberPermissions;

public class MemberInfo {
	
	private String name;
	
	private String account;
	
	private String password;
	
	private Long memberId;
	
	private List<TeamInfo> teamInfoList;
	
	private MemberPermissions permission;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static class TeamInfo {
		private Long teamId;
		private String name;
		public Long getTeamId() {
			return teamId;
		}
		public void setTeamId(Long teamId) {
			this.teamId = teamId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}

	public List<TeamInfo> getTeamInfoList() {
		return teamInfoList;
	}

	public void setTeamInfoList(List<TeamInfo> teamInfoList) {
		this.teamInfoList = teamInfoList;
	}

	public MemberPermissions getPermission() {
		return permission;
	}

	public void setPermission(MemberPermissions permission) {
		this.permission = permission;
	}


}
