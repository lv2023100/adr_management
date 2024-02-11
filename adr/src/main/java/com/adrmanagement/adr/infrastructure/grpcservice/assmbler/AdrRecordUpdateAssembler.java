package com.adrmanagement.adr.infrastructure.grpcservice.assmbler;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.adrmanagement.adr.infrastructure.grpcservice.AdrRecordUpdateRequest;
import com.adrmanagement.common.adr.domain.commands.AdrRecordUpdateCommand;
import com.adrmanagement.common.adr.domain.enums.AdrRecordProgressStatus;
import com.adrmanagement.common.util.ProtoBeanUtils;

public class AdrRecordUpdateAssembler {
	
	public static AdrRecordUpdateCommand requestToCommand(AdrRecordUpdateRequest adrRecordUpdateRequest) throws IOException {
		AdrRecordUpdateCommand adrRecordUpdateCommand = ProtoBeanUtils.toPojoBean(AdrRecordUpdateCommand.class, adrRecordUpdateRequest);
		adrRecordUpdateCommand.setProgressStatus(AdrRecordProgressStatus.getAdrRecordProgressStatus(adrRecordUpdateRequest.getProgressStatus()));
		adrRecordUpdateCommand.setUpdateTime(LocalDateTime.now());
		return adrRecordUpdateCommand;
	}

}
