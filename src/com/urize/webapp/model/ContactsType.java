package com.urize.webapp.model;

public enum ContactsType {
    PHONE("Телефон"),
    SKYPE("Skype"),
    MAIL("Почта"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW ("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");
    private final String title;

    public String getTitle() {
        return title;
    }

    ContactsType(String title) {
        this.title = title;
    }
}
