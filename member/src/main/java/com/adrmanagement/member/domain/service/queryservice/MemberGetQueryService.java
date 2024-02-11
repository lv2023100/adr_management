package com.adrmanagement.member.domain.service.queryservice;

import java.util.List;

import com.adrmanagement.common.member.domain.query.MemberGetQuery;
import com.adrmanagement.member.domain.model.aggregate.MemberAggregate;

public interface MemberGetQueryService {
	
	
	public List<MemberAggregate> getMemberList(MemberGetQuery memberGetQuery) throws Exception;
	public List<MemberAggregate> getMemberList() throws Exception;
	public MemberAggregate getMemberByAccount(String account) throws Exception;
	public Boolean isDuplicateAccount(String account) throws Exception;

}
