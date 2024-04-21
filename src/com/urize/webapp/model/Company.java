package com.urize.webapp.model;

import java.time.YearMonth;
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

    public static class Period {
        private final YearMonth startDate;
        private final YearMonth endDate;
        private final String title;
        private final String description;

        public Period(YearMonth startDate, YearMonth endDate, String title, String description) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }

        public YearMonth getStartDate() {
            return startDate;
        }

        public YearMonth getEndDate() {
            return endDate;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Period period = (Period) object;
            return Objects.equals(startDate, period.startDate) && Objects.equals(endDate, period.endDate) && Objects.equals(title, period.title) && Objects.equals(description, period.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, title, description);
        }

        @Override
        public String toString() {
            return startDate + " - " + endDate + '\t' + title + " (" + description + ')';
        }
    }
}
