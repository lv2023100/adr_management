package com.adrmanagement.member.infrastructure.grpcservice.assmbler;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.adrmanagement.common.adr.domain.commands.AdrRecordCreateCommand;
import com.adrmanagement.common.member.domain.commands.MemberCreateCommand;
import com.adrmanagement.common.member.domain.query.MemberGetQuery;
import com.adrmanagement.common.util.SequenceGenerator;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetRequest;

public class MemberGetAssembler {
	
	public static MemberGetQuery requestToCommand(MemberGetRequest memberGetRequest) {
		MemberGetQuery memberGetQuery = new MemberGetQuery();
		BeanUtils.copyProperties(memberGetRequest, memberGetQuery);
		return memberGetQuery;
	}

}
