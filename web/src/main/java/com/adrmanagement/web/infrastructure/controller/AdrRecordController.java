package com.adrmanagement.web.infrastructure.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordEventStoreResponse;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordGetResponse;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemGetListResponse;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemGetResponse;
import com.adrmanagement.common.adr.domain.enums.AdrRecordProgressStatus;
import com.adrmanagement.common.util.ProtoBeanUtils;
import com.adrmanagement.member.infrastructure.grpcservice.Member.MemberGrpcDto;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetResponse;
import com.adrmanagement.web.domain.service.commandservice.AdrRecordCommandService;
import com.adrmanagement.web.domain.service.queryservice.AdrRecordQueryService;
import com.adrmanagement.web.domain.service.queryservice.MemberQueryService;
import com.adrmanagement.web.domain.service.queryservice.TeamQueryService;
import com.adrmanagement.web.infrastructure.controller.component.AdrRecordEventStoreItem;
import com.adrmanagement.web.infrastructure.controller.component.AdrRecordForm;
import com.adrmanagement.web.infrastructure.controller.component.AdrRecordItemListItem;

@Controller
@RequestMapping("/admin")
public class AdrRecordController {

	Logger log = LoggerFactory.getLogger(AdrRecordController.class);

	@Autowired
	private AdrRecordQueryService adrRecordQueryService;

	@Autowired
	private TeamQueryService teamQueryService;

	@Autowired
	private MemberQueryService memberQueryService;

	@Autowired
	private AdrRecordCommandService adrRecordCommandService;

	@GetMapping("/adrUpdate/{adrId}")
	public String updateView(@PathVariable("adrId") Long adrId, Model model)throws Exception {
		AdrRecordGetResponse adrRecordGetResponse = adrRecordQueryService.getAdrRecord(adrId);
		TeamGetResponse teamGetResponse = teamQueryService.getTeam(adrRecordGetResponse.getTeamId());
		List<MemberGrpcDto> memberGrpcDtoList = memberQueryService
				.getMemberList(teamGetResponse.getMemberIdListList());
		AdrRecordForm adrRecordForm = ProtoBeanUtils.toPojoBean(AdrRecordForm.class, adrRecordGetResponse);
		model.addAttribute("memberList", memberGrpcDtoList);
		model.addAttribute("adrRecord", adrRecordForm);
		model.addAttribute("adrRecordProgressStatusList", AdrRecordProgressStatus.values());
		return "adr/adr_edit";
	}

	@PostMapping("/adrUpdate/{adrId}")
	public String update(AdrRecordForm adrRecordForm, @PathVariable("adrId") Long adrId, Model model)throws Exception {
		adrRecordCommandService.update(adrRecordForm);
		return "redirect:/adr_record_publishing.html?teamId=" + adrRecordForm.getTeamId();
	}

	@GetMapping("/adrCreate")
	public String createView(@RequestParam Long teamId, AdrRecordForm adrRecordForm, Model model)throws Exception {
		TeamGetResponse teamGetResponse = teamQueryService.getTeam(teamId);
		List<MemberGrpcDto> memberGrpcDtoList = memberQueryService
				.getMemberList(teamGetResponse.getMemberIdListList());
		model.addAttribute("memberList", memberGrpcDtoList);
		adrRecordForm.setDecisionMemberIdList(new ArrayList<Long>());
		adrRecordForm.setTeamId(teamId);
		model.addAttribute("adrRecord", adrRecordForm);
		model.addAttribute("adrRecordProgressStatusList", AdrRecordProgressStatus.values());
		return "adr/adr_edit";
	}

	@PostMapping("/adrCreate")
	public String create(AdrRecordForm adrRecordForm, Model model) throws Exception{
		adrRecordCommandService.create(adrRecordForm);
		return "redirect:/adr_record_publishing.html?teamId=" + adrRecordForm.getTeamId();
	}

	@GetMapping("/adr")
	public String adr(@RequestParam Long teamId, Model model) throws Exception{
		AdrRecordItemGetListResponse adrRecordItemGetListResponse = adrRecordQueryService
				.getAdrRecordItemList(teamId);
		List<AdrRecordItemGetResponse> adrRecordItemGetResponseList = adrRecordItemGetListResponse
				.getAdrRecordItemGetResponseList();
		List<AdrRecordItemListItem> adrRecordItemListItemList = new ArrayList<AdrRecordItemListItem>();
		if (!CollectionUtils.isEmpty(adrRecordItemGetResponseList)) {
			adrRecordItemListItemList = adrRecordItemGetResponseList.stream()
					.map(adrRecordItemGetResponse -> {
						AdrRecordItemListItem adrRecordItemListItem = new AdrRecordItemListItem();
						try {
							adrRecordItemListItem = ProtoBeanUtils.toPojoBean(AdrRecordItemListItem.class,
									adrRecordItemGetResponse);
						} catch (IOException e) {
							log.error(e.getMessage(), e);
						}
						return adrRecordItemListItem;
					})
					.collect(Collectors.toList());
		}
		model.addAttribute("adrRecordItemListItemList", adrRecordItemListItemList);
		return "adr/adr_list";

	}

	@GetMapping("/adrContent/{adrId}")
	public String content(@PathVariable("adrId") Long adrId, Model model) throws Exception{
		AdrRecordGetResponse adrRecordGetResponse = adrRecordQueryService.getAdrRecord(adrId);
		AdrRecordForm adrRecordForm = ProtoBeanUtils.toPojoBean(AdrRecordForm.class, adrRecordGetResponse);
		if(!CollectionUtils.isEmpty(adrRecordGetResponse.getDecisionMemberIdListList())) {
			List<MemberGrpcDto> memberGrpcDtoList = memberQueryService
					.getMemberList(adrRecordGetResponse.getDecisionMemberIdListList());
			List<String> decisionMemberNameList = memberGrpcDtoList.stream().map(MemberGrpcDto::getName)
					.collect(Collectors.toList());
			model.addAttribute("decisionMemberNameList", decisionMemberNameList);
		}
		List<AdrRecordEventStoreItem> adrRecordEventStoreItemList = new ArrayList<AdrRecordEventStoreItem>();
		for(AdrRecordEventStoreResponse adrRecordEventStoreResponse: adrRecordGetResponse.getAdrRecordEventStoreListList()) {
			AdrRecordEventStoreItem adrRecordEventStoreItem = ProtoBeanUtils.toPojoBean(AdrRecordEventStoreItem.class, adrRecordEventStoreResponse);
			adrRecordEventStoreItemList.add(adrRecordEventStoreItem);
		}
		model.addAttribute("adrRecordEventStoreItemList", adrRecordEventStoreItemList);
		model.addAttribute("adrRecordForm", adrRecordForm);
		
		return "adr/adr_content";

	}
	
	@GetMapping("/adrContent/historical")
	public String historical(AdrRecordEventStoreItem adrRecordEventStoreItem, Model model) throws Exception{
		if(!CollectionUtils.isEmpty(adrRecordEventStoreItem.getDecisionMemberIdList())) {
			List<MemberGrpcDto> memberGrpcDtoList = memberQueryService
					.getMemberList(adrRecordEventStoreItem.getDecisionMemberIdList());
			List<String> decisionMemberNameList = memberGrpcDtoList.stream().map(MemberGrpcDto::getName)
					.collect(Collectors.toList());
			model.addAttribute("decisionMemberNameList", decisionMemberNameList);
		}
		model.addAttribute("adrRecordForm", adrRecordEventStoreItem);
		model.addAttribute("isHistorical", true);
		
		return "adr/adr_content";

	}

}
