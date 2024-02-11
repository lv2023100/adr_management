package com.adrmanagement.team.domain.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "team")
public class Team {
	
	@Id
	private Long teamId;
	
	private String name;
	
	private LocalDateTime createTime;
	
	private LocalDateTime updateTime;
	
	@OneToMany(mappedBy="team", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<TeamMember> teamMemberList;

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

	public List<TeamMember> getTeamMemberList() {
		return teamMemberList;
	}

	public void setTeamMemberList(List<TeamMember> teamMemberList) {
		this.teamMemberList = teamMemberList;
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
