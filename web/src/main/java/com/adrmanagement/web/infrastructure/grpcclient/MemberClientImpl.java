package com.adrmanagement.web.infrastructure.grpcclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.adrmanagement.common.infrastructure.grpcservice.GenericResponseOuterClass.GenericResponse;
import com.adrmanagement.member.infrastructure.grpcservice.MemberCreateRequest;
import com.adrmanagement.member.infrastructure.grpcservice.MemberCreateServiceGrpc.MemberCreateServiceBlockingStub;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetByAccountRequest;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetByAccountResponse;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetByAccountServiceGrpc.MemberGetByAccountServiceBlockingStub;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetListRequest;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetListResponse;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetListServiceGrpc.MemberGetListServiceBlockingStub;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetRequest;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetResponse;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetServiceGrpc.MemberGetServiceBlockingStub;
import com.adrmanagement.member.infrastructure.grpcservice.MemberUpdateRequest;
import com.adrmanagement.member.infrastructure.grpcservice.MemberUpdateServiceGrpc.MemberUpdateServiceBlockingStub;
import com.adrmanagement.web.domain.service.grpcclient.MemberClient;

@Component
public class MemberClientImpl implements MemberClient{
	
	@Autowired
	private  MemberGetServiceBlockingStub memberGetServiceBlockingStub;
	
	@Autowired
	private  MemberUpdateServiceBlockingStub memberUpdateServiceBlockingStub;
	
	@Autowired
	private  MemberCreateServiceBlockingStub memberCreateServiceBlockingStub;
	
	@Autowired
	private MemberGetByAccountServiceBlockingStub memberGetByAccountServiceBlockingStub;
	
	@Autowired
	private MemberGetListServiceBlockingStub memberGetListServiceBlockingStub;

	@Override
	public MemberGetResponse getMember(List<Long> memberIdList) throws Exception {
		var memberRequestBuild= MemberGetRequest.newBuilder();
			if(!CollectionUtils.isEmpty(memberIdList)) {
				memberRequestBuild.addAllMemberId(memberIdList);
			}
				
		return memberGetServiceBlockingStub.get(memberRequestBuild.build());
		
	}
	
	@Override
	public MemberGetListResponse getMemberList() throws Exception {
		var memberGetListRequestBuild= MemberGetListRequest.newBuilder();

				
		return memberGetListServiceBlockingStub.get(memberGetListRequestBuild.build());
		
	}
	
	@Override
	public MemberGetByAccountResponse getMemberByAccount(String account) throws Exception {
		var memberRequestBuild= MemberGetByAccountRequest.newBuilder();
		memberRequestBuild.setAccount(account);
				
		return memberGetByAccountServiceBlockingStub.get(memberRequestBuild.build());
		
	}
	
	@Override
	public GenericResponse update(MemberUpdateRequest memberUpdateRequest) throws Exception {
		return memberUpdateServiceBlockingStub.update(memberUpdateRequest);
		
	}
	
	@Override
	public GenericResponse create(MemberCreateRequest memberCreateRequest) throws Exception {
		return memberCreateServiceBlockingStub.create(memberCreateRequest);
		
	}

}
