package com.adrmanagement.adr.application.service.commandservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrmanagement.adr.domain.model.entity.AdrRecordItem;
import com.adrmanagement.adr.domain.service.commandservice.AdrRecordItemPublishCommandService;
import com.adrmanagement.adr.domain.service.repository.AdrRecordItemRepository;
import com.adrmanagement.common.adr.domain.commands.AdrRecordItemPublishByMemberIdCommand;
import com.adrmanagement.common.adr.domain.commands.AdrRecordItemRepublishCommand;

@Service
public class AdrRecordItemPublishCommandServiceImpl implements AdrRecordItemPublishCommandService{
	
	@Autowired
	private AdrRecordItemRepository adrRecordItemRepository;
	
	@Autowired
	private CommandGateway commandGateway;

	@Override
	public List<CompletableFuture<String>> publishByMemberId(
			AdrRecordItemPublishByMemberIdCommand adrRecordItemPublishByMemberIdCommand) throws Exception {
		List<AdrRecordItem> adrRecordItemList = adrRecordItemRepository.findByEditorMemberId(adrRecordItemPublishByMemberIdCommand.getMemberId());
		List<CompletableFuture<String>> completableFutureList = new ArrayList<CompletableFuture<String>>();
		for(AdrRecordItem adrRecordItem :adrRecordItemList) {
			AdrRecordItemRepublishCommand adrRecordItemRepublishCommand = new AdrRecordItemRepublishCommand();
			BeanUtils.copyProperties(adrRecordItem, adrRecordItemRepublishCommand);
			adrRecordItemRepublishCommand.setEditorMemberName(adrRecordItemPublishByMemberIdCommand.getEditorMemberName());
			CompletableFuture<String> completableFuture= commandGateway.send(adrRecordItemRepublishCommand);
			completableFutureList.add(completableFuture);
		}
		return completableFutureList;
	}

}
