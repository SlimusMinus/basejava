package com.urize.webapp.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.YearMonth;

public class YearMonthAdapter extends TypeAdapter<YearMonth> {
    @Override
    public void write(JsonWriter out, YearMonth value) throws IOException {
        out.value(value.toString());
    }

    @Override
    public YearMonth read(JsonReader in) throws IOException {
        String yearMonthString = in.nextString();
        return YearMonth.parse(yearMonthString);
    }
}