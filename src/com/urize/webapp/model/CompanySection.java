package com.urize.webapp.model;

import java.util.List;
import java.util.Objects;

public class CompanySection extends AbstractSection {
    List<Company> list;

    public CompanySection(List<Company> list) {
        this.list = list;
    }

    public List<Company> getList() {
        return list;
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
