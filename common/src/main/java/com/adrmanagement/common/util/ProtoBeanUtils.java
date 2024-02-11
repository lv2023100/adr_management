package com.adrmanagement.common.util;

import java.io.IOException;
import java.time.LocalDateTime;

import com.adrmanagement.common.adr.domain.enums.AdrRecordProgressStatus;
import com.adrmanagement.common.member.domain.enums.MemberPermissions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

public class ProtoBeanUtils {

    public static <PojoType> PojoType toPojoBean(Class<PojoType> destPojoClass, Message sourceMessage)
        throws IOException {
        if (destPojoClass == null) {
            throw new IllegalArgumentException
                ("No destination pojo class specified");
        }
        if (sourceMessage == null) {
            throw new IllegalArgumentException("No source message specified");
        }
        String json = JsonFormat.printer().print(sourceMessage);
        return new GsonBuilder()
        		.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
        		.registerTypeAdapter(AdrRecordProgressStatus.class, new AdrRecordProgressStatusAdapter())
        		.registerTypeAdapter(MemberPermissions.class, new MemberPermissionsAdapter())
        		.create().fromJson(json, destPojoClass);
    }

    public static void toProtoBean(Message.Builder destBuilder, Object sourcePojoBean) throws IOException {
        if (destBuilder == null) {
            throw new IllegalArgumentException
                ("No destination message builder specified");
        }
        if (sourcePojoBean == null) {
            throw new IllegalArgumentException("No source pojo specified");
        }
        String json = new GsonBuilder()
        		.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
        		.registerTypeAdapter(AdrRecordProgressStatus.class, new AdrRecordProgressStatusAdapter())
        		.registerTypeAdapter(MemberPermissions.class, new MemberPermissionsAdapter())
        		.create()
                .toJson(sourcePojoBean);
        JsonFormat.parser().ignoringUnknownFields().merge(json, destBuilder);

    }
}
