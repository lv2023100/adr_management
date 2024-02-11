package com.adrmanagement.member.domain.service.grpcclient;

import org.springframework.stereotype.Component;

@Component
public interface AdrRecordItemPublishClient {
	
	public void publishByMemberId(Long memberId, String name);

}
