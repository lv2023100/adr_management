package com.adrmanagement.web.infrastructure.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adrmanagement.common.util.ProtoBeanUtils;
import com.adrmanagement.member.infrastructure.grpcservice.Member.MemberGrpcDto;
import com.adrmanagement.team.infrastructure.grpcservice.Team.TeamGrpcDto;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetResponse;
import com.adrmanagement.web.domain.service.commandservice.TeamCommandService;
import com.adrmanagement.web.domain.service.queryservice.MemberQueryService;
import com.adrmanagement.web.domain.service.queryservice.TeamQueryService;
import com.adrmanagement.web.infrastructure.controller.component.TeamForm;
import com.adrmanagement.web.infrastructure.controller.component.TeamListItem;
import com.adrmanagement.web.infrastructure.security.MemberDetails;

@Controller
@RequestMapping("/admin")
public class TeamController {
	
	Logger log = LoggerFactory.getLogger(TeamController.class);
	
	@Autowired
	private TeamQueryService teamQueryService;
	
	@Autowired
	private MemberQueryService memberQueryService;
	
	@Autowired
	private TeamCommandService teamCommandService;
	
	@GetMapping("/teamUpdate/{teamId}")
    public String updateView(@PathVariable("teamId") Long teamId, Model model)throws Exception {
		TeamGetResponse teamGetResponse = teamQueryService.getTeam(teamId);
		TeamForm teamForm = ProtoBeanUtils.toPojoBean(TeamForm.class, teamGetResponse);
		List<MemberGrpcDto> memberGrpcDtoList = memberQueryService.getMemberList(null);
		model.addAttribute("memberList", memberGrpcDtoList);
		model.addAttribute("teamForm",teamForm);
		return "team/team_edit";
    }
	
	@PostMapping("/teamUpdate/{teamId}")
    public String update(TeamForm teamForm, @PathVariable("teamId") Long teamId, Model model
    		,@AuthenticationPrincipal MemberDetails memberDetails)throws Exception {
		if(!teamQueryService.isExistTeamByMemberId(teamForm.getTeamId(), memberDetails.getMember().getMemberId())) {
			return "redirect:/403Error";
		}
		teamCommandService.update(teamForm);
		return "redirect:/admin/team";
	}
	
	@GetMapping("/teamCreate")
    public String createView(Model model)throws Exception {
		List<MemberGrpcDto> memberGrpcDtoList = memberQueryService.getMemberList(null);
		model.addAttribute("memberList", memberGrpcDtoList);
		model.addAttribute("teamForm",new TeamForm());
		return "team/team_edit";
    }
	
	@PostMapping("/teamCreate")
    public String create(TeamForm teamForm, Model model)throws Exception {
		teamCommandService.create(teamForm);
		return "redirect:/admin/team";
	}
	
	@GetMapping("/team")
    public String list (Model model)throws Exception {
		List<TeamGrpcDto> teamGrpcDtoList =  teamQueryService.getTeamList();
		List<TeamListItem> teamListItemList =  new ArrayList<TeamListItem>();
		for(TeamGrpcDto teamGrpcDto : teamGrpcDtoList) {
			TeamListItem teamListItem = ProtoBeanUtils.toPojoBean(TeamListItem.class, teamGrpcDto);
			teamListItemList.add(teamListItem);
		}
		model.addAttribute("teamListItemList", teamListItemList);
		return "team/team_list";
	}

}
