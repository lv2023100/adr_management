package com.adrmanagement.common.adr.domain.enums;

import java.util.Arrays;
import java.util.List;

public enum AdrRecordProgressStatus {
	DRAFT("Draft"), ACCEPTED("Accepted"), COMPLETED("Completed"), DEPRECATED("Deprecated"), SUPERSEDED("Superseded"),
	REJECTED("Rejected");

	private AdrRecordProgressStatus(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static AdrRecordProgressStatus getAdrRecordProgressStatus(String name) {
		switch (name) {
		case "Draft":
			return AdrRecordProgressStatus.DRAFT;
		case "Accepted":
			return AdrRecordProgressStatus.ACCEPTED;
		case "Completed":
			return AdrRecordProgressStatus.COMPLETED;
		case "Deprecated":
			return AdrRecordProgressStatus.DEPRECATED;
		case "Superseded":
			return AdrRecordProgressStatus.SUPERSEDED;
		case "Rejected":
			return AdrRecordProgressStatus.REJECTED;
		}
		return null;
	}
}
