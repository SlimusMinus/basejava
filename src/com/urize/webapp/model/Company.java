package com.urize.webapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@Getter
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    private Link link;
    private final List<Period> periods = new ArrayList<>();

    public Company(String name, String url, Period... periods) {
        this.link = new Link(name, url);
        Arrays.asList(periods);
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
    @Getter
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Serializable {
        private YearMonth startDate;
        private YearMonth endDate;
        private String title;
        private String description;

        public Period(YearMonth startDate, YearMonth endDate, String title, String description) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
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
