package com.adrmanagement.member.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.adrmanagement.member.domain.model.entity.Member;
import com.adrmanagement.member.domain.service.repository.MemberRepository;
import com.adrmanagement.member.infrastructure.repository.mapper.MemberMapper;

@Repository
public class MemberRepositoryImpl implements MemberRepository{
	
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public void save(Member member) throws Exception {
		memberMapper.save(member);
	}
	
	@Override
	public List<Member> findByIdList(List<Long> memberIdList) throws Exception {
		List<Member> memberList = null;
		if(CollectionUtils.isEmpty(memberIdList)) {
			memberList = memberMapper.findAll();
		}else {
			memberList = memberMapper.findAllById(memberIdList);
		}
		return memberList;
		
	}
	
	@Override
	public List<Member> findAll() throws Exception {
		List<Member> memberList = memberMapper.findAll();
		return memberList;
		
	}

	@Override
	public Member findById(Long memberId) throws Exception {
		return memberMapper.findById(memberId).orElseThrow();
		
	}
	
	@Override
	public Member findByAccount(String account) throws Exception {
		return memberMapper.findByAccount(account).orElseThrow();
		
	}
	
	@Override
	public Boolean isAccountExist(String account) throws Exception {
		return memberMapper.findByAccount(account).isPresent();
		
	}
	
	

}
