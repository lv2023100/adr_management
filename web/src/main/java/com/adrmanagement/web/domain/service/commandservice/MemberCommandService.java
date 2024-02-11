package com.adrmanagement.web.domain.service.commandservice;

import com.adrmanagement.common.infrastructure.grpcservice.GenericResponseOuterClass.GenericResponse;
import com.adrmanagement.web.infrastructure.controller.component.MemberForm;

public interface MemberCommandService {
	
	public GenericResponse update(MemberForm memberForm)throws Exception;
	public GenericResponse create(MemberForm memberForm) throws Exception;

}
