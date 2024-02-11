package com.adrmanagement.member.application.service.commandservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemPublishByMemberIdRequest;
import com.adrmanagement.common.member.domain.commands.MemberUpdateCommand;
import com.adrmanagement.member.domain.model.entity.Member;
import com.adrmanagement.member.domain.service.commandservice.MemberUpdateCommandService;
import com.adrmanagement.member.domain.service.grpcclient.AdrRecordItemPublishClient;
import com.adrmanagement.member.domain.service.repository.MemberRepository;
import com.adrmanagement.member.domain.service.repository.factory.MemberFactory;

@Service
public class MemberUpdateCommandServiceImpl implements MemberUpdateCommandService{
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private AdrRecordItemPublishClient adrRecordItemPublishClient;


	@Override
	public void update(MemberUpdateCommand memberUpdateCommand) throws Exception {
		Member member = memberRepository.findById(memberUpdateCommand.getMemberId());
		member = MemberFactory.memberUpdateCommandToMember(memberUpdateCommand, member);
		memberRepository.save(member);
		adrRecordItemPublishClient.publishByMemberId(member.getMemberId(), member.getName());
	}
	
	



}
