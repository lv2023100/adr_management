package com.adrmanagement.member.domain.service.commandservice;

import org.springframework.stereotype.Service;

import com.adrmanagement.common.member.domain.commands.MemberUpdateCommand;

@Service
public interface MemberUpdateCommandService {
	
	public void update(MemberUpdateCommand memberUpdateCommand) throws Exception;

}
