package com.adrmanagement.member.infrastructure.grpcservice.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adrmanagement.common.member.domain.query.MemberGetQuery;
import com.adrmanagement.common.util.ProtoBeanUtils;
import com.adrmanagement.member.domain.model.aggregate.MemberAggregate;
import com.adrmanagement.member.domain.service.queryservice.MemberGetQueryService;
import com.adrmanagement.member.infrastructure.grpcservice.Member.MemberGrpcDto;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetRequest;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetResponse;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetResponse.Builder;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetServiceGrpc.MemberGetServiceImplBase;
import com.adrmanagement.member.infrastructure.grpcservice.assmbler.MemberGetAssembler;

import io.grpc.stub.StreamObserver;

@GRpcService
public class MemberGetServiceGrpcImpl extends MemberGetServiceImplBase {

	Logger log = LoggerFactory.getLogger(MemberGetServiceGrpcImpl.class);

	@Autowired
	private MemberGetQueryService memberGetQueryService;

	@Override
	public void get(MemberGetRequest memberGetRequest, StreamObserver<MemberGetResponse> responseObserver) {
		try {
			MemberGetQuery memberGetQuery = MemberGetAssembler.requestToCommand(memberGetRequest);
			List<MemberAggregate> memberAggregateList = memberGetQueryService.getMemberList(memberGetQuery);
			Builder memberGetResponseBuilder = MemberGetResponse.newBuilder();
			List<MemberGrpcDto> memberList = memberAggregateList.stream().map(memberAggregate -> {
				var memberBuilder = MemberGrpcDto.newBuilder();
				try {
					ProtoBeanUtils.toProtoBean(memberBuilder, memberAggregate);
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
				return memberBuilder.build();
			}).collect(Collectors.toList());
			memberGetResponseBuilder.addAllMember(memberList);
			responseObserver.onNext(memberGetResponseBuilder.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			responseObserver.onCompleted();
		}

	}

}
