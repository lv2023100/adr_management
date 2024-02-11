package com.adrmanagement.team.infrastructure.grpcservice.impl;

import com.adrmanagement.team.infrastructure.grpcservice.TeamMemberGetRequest;
import com.adrmanagement.team.infrastructure.grpcservice.TeamMemberGetResponse;
import com.adrmanagement.team.infrastructure.grpcservice.TeamMemberGetServiceGrpc.TeamMemberGetServiceImplBase;

import io.grpc.stub.StreamObserver;

public class TeamMemeberGetServiceGrpcImpl extends TeamMemberGetServiceImplBase {
	
	@Override
	public void get(TeamMemberGetRequest teamMemberGetRequest, StreamObserver<TeamMemberGetResponse> responseObserver) {
		
	}

}
