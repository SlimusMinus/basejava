package com.urize.webapp.model;

public enum ContactsType {
    PHONE("Телефон"),
    SKYPE("Skype"),
    MAIL("Почта"),
    PROFILE("Профиль"),
    HOMEPAGE("Домашняя страница");
    private final String title;

    public String getTitle() {
        return title;
    }

    ContactsType(String title) {
        this.title = title;
    }
}
