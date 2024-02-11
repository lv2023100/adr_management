package com.adrmanagement.team.domain.model.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "team_member")
public class TeamMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teamMemberId;
	
	@ManyToOne()
    @JoinColumn(name = "teamId")
    private Team team;
	
	private Long memberId;

	public Long getTeamMemberId() {
		return teamMemberId;
	}

	public void setTeamMemberId(Long teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

}
