package com.adrmanagement.web.infrastructure.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adrmanagement.common.enums.GenericResponseEnum;
import com.adrmanagement.common.infrastructure.grpcservice.GenericResponseOuterClass.GenericResponse;
import com.adrmanagement.common.util.ProtoBeanUtils;
import com.adrmanagement.member.infrastructure.grpcservice.Member.MemberGrpcDto;
import com.adrmanagement.web.domain.service.commandservice.MemberCommandService;
import com.adrmanagement.web.domain.service.queryservice.MemberQueryService;
import com.adrmanagement.web.infrastructure.controller.component.MemberForm;
import com.adrmanagement.web.infrastructure.controller.component.MemberListItem;
import com.adrmanagement.web.infrastructure.security.MemberDetails;

@Controller
@RequestMapping("/admin")
public class MemberController {
	
	Logger log = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberQueryService memberQueryService;
	
	@Autowired
	private MemberCommandService memberCommandService;
	
	@GetMapping("/memberUpdate/{memberId}")
    public String updateView(@PathVariable("memberId") Long memberId, Model model, Authentication authentication)throws Exception {
		
		MemberGrpcDto memberGrpcDto = memberQueryService.getMember(memberId);
		MemberForm memberForm = ProtoBeanUtils.toPojoBean(MemberForm.class, memberGrpcDto);
		model.addAttribute("memberForm", memberForm);
		return "member/member_edit";
		
	}
	
	@PostMapping("/memberUpdate/{memberId}")
    public String update(MemberForm memberForm, @PathVariable("memberId") Long memberId, Model model
    		,@AuthenticationPrincipal MemberDetails memberDetails) throws Exception{
		if(!memberDetails.getMember().getMemberId().equals(memberForm.getMemberId())) {
			return "redirect:/403Error";
		}
		if(!memberForm.getPassword().equals(memberForm.getPassword2())) {
			model.addAttribute("failMessage", "Passwords Do Not Match");
			return this.createView(memberForm, model);
		}
		memberCommandService.update(memberForm);
		return "redirect:/admin/member";
	}
	
	@GetMapping("/memberCreate")
    public String createView(MemberForm memberForm, Model model)throws Exception {
		
		model.addAttribute("memberForm", memberForm);
		return "member/member_edit";
		
	}
	
	@PostMapping("/memberCreate")
    public String create(MemberForm memberForm, Model model)throws Exception {
		if(!memberForm.getPassword().equals(memberForm.getPassword2())) {
			model.addAttribute("failMessage", "Passwords Do Not Match");
			return this.createView(memberForm, model);
		}
		GenericResponse result= memberCommandService.create(memberForm);
		if(result.getStatus() != GenericResponseEnum.SUCCESS.getStatus()) {
			model.addAttribute("failMessage", result.getMessage());
			
		}
		return "redirect:/admin/member";
	}
	
	@GetMapping("/member")
    public String list (Model model)throws Exception {
		List<MemberGrpcDto> memberGrpcDtoList =  memberQueryService.getMemberList();
		List<MemberListItem> memberListItemList =  new ArrayList<MemberListItem>();
		for(MemberGrpcDto memberGrpcDto : memberGrpcDtoList) {
			MemberListItem memberListItem = ProtoBeanUtils.toPojoBean(MemberListItem.class, memberGrpcDto);
			memberListItemList.add(memberListItem);
		}
		model.addAttribute("memberListItemList", memberListItemList);
		return "member/member_list";
	}
	

}
