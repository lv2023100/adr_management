package com.adrmanagement.web.domain.service.grpcclient;

import java.util.List;

import com.adrmanagement.common.infrastructure.grpcservice.GenericResponseOuterClass.GenericResponse;
import com.adrmanagement.member.infrastructure.grpcservice.MemberCreateRequest;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetByAccountResponse;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetListResponse;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetResponse;
import com.adrmanagement.member.infrastructure.grpcservice.MemberUpdateRequest;

public interface MemberClient {
	
	public MemberGetResponse getMember(List<Long> memberIdList) throws Exception ;
	public GenericResponse update(MemberUpdateRequest memberUpdateRequest) throws Exception;
	public GenericResponse create(MemberCreateRequest memberCreateRequest) throws Exception;
	public MemberGetByAccountResponse getMemberByAccount(String account) throws Exception;
	public MemberGetListResponse getMemberList() throws Exception;

}
