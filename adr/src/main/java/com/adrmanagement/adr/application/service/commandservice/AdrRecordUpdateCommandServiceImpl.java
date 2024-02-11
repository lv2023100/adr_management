package com.adrmanagement.adr.application.service.commandservice;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrmanagement.adr.domain.service.commandservice.AdrRecordUpdateCommandService;
import com.adrmanagement.common.adr.domain.commands.AdrRecordUpdateCommand;

@Service
public class AdrRecordUpdateCommandServiceImpl implements AdrRecordUpdateCommandService{
	
	@Autowired
	private CommandGateway commandGateway;

	@Override
	public void update(AdrRecordUpdateCommand adrRecordUpdateCommand) throws Exception{
		commandGateway.sendAndWait(adrRecordUpdateCommand);
		
	}

}
