package com.adrmanagement.common.util;

import java.lang.reflect.Type;

import com.adrmanagement.common.adr.domain.enums.AdrRecordProgressStatus;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class AdrRecordProgressStatusAdapter implements JsonSerializer<AdrRecordProgressStatus>, JsonDeserializer<AdrRecordProgressStatus>{

	@Override
	public AdrRecordProgressStatus deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		return AdrRecordProgressStatus.getAdrRecordProgressStatus(json.getAsString());
	}

	@Override
	public JsonElement serialize(AdrRecordProgressStatus src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src.getName());
	}

}
