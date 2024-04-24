package com.urize.webapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;
import java.util.Objects;
@Getter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanySection extends AbstractSection {
    private List<Company> list;

    public CompanySection(List<Company> list) {
        Objects.requireNonNull(list, "company must be not null");
        this.list = list;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CompanySection that = (CompanySection) object;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public String toString() {
        return "CompanySection " + "list = " + list;
    }
}
