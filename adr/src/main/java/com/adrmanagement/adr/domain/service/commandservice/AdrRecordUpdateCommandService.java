package com.adrmanagement.adr.domain.service.commandservice;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.adrmanagement.common.adr.domain.commands.AdrRecordUpdateCommand;

@Service
public interface AdrRecordUpdateCommandService {
	
	public void update(AdrRecordUpdateCommand adrRecordUpdateCommand) throws Exception;

}
