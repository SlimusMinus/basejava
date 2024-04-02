package com.urize.webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {
    private final String website;
    private final String name;
    private final List<Period> periods;


    public Company(String website, String name, List<Period> periodList) {
        this.website = website;
        this.name = name;
        this.periods = periodList;
    }

    public String getWebsite() {
        return website;
    }

    public String getName() {
        return name;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public String toString() {
        return "Company " + name + "website = " + website + ", periods = " + periods;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Company company = (Company) object;
        return Objects.equals(website, company.website) && Objects.equals(name, company.name) && Objects.equals(periods, company.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(website, name, periods);
    }
}
