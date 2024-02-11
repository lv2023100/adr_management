package com.adrmanagement.team.infrastructure.grpcservice.assmbler;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.adrmanagement.common.util.ProtoBeanUtils;
import com.adrmanagement.common.util.SequenceGenerator;
import com.adrmanagement.team.domain.service.command.TeamCreateCommand;
import com.adrmanagement.team.domain.service.command.TeamUpdateCommand;
import com.adrmanagement.team.infrastructure.grpcservice.TeamCreateRequest;

public class TeamCreateAssembler {
	
	public static TeamCreateCommand rquestToCommand(TeamCreateRequest teamCreateRequest) throws IOException {
		TeamCreateCommand teamCreateCommand = ProtoBeanUtils.toPojoBean(TeamCreateCommand.class, teamCreateRequest);
		LocalDateTime now = LocalDateTime.now();
		teamCreateCommand.setTeamId(SequenceGenerator.genId());
		teamCreateCommand.setCreateTime(now);
		teamCreateCommand.setUpdateTime(now);
		return teamCreateCommand;
	}

}
