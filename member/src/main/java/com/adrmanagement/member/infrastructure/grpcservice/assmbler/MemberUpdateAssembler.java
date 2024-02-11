package com.adrmanagement.member.infrastructure.grpcservice.assmbler;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.adrmanagement.common.member.domain.commands.MemberCreateCommand;
import com.adrmanagement.common.member.domain.commands.MemberUpdateCommand;
import com.adrmanagement.common.util.SequenceGenerator;
import com.adrmanagement.member.infrastructure.grpcservice.MemberUpdateRequest;

public class MemberUpdateAssembler {
	
	public static MemberUpdateCommand rquestToCommand(MemberUpdateRequest memberUpdateRequest) {
		MemberUpdateCommand memberUpdateCommand = new MemberUpdateCommand();
		
		BeanUtils.copyProperties(memberUpdateRequest, memberUpdateCommand);
		LocalDateTime now = LocalDateTime.now();
		memberUpdateCommand.setUpdateTime(now);
		return memberUpdateCommand;
	}

}
