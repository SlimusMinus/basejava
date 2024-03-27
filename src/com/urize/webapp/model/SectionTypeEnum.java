package com.urize.webapp.model;

public enum SectionTypeEnum {
    PERSONAL("Личные качества"),
    OBJECTIVE("Позиция"),
    ACHIEVEMENT ("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private final String title;

    SectionTypeEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
