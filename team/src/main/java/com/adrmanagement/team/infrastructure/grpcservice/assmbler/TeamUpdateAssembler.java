package com.adrmanagement.team.infrastructure.grpcservice.assmbler;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.adrmanagement.common.util.ProtoBeanUtils;
import com.adrmanagement.team.domain.service.command.TeamUpdateCommand;
import com.adrmanagement.team.infrastructure.grpcservice.TeamUpdateRequest;

public class TeamUpdateAssembler {
	
	public static TeamUpdateCommand rquestToCommand(TeamUpdateRequest teamUpdateRequest) throws IOException {
		TeamUpdateCommand teamUpdateCommand = ProtoBeanUtils.toPojoBean(TeamUpdateCommand.class, teamUpdateRequest);
		LocalDateTime now = LocalDateTime.now();
		teamUpdateCommand.setUpdateTime(now);
		return teamUpdateCommand;
	}

}
