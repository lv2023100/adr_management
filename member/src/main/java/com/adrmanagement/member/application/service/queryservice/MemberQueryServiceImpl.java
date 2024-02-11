package com.adrmanagement.member.application.service.queryservice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrmanagement.common.member.domain.query.MemberGetQuery;
import com.adrmanagement.member.domain.model.aggregate.MemberAggregate;
import com.adrmanagement.member.domain.model.entity.Member;
import com.adrmanagement.member.domain.service.queryservice.MemberGetQueryService;
import com.adrmanagement.member.domain.service.repository.MemberRepository;

@Service
public class MemberQueryServiceImpl implements MemberGetQueryService {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public List<MemberAggregate> getMemberList(MemberGetQuery memberGetQuery) throws Exception {
		List<Member> memberList = memberRepository.findByIdList(memberGetQuery.getMemberIdList());
		List<MemberAggregate> memberAggregateList = memberList.stream().map(member -> {
			MemberAggregate memberAggregate = new MemberAggregate();
			BeanUtils.copyProperties(member, memberAggregate);
			return memberAggregate;
		}).collect(Collectors.toList());

		return memberAggregateList;
	}

	@Override
	public List<MemberAggregate> getMemberList() throws Exception {
		List<Member> memberList = memberRepository.findAll();
		List<MemberAggregate> memberAggregateList = memberList.stream().map(member -> {
			MemberAggregate memberAggregate = new MemberAggregate();
			BeanUtils.copyProperties(member, memberAggregate);
			return memberAggregate;
		}).collect(Collectors.toList());

		return memberAggregateList;
	}

	@Override
	public MemberAggregate getMemberByAccount(String account) throws Exception {
		Member member = memberRepository.findByAccount(account);
		MemberAggregate memberAggregate = new MemberAggregate();
		BeanUtils.copyProperties(member, memberAggregate);
		return memberAggregate;
	}

	@Override
	public Boolean isDuplicateAccount(String account) throws Exception {
		return memberRepository.isAccountExist(account);
	}

}
