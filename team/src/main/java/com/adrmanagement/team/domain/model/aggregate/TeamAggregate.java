package com.adrmanagement.team.domain.model.aggregate;

import java.time.LocalDateTime;
import java.util.List;

public class TeamAggregate {
	
	private Long teamId;
	
	private String name;
	
	private LocalDateTime createTime;
	
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

	public List<Long> getMemberIdList() {
		return memberIdList;
	}

	public void setMemberIdList(List<Long> memberIdList) {
		this.memberIdList = memberIdList;
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

}
