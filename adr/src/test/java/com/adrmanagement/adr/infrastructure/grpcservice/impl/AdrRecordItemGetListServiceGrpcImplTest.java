package com.adrmanagement.adr.infrastructure.grpcservice.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.adrmanagement.adr.domain.model.aggregate.AdrRecordItemAggregate;
import com.adrmanagement.adr.domain.service.queryservice.AdrRecordItemQueryService;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordGetResponse;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemGetListRequest;
import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordItemGetListResponse;
import com.adrmanagement.common.adr.domain.query.AdrRecordItemGetListQuery;
import com.adrmanagement.common.util.SequenceGenerator;

import io.grpc.stub.StreamObserver;
@RunWith(MockitoJUnitRunner.class)
public class AdrRecordItemGetListServiceGrpcImplTest {
	
	@InjectMocks
	private AdrRecordItemGetListServiceGrpcImpl grpcService;

	@Mock
	private AdrRecordItemQueryService adrRecordItemQueryServiceMock;
	
	@Captor
    private ArgumentCaptor<AdrRecordItemGetListQuery> adrRecordItemGetListQueryCaptor;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void getAdrList() throws Exception {
		Long adrId = SequenceGenerator.genId();
		AdrRecordItemGetListRequest request = AdrRecordItemGetListRequest.newBuilder().setTeamId(1L).build();
		List<AdrRecordItemAggregate> adrRecordAggregateList = new ArrayList<AdrRecordItemAggregate>();
		AdrRecordItemAggregate adrRecordItemAggregate = new AdrRecordItemAggregate();
		adrRecordItemAggregate.setAdrId(adrId);
		adrRecordAggregateList.add(adrRecordItemAggregate);
		when(adrRecordItemQueryServiceMock.getAdrRecordList(any(AdrRecordItemGetListQuery.class))).thenReturn(adrRecordAggregateList);

		StreamObserver<AdrRecordItemGetListResponse> responseObserver = mock(StreamObserver.class);
		grpcService.getAdrList(request, responseObserver);
		
		verify(adrRecordItemQueryServiceMock, times(1)).getAdrRecordList(adrRecordItemGetListQueryCaptor.capture());
		
		ArgumentCaptor<AdrRecordItemGetListResponse> responseCaptor = ArgumentCaptor.forClass(AdrRecordItemGetListResponse.class);
        verify(responseObserver).onNext(responseCaptor.capture());
        
        assertEquals(1,responseCaptor.getValue().getAdrRecordItemGetResponseCount());
        assertEquals(adrId,responseCaptor.getValue().getAdrRecordItemGetResponse(0).getAdrId());
		
	}

}
