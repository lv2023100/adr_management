package com.adrmanagement.web.infrastructure.grpcservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordCreateServiceGrpc;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordCreateServiceGrpc.AdrRecordCreateServiceBlockingStub;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordGetServiceGrpc;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordGetServiceGrpc.AdrRecordGetServiceBlockingStub;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemGetListServiceGrpc;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemGetListServiceGrpc.AdrRecordItemGetListServiceBlockingStub;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordUpdateServiceGrpc;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordUpdateServiceGrpc.AdrRecordUpdateServiceBlockingStub;
import com.adrmanagement.member.infrastructure.grpcservice.MemberCreateServiceGrpc;
import com.adrmanagement.member.infrastructure.grpcservice.MemberCreateServiceGrpc.MemberCreateServiceBlockingStub;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetByAccountServiceGrpc;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetByAccountServiceGrpc.MemberGetByAccountServiceBlockingStub;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetListServiceGrpc;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetListServiceGrpc.MemberGetListServiceBlockingStub;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetServiceGrpc;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetServiceGrpc.MemberGetServiceBlockingStub;
import com.adrmanagement.member.infrastructure.grpcservice.MemberUpdateServiceGrpc;
import com.adrmanagement.member.infrastructure.grpcservice.MemberUpdateServiceGrpc.MemberUpdateServiceBlockingStub;
import com.adrmanagement.team.infrastructure.grpcservice.TeamCreateServiceGrpc;
import com.adrmanagement.team.infrastructure.grpcservice.TeamCreateServiceGrpc.TeamCreateServiceBlockingStub;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListByMemberIdServiceGrpc;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListByMemberIdServiceGrpc.TeamGetListByMemberIdServiceBlockingStub;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListServiceGrpc;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetListServiceGrpc.TeamGetListServiceBlockingStub;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetServiceGrpc;
import com.adrmanagement.team.infrastructure.grpcservice.TeamGetServiceGrpc.TeamGetServiceBlockingStub;
import com.adrmanagement.team.infrastructure.grpcservice.TeamUpdateServiceGrpc;
import com.adrmanagement.team.infrastructure.grpcservice.TeamUpdateServiceGrpc.TeamUpdateServiceBlockingStub;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

@Configuration
public class GrpcClientConfiguration {
	
	@Value("${adr.domain-name}")
	private String adrDomainName;
	
	@Value("${team.domain-name}")
	private String teamDomainName;
	
	@Value("${member.domain-name}")
	private String memberDomainName;
	
	@Bean
	AdrRecordGetServiceBlockingStub adrRecordGetServiceBlockingStub() {
        Channel channel = ManagedChannelBuilder.forAddress(adrDomainName, 6565)
            .usePlaintext()
            .build();

        return AdrRecordGetServiceGrpc.newBlockingStub(channel);
    }
	
	@Bean
	AdrRecordItemGetListServiceBlockingStub adrRecordItemGetListServiceBlockingStub() {
        Channel channel = ManagedChannelBuilder.forAddress(adrDomainName, 6565)
            .usePlaintext()
            .build();

        return AdrRecordItemGetListServiceGrpc.newBlockingStub(channel);
    }
	
	@Bean
	AdrRecordUpdateServiceBlockingStub adrRecordUpdateServiceBlockingStub() {
        Channel channel = ManagedChannelBuilder.forAddress(adrDomainName, 6565)
            .usePlaintext()
            .build();

        return AdrRecordUpdateServiceGrpc.newBlockingStub(channel);
    }
	
	@Bean
	AdrRecordCreateServiceBlockingStub adrRecordCreateServiceBlockingStub() {
        Channel channel = ManagedChannelBuilder.forAddress(adrDomainName, 6565)
            .usePlaintext()
            .build();

        return AdrRecordCreateServiceGrpc.newBlockingStub(channel);
    }
	
	@Bean
	TeamGetServiceBlockingStub teamGetServiceBlockingStub() {
        Channel channel = ManagedChannelBuilder.forAddress(teamDomainName, 6567)
            .usePlaintext()
            .build();

        return TeamGetServiceGrpc.newBlockingStub(channel);
    }
	
	@Bean
	TeamGetListByMemberIdServiceBlockingStub teamGetListByMemberIdServiceBlockingStub() {
        Channel channel = ManagedChannelBuilder.forAddress(teamDomainName, 6567)
            .usePlaintext()
            .build();

        return TeamGetListByMemberIdServiceGrpc.newBlockingStub(channel);
    }
	
	@Bean
	TeamGetListServiceBlockingStub teamGetListServiceBlockingStub() {
        Channel channel = ManagedChannelBuilder.forAddress(teamDomainName, 6567)
            .usePlaintext()
            .build();

        return TeamGetListServiceGrpc.newBlockingStub(channel);
    }
	
	@Bean
	TeamUpdateServiceBlockingStub teamUpdateServiceBlockingStub() {
        Channel channel = ManagedChannelBuilder.forAddress(teamDomainName, 6567)
            .usePlaintext()
            .build();

        return TeamUpdateServiceGrpc.newBlockingStub(channel);
    }
	
	@Bean
	TeamCreateServiceBlockingStub teamCreateServiceBlockingStub() {
        Channel channel = ManagedChannelBuilder.forAddress(teamDomainName, 6567)
            .usePlaintext()
            .build();

        return TeamCreateServiceGrpc.newBlockingStub(channel);
    }
	
	@Bean
	MemberGetServiceBlockingStub memberGetServiceBlockingStub() {
        Channel channel = ManagedChannelBuilder.forAddress(memberDomainName, 6566)
            .usePlaintext()
            .build();

        return MemberGetServiceGrpc.newBlockingStub(channel);
    }
	
	@Bean
	MemberGetByAccountServiceBlockingStub memberGetByAccountServiceBlockingStub() {
        Channel channel = ManagedChannelBuilder.forAddress(memberDomainName, 6566)
            .usePlaintext()
            .build();

        return MemberGetByAccountServiceGrpc.newBlockingStub(channel);
    }
	
	@Bean
	MemberGetListServiceBlockingStub memberGetListServiceBlockingStub() {
        Channel channel = ManagedChannelBuilder.forAddress(memberDomainName, 6566)
            .usePlaintext()
            .build();

        return MemberGetListServiceGrpc.newBlockingStub(channel);
    }
	
	@Bean
	MemberUpdateServiceBlockingStub memberUpdateServiceBlockingStub() {
        Channel channel = ManagedChannelBuilder.forAddress(memberDomainName, 6566)
            .usePlaintext()
            .build();

        return MemberUpdateServiceGrpc.newBlockingStub(channel);
    }
	
	@Bean
	MemberCreateServiceBlockingStub memberCreateServiceBlockingStub() {
        Channel channel = ManagedChannelBuilder.forAddress(memberDomainName, 6566)
            .usePlaintext()
            .build();

        return MemberCreateServiceGrpc.newBlockingStub(channel);
    }

}
