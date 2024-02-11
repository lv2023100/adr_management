package com.adrmanagement.member.infrastructure.grpcservice.impl;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adrmanagement.common.util.ProtoBeanUtils;
import com.adrmanagement.member.domain.model.aggregate.MemberAggregate;
import com.adrmanagement.member.domain.service.queryservice.MemberGetQueryService;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetByAccountRequest;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetByAccountResponse;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetByAccountServiceGrpc.MemberGetByAccountServiceImplBase;

import io.grpc.stub.StreamObserver;
@GRpcService
public class MemberGetByAccountServiceImpl extends MemberGetByAccountServiceImplBase{
	
	Logger log = LoggerFactory.getLogger(MemberGetByAccountServiceImpl.class);
	
	@Autowired
	private MemberGetQueryService memberGetQueryService;
	
	@Override
	public void get(MemberGetByAccountRequest memberGetByAccountRequest, StreamObserver<MemberGetByAccountResponse> responseObserver) {
		try {
			MemberAggregate memberAggregate = memberGetQueryService.getMemberByAccount(memberGetByAccountRequest.getAccount());
			var memberGetByAccountResponseBuild = MemberGetByAccountResponse.newBuilder();
			ProtoBeanUtils.toProtoBean(memberGetByAccountResponseBuild, memberAggregate);
			responseObserver.onNext(memberGetByAccountResponseBuild.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			responseObserver.onCompleted();
		}
	}

}
