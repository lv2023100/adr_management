package com.adrmanagement.member.domain.service.commandservice;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.adrmanagement.common.member.domain.commands.MemberCreateCommand;

@Service
public interface MemberCreateCommandService {
	
	public void create(MemberCreateCommand memberCreateCommand) throws Exception;

}
