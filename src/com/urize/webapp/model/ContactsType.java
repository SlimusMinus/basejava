package com.urize.webapp.model;

import lombok.Getter;

@Getter
public enum ContactsType {
    PHONE("Phone"),
    SKYPE("Skype") {
        @Override
        protected String toHtml0(String value) {
            return getTitle() + ": " + toLink("skype:" + value, value);
        }
    },
    MAIL("Mails") {
        @Override
        protected String toHtml0(String value) {
            return getTitle() + ": " + toLink("mailto:" + value, value);
        }
    },
    LINKEDIN("Profile LinkedIn"){
        @Override
        protected String toHtml0(String value) {
            return getTitle() + ": " + toLink("LinkedIn:" + value, value);
        }
    },
    GITHUB("Profile GitHub"){
        @Override
        protected String toHtml0(String value) {
            return getTitle() + ": " + toLink("GitHub:" + value, value);
        }
    },
    STACKOVERFLOW("Profile Stackoverflow"){
        @Override
        protected String toHtml0(String value) {
            return getTitle() + ": " + toLink("GitHub:" + value, value);
        }
    },
    HOMEPAGE("Home page"){
        @Override
        protected String toHtml0(String value) {
            return getTitle() + ": " + toLink("GitHub:" + value, value);
        }
    };
    private final String title;

    ContactsType(String title) {
        this.title = title;
    }

    protected String toHtml0(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }

    public static String toLink(String href, String title) {
        return "<a href='" + href + "'>" + title + "</a>";
    }

}
