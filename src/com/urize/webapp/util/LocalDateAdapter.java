package com.urize.webapp.util;

import java.time.LocalDate;
import java.time.YearMonth;
import javax.xml.bind.annotation.adapters.XmlAdapter;
public class LocalDateAdapter extends XmlAdapter<String, YearMonth> {

    @Override
    public YearMonth unmarshal(String v) throws Exception {
        return YearMonth.parse(v);
    }

    @Override
    public String marshal(YearMonth v) throws Exception {
        return v.toString();
    }
}