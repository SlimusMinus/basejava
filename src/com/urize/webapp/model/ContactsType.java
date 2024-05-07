package com.urize.webapp.model;

import lombok.Getter;

@Getter
public enum ContactsType {
    PHONE("Телефон"),
    SKYPE("Skype"),
    MAIL("Почта"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW ("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");
    private final String title;

    ContactsType(String title) {
        this.title = title;
    }


}
