package com.adrmanagement.member.infrastructure.grpcservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemPublishServiceGrpc;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemPublishServiceGrpc.AdrRecordItemPublishServiceBlockingStub;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

@Configuration
public class GrpcClientConfiguration {
	
	@Bean
	AdrRecordItemPublishServiceBlockingStub adrRecordItemPublishServiceBlockingStub() {
        Channel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
            .usePlaintext()
            .build();

        return AdrRecordItemPublishServiceGrpc.newBlockingStub(channel);
    }

}
