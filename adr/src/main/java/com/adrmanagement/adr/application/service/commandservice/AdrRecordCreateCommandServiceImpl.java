package com.adrmanagement.adr.application.service.commandservice;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrmanagement.adr.domain.service.commandservice.AdrRecordCreateCommandService;
import com.adrmanagement.adr.domain.service.event.handler.AdrRecordEventHandler;
import com.adrmanagement.common.adr.domain.commands.AdrRecordCreateCommand;
import com.adrmanagement.common.enums.GenericResponseEnum;

@Service
public class AdrRecordCreateCommandServiceImpl implements AdrRecordCreateCommandService {

	Logger log = LoggerFactory.getLogger(AdrRecordCreateCommandServiceImpl.class);

	@Autowired
	private CommandGateway commandGateway;

	@Override
	public void create(AdrRecordCreateCommand adrRecordCreateCommand) throws Exception {
		commandGateway.sendAndWait(adrRecordCreateCommand);

	}

}
