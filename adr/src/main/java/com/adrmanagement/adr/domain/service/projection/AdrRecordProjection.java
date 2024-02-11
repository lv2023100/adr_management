package com.adrmanagement.adr.domain.service.projection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adrmanagement.adr.domain.model.aggregate.AdrRecordAggregate;
import com.adrmanagement.adr.domain.model.aggregate.AdrRecordItemAggregate;
import com.adrmanagement.adr.domain.model.entity.AdrRecord;
import com.adrmanagement.adr.domain.model.entity.AdrRecordItem;
import com.adrmanagement.adr.domain.service.repository.AdrRecordItemRepository;
import com.adrmanagement.adr.domain.service.repository.AdrRecordRepository;
import com.adrmanagement.adr.domain.service.repository.factory.AdrRecordFactory;
import com.adrmanagement.common.adr.domain.query.AdrRecordGetQuery;
import com.adrmanagement.common.adr.domain.query.AdrRecordItemGetListQuery;

@Component
public class AdrRecordProjection {

	Logger log = LoggerFactory.getLogger(AdrRecordProjection.class);

	@Autowired
	private AdrRecordRepository adrRecordRepository;

	@QueryHandler
	public AdrRecordAggregate adrRecordGet(AdrRecordGetQuery adrRecordGetQuery) {
		try {
			AdrRecord adrRecord = adrRecordRepository.findById(adrRecordGetQuery.getAdrId());
			
			return AdrRecordFactory.adrRecordToAdrRecordAggregate(adrRecord);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}

	}
	
	@Autowired
	private AdrRecordItemRepository adrRecordItemRepository;

	
	@QueryHandler
	public List<AdrRecordItemAggregate> adrRecordItemGetList(AdrRecordItemGetListQuery adrRecordItemGetListQuery) {
		try {
			AdrRecordItem adrRecordItemQuery = new AdrRecordItem();
			adrRecordItemQuery.setTeamId(adrRecordItemGetListQuery.getTeamId());
			List<AdrRecordItem> adrRecordItemList = adrRecordItemRepository.findAll(adrRecordItemQuery);
			List<AdrRecordItemAggregate> adrRecordItemAggregateList = new ArrayList<AdrRecordItemAggregate>();
			for(AdrRecordItem adrRecordItem: adrRecordItemList) {
				AdrRecordItemAggregate adrRecordItemAggregate = new AdrRecordItemAggregate();
				BeanUtils.copyProperties(adrRecordItem, adrRecordItemAggregate);
				adrRecordItemAggregateList.add(adrRecordItemAggregate);
			}
			return adrRecordItemAggregateList;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
		
	}
	

}
