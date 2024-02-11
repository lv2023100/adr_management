package com.adrmanagement.adr.domain.service.commandservice;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.adrmanagement.common.adr.domain.commands.AdrRecordCreateCommand;
import com.adrmanagement.common.adr.domain.commands.AdrRecordItemPublishByMemberIdCommand;

public interface AdrRecordItemPublishCommandService {
	
	public List<CompletableFuture<String>> publishByMemberId(AdrRecordItemPublishByMemberIdCommand adrRecordItemPublishByMemberIdCommand) throws Exception;

}
