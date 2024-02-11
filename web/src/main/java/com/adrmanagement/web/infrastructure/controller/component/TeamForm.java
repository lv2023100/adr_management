package com.adrmanagement.web.infrastructure.controller.component;

import java.util.ArrayList;
import java.util.List;

public class TeamForm {
	
	private Long teamId;
	
	private String name;
	
	private List<Long> memberIdList = new ArrayList<Long>();


	public List<Long> getMemberIdList() {
		return memberIdList;
	}

	public void setMemberIdList(List<Long> memberIdList) {
		this.memberIdList = memberIdList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

}
