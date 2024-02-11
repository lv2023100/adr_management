package com.adrmanagement.common.enums;

import com.adrmanagement.common.infrastructure.grpcservice.GenericResponseOuterClass.GenericResponse;
import com.adrmanagement.member.infrastructure.grpcservice.MemberGetResponse.Builder;

public enum GenericResponseEnum {
	
	SUCCESS(0,"success"),ERROR(1,"error"),FAIL(2,"fail"),DUPLICATE_ACCOUNT(3,"Duplicate Account");
	
	private GenericResponseEnum(Integer status, String message) {
		this.status = status;
		this.message = message;
	}

	private Integer status;
	
	private String message;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public static GenericResponse error() {
		return GenericResponse.newBuilder()
		.setMessage(ERROR.getMessage())
		.setStatus(ERROR.getStatus()).build();
	}
	
	public static GenericResponse success() {
		return GenericResponse.newBuilder()
		.setMessage(SUCCESS.getMessage())
		.setStatus(SUCCESS.getStatus()).build();
	}
	
	public static GenericResponse fail() {
		return GenericResponse.newBuilder()
		.setMessage(FAIL.getMessage())
		.setStatus(FAIL.getStatus()).build();
	}
	
	public static GenericResponse duplicateAccount() {
		return GenericResponse.newBuilder()
		.setMessage(DUPLICATE_ACCOUNT.getMessage())
		.setStatus(DUPLICATE_ACCOUNT.getStatus()).build();
	}

}
