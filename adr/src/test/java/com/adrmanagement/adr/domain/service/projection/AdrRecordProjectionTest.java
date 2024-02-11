package com.adrmanagement.adr.domain.service.projection;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.adrmanagement.adr.domain.model.aggregate.AdrRecordAggregate;
import com.adrmanagement.adr.domain.model.entity.AdrRecord;
import com.adrmanagement.adr.domain.service.repository.AdrRecordRepository;
import com.adrmanagement.common.adr.domain.query.AdrRecordGetQuery;
import com.adrmanagement.common.util.SequenceGenerator;

public class AdrRecordProjectionTest {
	
	@InjectMocks
    private AdrRecordProjection queryHandler;
	
	@Mock
	private AdrRecordRepository adrRecordRepositoryMock;
	
	@BeforeEach
    public void setUp() {
		MockitoAnnotations.openMocks(this);
    }
	
	@Mock
    private QueryGateway queryGateway;

    @InjectMocks
    private AdrRecordProjection adrRecordQueryHandler;

    @Test
    void testAdrRecordGetQueryHandler() throws Exception {
    	Long adrId = SequenceGenerator.genId();

        AdrRecordGetQuery adrRecordGetQuery = new AdrRecordGetQuery();
        adrRecordGetQuery.setAdrId(adrId);
        
        AdrRecord adrRecord = new AdrRecord();
		adrRecord.setAdrId(adrId);
		when(adrRecordRepositoryMock.findById(adrId)).thenReturn(adrRecord);


        AdrRecordAggregate mockResult = new AdrRecordAggregate(/* provide necessary data */);
        when(queryGateway.query(adrRecordGetQuery, ResponseTypes.instanceOf(AdrRecordAggregate.class))).thenReturn(CompletableFuture.completedFuture(mockResult));
        

        AdrRecordAggregate adrRecordAggregate = adrRecordQueryHandler.adrRecordGet(adrRecordGetQuery);

        assertNotNull(adrRecordAggregate);

    }

}
