package com.adrmanagement.web.infrastructure.security;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.adrmanagement.common.util.ProtoBeanUtils;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetByAccountResponse;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListByMemberIdResponse;
import com.adrmanagement.web.domain.service.grpcclient.MemberClient;
import com.adrmanagement.web.domain.service.grpcclient.TeamClient;
import com.adrmanagement.web.infrastructure.security.MemberInfo.TeamInfo;

@Service
public class MemberDetailsService implements UserDetailsService {

	Logger log = LoggerFactory.getLogger(MemberDetailsService.class);

	@Autowired
	private MemberClient memberClient;

	@Autowired
	private TeamClient teamClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			MemberGetByAccountResponse memberGetByAccountResponse = memberClient.getMemberByAccount(username);
			if (memberGetByAccountResponse.getMemberId() == 0) {
				throw new UsernameNotFoundException(username);
			}
			TeamGetListByMemberIdResponse teamGetListByMemberIdResponse = teamClient
					.getTeamListByMemberId(memberGetByAccountResponse.getMemberId());
			MemberInfo memberInfo = ProtoBeanUtils.toPojoBean(MemberInfo.class, memberGetByAccountResponse);
			List<TeamInfo> teamInfoList = new ArrayList<MemberInfo.TeamInfo>();
			if (!CollectionUtils.isEmpty(teamGetListByMemberIdResponse.getTeamListList())) {
				teamInfoList = teamGetListByMemberIdResponse.getTeamListList().stream().map(team -> {
					TeamInfo teamInfo = new TeamInfo();
					teamInfo.setName(team.getName());
					teamInfo.setTeamId(team.getTeamId());
					return teamInfo;
				}).collect(Collectors.toList());

			}
			memberInfo.setTeamInfoList(teamInfoList);
			return new MemberDetails(memberInfo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
}
