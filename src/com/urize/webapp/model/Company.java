package com.urize.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Company {
    private final Link link;
    private final List<Period> periods = new ArrayList<>();

    public Company(String name, String url, Period... periods) {
        this.link = new Link(name, url);
        Arrays.asList(periods);
    }

    public Link getLink() {
        return link;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Company company = (Company) object;
        return Objects.equals(link, company.link) && Objects.equals(periods, company.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, periods);
    }

    @Override
    public String toString() {
        return "Company " +
                "link = " + link +
                ", periods=" + periods;
    }
}
