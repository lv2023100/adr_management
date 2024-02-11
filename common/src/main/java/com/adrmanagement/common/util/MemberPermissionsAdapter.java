package com.adrmanagement.common.util;

import java.lang.reflect.Type;

import com.adrmanagement.common.adr.domain.enums.AdrRecordProgressStatus;
import com.adrmanagement.common.member.domain.enums.MemberPermissions;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class MemberPermissionsAdapter implements JsonSerializer<MemberPermissions>, JsonDeserializer<MemberPermissions>{
	
	@Override
	public MemberPermissions deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		return MemberPermissions.valueOf(json.getAsString());
	}

	@Override
	public JsonElement serialize(MemberPermissions src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src.name());
	}

}
