package com.adrmanagement.team.domain.service.command;

import java.time.LocalDateTime;
import java.util.List;

public class TeamUpdateCommand {
	
	private Long teamId;
	private String name;
	private LocalDateTime updateTime;
	private List<Long> memberIdList;
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
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
	public List<Long> getMemberIdList() {
		return memberIdList;
	}
	public void setMemberIdList(List<Long> memberIdList) {
		this.memberIdList = memberIdList;
	}

}
