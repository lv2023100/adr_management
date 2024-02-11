package com.adrmanagement.adr.domain.service.queryservice;

import java.util.List;

import com.adrmanagement.adr.domain.model.aggregate.AdrRecordItemAggregate;
import com.adrmanagement.common.adr.domain.query.AdrRecordItemGetListQuery;

public interface AdrRecordItemQueryService {
	
	public List<AdrRecordItemAggregate> getAdrRecordList(AdrRecordItemGetListQuery adrRecordItemGetListQuery) throws Exception;

}
