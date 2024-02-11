package com.adrmanagement.member.infrastructure.grpcservice.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adrmanagement.common.enums.GenericResponseEnum;
import com.adrmanagement.common.util.ProtoBeanUtils;
import com.adrmanagement.member.domain.model.aggregate.MemberAggregate;
import com.adrmanagement.member.domain.service.queryservice.MemberGetQueryService;
import com.adrmanagement.member.infrastructure.grpcservice.Member.MemberGrpcDto;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetListRequest;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetListResponse;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetListServiceGrpc.MemberGetListServiceImplBase;

import io.grpc.stub.StreamObserver;
@GRpcService
public class MemberGetListServiceGrpcImpl extends MemberGetListServiceImplBase{
	
	Logger log = LoggerFactory.getLogger(MemberGetListServiceGrpcImpl.class);
	
	@Autowired
	private MemberGetQueryService memberGetQueryService;
	
	@Override
	public void get(MemberGetListRequest memberGetListRequest, StreamObserver<MemberGetListResponse> responseObserver) {
		var memberGetListResponseBuilder = MemberGetListResponse.newBuilder();
		try {
			List<MemberAggregate> memberAggregateList = memberGetQueryService.getMemberList();
			List<MemberGrpcDto> memberGrpcDtoList = memberAggregateList.stream()
				    .map(memberAggregate -> {
				        var memberGrpcDtoBuilder = MemberGrpcDto.newBuilder();
				        try {
							ProtoBeanUtils.toProtoBean(memberGrpcDtoBuilder, memberAggregate);
						} catch (IOException e) {
							log.error(e.getMessage(),e);
						}
				        return memberGrpcDtoBuilder.build();
				    })
				    .collect(Collectors.toList());
			memberGetListResponseBuilder.addAllMemberList(memberGrpcDtoList);
			memberGetListResponseBuilder.setGenericResponse(GenericResponseEnum.success());
			responseObserver.onNext(memberGetListResponseBuilder.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			memberGetListResponseBuilder.setGenericResponse(GenericResponseEnum.error());
			responseObserver.onCompleted();
		}
	}

}
