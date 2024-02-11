package com.adrmanagement.web.application.service.queryservice;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.adrmanagement.member.infrastructure.grpcservice.Member.MemberGrpcDto;
import com.adrmanagement.web.domain.service.grpcclient.MemberClient;
import com.adrmanagement.web.domain.service.queryservice.MemberQueryService;
@Service
public class MemberQueryServiceImpl implements MemberQueryService{
	
	@Autowired
	private MemberClient memberClient;

	@Override
	@Cacheable(value="members")
	public List<MemberGrpcDto> getMemberList(List<Long> memberIdList) throws Exception {
		return memberClient.getMember(memberIdList).getMemberList();

	}
	
	@Override
	@Cacheable(value="member" ,key="#memberId")
	public MemberGrpcDto getMember(Long memberId) throws Exception {
		return memberClient.getMember(Arrays.asList(memberId)).getMemberList().get(0);

	}
	
	@Override
	@Cacheable(value="members")
	public List<MemberGrpcDto> getMemberList() throws Exception {
		return memberClient.getMemberList().getMemberListList();

	}

}
