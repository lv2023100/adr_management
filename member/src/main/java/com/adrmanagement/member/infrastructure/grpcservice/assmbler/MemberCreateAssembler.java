package com.adrmanagement.member.infrastructure.grpcservice.assmbler;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.adrmanagement.common.member.domain.commands.MemberCreateCommand;
import com.adrmanagement.common.util.SequenceGenerator;
import com.adrmanagement.member.infrastructure.grpcservice.MemberCreateRequest;

public class MemberCreateAssembler {
	
	public static MemberCreateCommand rquestToCommand(MemberCreateRequest memberCreateRequest) {
		MemberCreateCommand memberCreateCommand = new MemberCreateCommand();
		BeanUtils.copyProperties(memberCreateRequest, memberCreateCommand);
		LocalDateTime now = LocalDateTime.now();
		memberCreateCommand.setMemberId(SequenceGenerator.genId());
		memberCreateCommand.setCreateTime(now);
		memberCreateCommand.setUpdateTime(now);
		return memberCreateCommand;
	}

}
