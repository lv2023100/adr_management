package com.adrmanagement.adr.infrastructure.grpcservice.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.adrmanagement.adr.domain.model.aggregate.AdrRecordAggregate;
import com.adrmanagement.adr.domain.service.commandservice.AdrRecordCreateCommandService;
import com.adrmanagement.adr.domain.service.queryservice.AdrRecordQueryService;
import com.adrmanagement.adr.domain.service.repository.AdrRecordItemRepository;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordCreateRequest;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordCreateServiceGrpc;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordGetRequest;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordGetResponse;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordGetServiceGrpc;
import com.adrmanagement.common.adr.domain.commands.AdrRecordCreateCommand;
import com.adrmanagement.common.adr.domain.query.AdrRecordGetQuery;
import com.adrmanagement.common.infrastructure.grpcservice.GenericResponseOuterClass.GenericResponse;
import com.adrmanagement.common.util.SequenceGenerator;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
@RunWith(MockitoJUnitRunner.class)
public class AdrRecordGetServiceGrpcImplTest {
	
	@InjectMocks
	private AdrRecordGetServiceGrpcImpl grpcService;

	@Mock
	private AdrRecordQueryService adrRecordQueryService;
	
	@Captor
    private ArgumentCaptor<AdrRecordGetQuery> adrRecordGetQueryCaptor;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	

	@Test
	public void getAdr() throws Exception {
		Long adrId = SequenceGenerator.genId();
		AdrRecordGetRequest request = AdrRecordGetRequest.newBuilder().setAdrId(adrId).build();
		AdrRecordAggregate adrRecordAggregate = new AdrRecordAggregate();
		adrRecordAggregate.setAdrId(adrId);
		when(adrRecordQueryService.getAdrRecord(any(AdrRecordGetQuery.class))).thenReturn(adrRecordAggregate);

		StreamObserver<AdrRecordGetResponse> responseObserver = mock(StreamObserver.class);
		grpcService.getAdr(request, responseObserver);
		
		verify(adrRecordQueryService, times(1)).getAdrRecord(adrRecordGetQueryCaptor.capture());
		
		ArgumentCaptor<AdrRecordGetResponse> responseCaptor = ArgumentCaptor.forClass(AdrRecordGetResponse.class);
        verify(responseObserver).onNext(responseCaptor.capture());
        
        assertEquals(adrId,responseCaptor.getValue().getAdrId());
		
	}

}
