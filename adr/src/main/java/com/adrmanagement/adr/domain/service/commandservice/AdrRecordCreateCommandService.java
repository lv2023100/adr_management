package com.adrmanagement.adr.domain.service.commandservice;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.adrmanagement.common.adr.domain.commands.AdrRecordCreateCommand;

@Service
public interface AdrRecordCreateCommandService {
	
	public void create(AdrRecordCreateCommand adrRecordCreateCommand) throws Exception;

}
