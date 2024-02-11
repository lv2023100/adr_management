package com.adrmanagement.common.util;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LocalDateTimeTypeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime>  {
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Override
	public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		return LocalDateTime.parse(json.getAsString(), formatter);
	}

	@Override
	public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src.format(formatter));
	}

}
