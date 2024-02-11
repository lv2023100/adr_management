package com.adrmanagement.web.application.service.commandservice;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import com.adrmanagement.common.infrastructure.grpcservice.GenericResponseOuterClass.GenericResponse;
import com.adrmanagement.common.util.ProtoBeanUtils;
import com.adrmanagement.member.infrastructure.grpcservice.MemberCreateRequest;
import com.adrmanagement.member.infrastructure.grpcservice.MemberUpdateRequest;
import com.adrmanagement.web.domain.service.commandservice.MemberCommandService;
import com.adrmanagement.web.domain.service.grpcclient.MemberClient;
import com.adrmanagement.web.infrastructure.controller.component.MemberForm;
@Service
public class MemberCommandServiceImpl implements MemberCommandService{
	
	@Autowired
	private MemberClient memberClient;

	@Override
	@Caching(evict = {@CacheEvict(cacheNames = "member", key = "#memberForm.memberId"),
			 @CacheEvict(cacheNames = "members", allEntries = true)})
	public GenericResponse update(MemberForm memberForm) throws Exception {
		var memberUpdateRequestBuild = MemberUpdateRequest.newBuilder();
		if(StringUtils.isNotBlank( memberForm.getPassword())) {
			memberForm.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(memberForm.getPassword()));
		}
		ProtoBeanUtils.toProtoBean(memberUpdateRequestBuild, memberForm);
		return memberClient.update(memberUpdateRequestBuild.build());
	}
	
	@Override
	@CacheEvict(cacheNames = "members", allEntries = true)
	public GenericResponse create(MemberForm memberForm) throws Exception {
		var memberCreateRequestBuild = MemberCreateRequest.newBuilder();
		if(StringUtils.isNotBlank( memberForm.getPassword())) {
			memberForm.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(memberForm.getPassword()));
		}
		ProtoBeanUtils.toProtoBean(memberCreateRequestBuild, memberForm);
		return memberClient.create(memberCreateRequestBuild.build());
	}

}
