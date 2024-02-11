package com.adrmanagement.member.domain.service.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.adrmanagement.member.domain.model.entity.Member;

@Repository
public interface MemberRepository {
	
	void save(Member member) throws Exception;
	List<Member> findByIdList(List<Long> memberIdList) throws Exception;
	Member findById(Long memberId) throws Exception;
	List<Member> findAll() throws Exception;
	Member findByAccount(String account) throws Exception;
	public Boolean isAccountExist(String account) throws Exception;

}
