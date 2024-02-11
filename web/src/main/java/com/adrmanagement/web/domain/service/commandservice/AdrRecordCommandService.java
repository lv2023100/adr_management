package com.adrmanagement.web.domain.service.commandservice;

import com.adrmanagement.web.infrastructure.controller.component.AdrRecordForm;

public interface AdrRecordCommandService {
	
	public void update(AdrRecordForm adrRecordForm)throws Exception;
	public void create(AdrRecordForm adrRecordForm)throws Exception;

}
