package com.adrmanagement.web.application.service.commandservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordCreateRequest;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordUpdateRequest;
import com.adrmanagement.common.util.ProtoBeanUtils;
import com.adrmanagement.web.domain.service.commandservice.AdrRecordCommandService;
import com.adrmanagement.web.domain.service.grpcclient.AdrRecordClient;
import com.adrmanagement.web.infrastructure.controller.component.AdrRecordForm;
import com.adrmanagement.web.infrastructure.security.MemberDetails;
import com.adrmanagement.web.infrastructure.security.MemberInfo;

@Service
public class AdrRecordCommandServiceImpl implements AdrRecordCommandService{
	
	@Autowired
	private AdrRecordClient adrRecordClient;

	@Override
	@Caching(evict = {@CacheEvict(cacheNames = "adr", key = "#adrRecordForm.adrId"),
			 @CacheEvict(cacheNames = "adrItems", key="#adrRecordForm.teamId")})
	public void update(AdrRecordForm adrRecordForm) throws Exception {
		var adrRecordUpdateRequest = AdrRecordUpdateRequest.newBuilder();
		ProtoBeanUtils.toProtoBean(adrRecordUpdateRequest, adrRecordForm);
		MemberInfo memberInfo =  
				((MemberDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMember();
		adrRecordUpdateRequest.setEditorMemberId(memberInfo.getMemberId());
		adrRecordUpdateRequest.setEditorMemberName(memberInfo.getName());
		adrRecordClient.update(adrRecordUpdateRequest.build());
		
	}

	@Override
	@CacheEvict(cacheNames = "adrItems", key="#adrRecordForm.teamId")
	public void create(AdrRecordForm adrRecordForm) throws Exception {
		var adrRecordCreateRequest = AdrRecordCreateRequest.newBuilder();
		ProtoBeanUtils.toProtoBean(adrRecordCreateRequest, adrRecordForm);
		MemberInfo memberInfo =  
				((MemberDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMember();
		adrRecordCreateRequest.setEditorMemberId(memberInfo.getMemberId());
		adrRecordCreateRequest.setEditorMemberName(memberInfo.getName());
		adrRecordClient.create(adrRecordCreateRequest.build());
		
	}

}
