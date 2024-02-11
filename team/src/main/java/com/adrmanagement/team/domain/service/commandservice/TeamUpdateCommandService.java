package com.adrmanagement.team.domain.service.commandservice;

import com.adrmanagement.team.domain.service.command.TeamUpdateCommand;

public interface TeamUpdateCommandService {
	
	public void update(TeamUpdateCommand teamUpdateCommand) throws Exception; 

}
