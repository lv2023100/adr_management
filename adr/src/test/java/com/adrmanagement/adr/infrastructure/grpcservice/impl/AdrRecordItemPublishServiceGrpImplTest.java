package com.adrmanagement.adr.infrastructure.grpcservice.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.adrmanagement.adr.domain.service.commandservice.AdrRecordItemPublishCommandService;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemGetListResponse;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemPublishByMemberIdRequest;
import com.adrmanagement.common.adr.domain.commands.AdrRecordItemPublishByMemberIdCommand;
import com.adrmanagement.common.adr.domain.query.AdrRecordItemGetListQuery;
import com.adrmanagement.common.infrastructure.grpcservice.GenericResponseOuterClass.GenericResponse;
import com.adrmanagement.common.util.SequenceGenerator;

import io.grpc.stub.StreamObserver;

@RunWith(MockitoJUnitRunner.class)
public class AdrRecordItemPublishServiceGrpImplTest {
	
	@InjectMocks
	private AdrRecordItemPublishServiceGrpImpl grpcService;

	@Mock
	private AdrRecordItemPublishCommandService adrRecordItemPublishCommandServiceMock;
	
	@Captor
    private ArgumentCaptor<AdrRecordItemPublishByMemberIdCommand> adrRecordItemPublishByMemberIdCommandCaptor;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void publishByMemberId() throws Exception {
		AdrRecordItemPublishByMemberIdRequest request = AdrRecordItemPublishByMemberIdRequest.newBuilder().setMemberId(1L).build();
		
		when(adrRecordItemPublishCommandServiceMock.publishByMemberId(any(AdrRecordItemPublishByMemberIdCommand.class))).thenReturn(null, null);
		StreamObserver<GenericResponse> responseObserver = mock(StreamObserver.class);
		grpcService.publishByMemberId(request, responseObserver);
		
		verify(adrRecordItemPublishCommandServiceMock, times(1)).publishByMemberId(adrRecordItemPublishByMemberIdCommandCaptor.capture());
		
		ArgumentCaptor<GenericResponse> responseCaptor = ArgumentCaptor.forClass(GenericResponse.class);
        verify(responseObserver).onNext(responseCaptor.capture());
        
        assertEquals("success",responseCaptor.getValue().getMessage());

		
	}

}
