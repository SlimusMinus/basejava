package com.urize.webapp.model;

import java.time.LocalDate;

public class Period {
    LocalDate startDate;
    LocalDate endDate;
    String title;
    String description;

    public Period(LocalDate startDate, LocalDate endDate, String title, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return startDate + " - " + endDate + '\n' + title + '\n' + description + '\'';
    }
}
