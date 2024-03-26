package com.urize.webapp.model;

public enum Contacts {
    PHONE("Телефон"),
    SKYPE("Skype"),
    MAIL("Почта"),
    PROFILE("Профиль"),
    HOMEPAGE("Домашняя страница");
    private String title;

    public String getTitle() {
        return title;
    }

    Contacts(String title) {
        this.title = title;
    }
}
