package com.adrmanagement.member.domain.service.repository.factory;


import org.apache.commons.lang3.StringUtils;

import com.adrmanagement.common.member.domain.commands.MemberUpdateCommand;
import com.adrmanagement.member.domain.model.entity.Member;

public class MemberFactory {
	
	public static Member memberUpdateCommandToMember(MemberUpdateCommand memberUpdateCommand, Member member) {
		member.setName(memberUpdateCommand.getName());
		member.setEmail(memberUpdateCommand.getEmail());
		member.setPhoneNumber(memberUpdateCommand.getPhoneNumber());
		member.setUpdateTime(memberUpdateCommand.getUpdateTime());
		member.setAccount(memberUpdateCommand.getAccount());
		if(StringUtils.isNotBlank(memberUpdateCommand.getPassword())) {
			member.setPassword(memberUpdateCommand.getPassword());
		}
		return member;
	}

}
