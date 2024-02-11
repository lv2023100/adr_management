package com.adrmanagement.member.infrastructure.grpcclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemPublishByMemberIdRequest;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemPublishServiceGrpc.AdrRecordItemPublishServiceBlockingStub;
import com.adrmanagement.member.domain.service.grpcclient.AdrRecordItemPublishClient;

@Component
public class AdrRecordItemPublishClientImpl implements AdrRecordItemPublishClient{
	
	@Autowired
	private  AdrRecordItemPublishServiceBlockingStub adrRecordItemPublishServiceBlockingStub;

	@Override
	public void publishByMemberId(Long memberId, String name) {
		var adrRecordItemPublishByMemberIdRequestBuild= AdrRecordItemPublishByMemberIdRequest.newBuilder()
				.setMemberId(memberId)
				.setName(name);
		adrRecordItemPublishServiceBlockingStub.publishByMemberId(adrRecordItemPublishByMemberIdRequestBuild.build());
		
	}

}
