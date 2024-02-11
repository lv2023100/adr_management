package com.adrmanagement.adr.application.service.queryservice;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrmanagement.adr.domain.model.aggregate.AdrRecordItemAggregate;
import com.adrmanagement.adr.domain.service.queryservice.AdrRecordItemQueryService;
import com.adrmanagement.adr.domain.service.repository.AdrRecordItemRepository;
import com.adrmanagement.adr.infrastructure.grpcservice.impl.AdrRecordItemPublishServiceGrpImpl;
import com.adrmanagement.common.adr.domain.query.AdrRecordItemGetListQuery;

@Service
public class AdrRecordItemQueryServiceImpl implements AdrRecordItemQueryService {
	
	Logger log = LoggerFactory.getLogger(AdrRecordItemQueryServiceImpl.class);

	@Autowired
	private QueryGateway queryGateway;

	@Autowired
	private AdrRecordItemRepository adrRecordItemRepository;

	@Override
	public List<AdrRecordItemAggregate> getAdrRecordList(AdrRecordItemGetListQuery adrRecordItemGetListQuery)
			throws Exception {
//		AdrRecordItem adrRecordItemQuery = new AdrRecordItem();
//		adrRecordItemQuery.setTeamId(adrRecordItemGetListQuery.getTeamId());
//		List<AdrRecordItem> adrRecordItemList = adrRecordItemRepository.findAll(adrRecordItemQuery);
//
//		List<AdrRecordItemAggregate> adrRecordItemAggregateList = adrRecordItemList.stream().map(adrRecordItem -> {
//			AdrRecordItemAggregate adrRecordItemAggregate = new AdrRecordItemAggregate();
//			BeanUtils.copyProperties(adrRecordItem, adrRecordItemAggregate);
//			return adrRecordItemAggregate;
//		}).collect(Collectors.toList());
//		return adrRecordItemAggregateList;
		
		CompletableFuture<List<AdrRecordItemAggregate>> future = 
				queryGateway.query(adrRecordItemGetListQuery, ResponseTypes.multipleInstancesOf(AdrRecordItemAggregate.class));
		return future.get();
		
	}

}
