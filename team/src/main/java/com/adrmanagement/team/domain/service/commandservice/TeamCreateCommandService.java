package com.adrmanagement.team.domain.service.commandservice;

import com.adrmanagement.team.domain.service.command.TeamCreateCommand;

public interface TeamCreateCommandService {
	
	public void create(TeamCreateCommand teamCreateCommand) throws Exception;

}
