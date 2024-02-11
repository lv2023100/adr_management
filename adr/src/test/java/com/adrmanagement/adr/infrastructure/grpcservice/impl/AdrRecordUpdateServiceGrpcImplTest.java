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

import com.adrmanagement.adr.domain.service.commandservice.AdrRecordUpdateCommandService;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordUpdateRequest;
import com.adrmanagement.common.adr.domain.commands.AdrRecordCreateCommand;
import com.adrmanagement.common.adr.domain.commands.AdrRecordUpdateCommand;
import com.adrmanagement.common.infrastructure.grpcservice.GenericResponseOuterClass.GenericResponse;
import com.adrmanagement.common.util.SequenceGenerator;

import io.grpc.stub.StreamObserver;
@RunWith(MockitoJUnitRunner.class)
public class AdrRecordUpdateServiceGrpcImplTest {
	
	@InjectMocks
	private AdrRecordUpdateServiceGrpcImpl grpcService;

	@Mock
	private AdrRecordUpdateCommandService adrRecordUpdateCommandServiceMock;
	
	@Captor
    private ArgumentCaptor<AdrRecordUpdateCommand> adrRecordUpdateCommandCaptor;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void update() throws Exception {
		Long adrId = SequenceGenerator.genId();
		AdrRecordUpdateRequest request = AdrRecordUpdateRequest.newBuilder()
				.setAdrId(adrId)
				.setTitle("test").build();
		doNothing().when(adrRecordUpdateCommandServiceMock).update(any(AdrRecordUpdateCommand.class));

		StreamObserver<GenericResponse> responseObserver = mock(StreamObserver.class);
		grpcService.update(request, responseObserver);

		verify(adrRecordUpdateCommandServiceMock, times(1)).update(adrRecordUpdateCommandCaptor.capture());;
		
		ArgumentCaptor<GenericResponse> responseCaptor = ArgumentCaptor.forClass(GenericResponse.class);
        verify(responseObserver).onNext(responseCaptor.capture());

		assertEquals("success",responseCaptor.getValue().getMessage());
	}

}
