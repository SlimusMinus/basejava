package com.urize.webapp.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.urize.webapp.model.AbstractSection;

import java.io.Reader;
import java.io.Writer;
import java.time.YearMonth;

public class JsonParser {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(AbstractSection.class, new JsonSectionAdapter())
            .registerTypeAdapter(YearMonth.class, new YearMonthAdapter())
            .create();

    public static <T> T read(Reader reader, Class<T> clazz) {
        return GSON.fromJson(reader, clazz);
    }

    public static <T> void write(T object, Writer writer) {
        GSON.toJson(object, writer);
    }
}