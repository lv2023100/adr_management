package com.adrmanagement.member.application.service.commandservice;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrmanagement.common.member.domain.commands.MemberCreateCommand;
import com.adrmanagement.member.domain.model.entity.Member;
import com.adrmanagement.member.domain.service.commandservice.MemberCreateCommandService;
import com.adrmanagement.member.domain.service.repository.MemberRepository;

@Service
public class MemberCreateCommandServiceImpl implements MemberCreateCommandService{
	
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public void create(MemberCreateCommand memberCreateCommand) throws Exception {
		Member member = new Member();
		BeanUtils.copyProperties(memberCreateCommand, member);
		memberRepository.save(member);
	}

}
