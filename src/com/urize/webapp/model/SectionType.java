package com.urize.webapp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SectionType {
    private Map<SectionTypeEnum, String> stringMap = new HashMap<>();
    private Map<SectionTypeEnum, List<String>> enumListMap = new HashMap<>();

    public Map<SectionTypeEnum, String> getStringMap() {
        return stringMap;
    }

    public Map<SectionTypeEnum, List<String>> getEnumListMap() {
        return enumListMap;
    }

    public void fillStringMap(Map<SectionTypeEnum, String> stringMap){
        this.stringMap = stringMap;
    }

    public void fillEnumListMap(Map<SectionTypeEnum, List<String>> enumListMap){
        this.enumListMap = enumListMap;
    }
}
