package com.adrmanagement.adr.infrastructure.grpcservice.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.adrmanagement.adr.domain.service.commandservice.AdrRecordCreateCommandService;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordCreateRequest;
import com.adrmanagement.common.adr.domain.commands.AdrRecordCreateCommand;
import com.adrmanagement.common.infrastructure.grpcservice.GenericResponseOuterClass.GenericResponse;

import io.grpc.stub.StreamObserver;

@RunWith(MockitoJUnitRunner.class)
public class AdrRecordCreateServiceGrpcImplTest {

	@InjectMocks
	private AdrRecordCreateServiceGrpcImpl grpcService;

	@Mock
	private AdrRecordCreateCommandService adrRecordCreateCommandService;
	
	@Captor
    private ArgumentCaptor<AdrRecordCreateCommand> commandCaptor;


	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreate() throws Exception {

		AdrRecordCreateRequest request = AdrRecordCreateRequest.newBuilder().setTitle("test").build();
		doNothing().when(adrRecordCreateCommandService).create(any(AdrRecordCreateCommand.class));

		StreamObserver<GenericResponse> responseObserver = mock(StreamObserver.class);
		grpcService.create(request, responseObserver);

		verify(adrRecordCreateCommandService, times(1)).create(commandCaptor.capture());;
		
		ArgumentCaptor<GenericResponse> responseCaptor = ArgumentCaptor.forClass(GenericResponse.class);
        verify(responseObserver).onNext(responseCaptor.capture());

		assertEquals("success",responseCaptor.getValue().getMessage());
	}

}
