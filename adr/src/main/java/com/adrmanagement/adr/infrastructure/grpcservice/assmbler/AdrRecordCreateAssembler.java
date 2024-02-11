package com.adrmanagement.adr.infrastructure.grpcservice.assmbler;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordCreateRequest;
import com.adrmanagement.common.adr.domain.commands.AdrRecordCreateCommand;
import com.adrmanagement.common.adr.domain.commands.AdrRecordUpdateCommand;
import com.adrmanagement.common.adr.domain.enums.AdrRecordProgressStatus;
import com.adrmanagement.common.util.ProtoBeanUtils;
import com.adrmanagement.common.util.SequenceGenerator;

public class AdrRecordCreateAssembler {
	
	public static AdrRecordCreateCommand requestToCommand(AdrRecordCreateRequest adrRecordCreateRequest) throws IOException {
		AdrRecordCreateCommand adrRecordCreateCommand = ProtoBeanUtils.toPojoBean(AdrRecordCreateCommand.class, adrRecordCreateRequest);
		LocalDateTime now = LocalDateTime.now();
		adrRecordCreateCommand.setProgressStatus(AdrRecordProgressStatus.getAdrRecordProgressStatus(adrRecordCreateRequest.getProgressStatus()));
		adrRecordCreateCommand.setAdrId(SequenceGenerator.genId());
		adrRecordCreateCommand.setCreateTime(now);
		adrRecordCreateCommand.setUpdateTime(now);
		return adrRecordCreateCommand;
	}

}
