package com.adrmanagement.web.domain.service.commandservice;

import com.adrmanagement.web.infrastructure.controller.component.TeamForm;

public interface TeamCommandService {
	
	public void update(TeamForm teamForm)throws Exception;
	public void create(TeamForm teamForm) throws Exception;

}
