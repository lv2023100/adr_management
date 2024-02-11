package com.adrmanagement.web.domain.service.queryservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adrmanagement.member.infrastructure.grpcservice.MemberGetListResponse;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetResponse;
import com.adrmanagement.member.infrastructure.grpcservice.Member.MemberGrpcDto;
@Service
public interface MemberQueryService {
	
	public List<MemberGrpcDto> getMemberList(List<Long> memberIdList) throws Exception;
	public List<MemberGrpcDto> getMemberList() throws Exception;
	public MemberGrpcDto getMember(Long memberId) throws Exception;

}
