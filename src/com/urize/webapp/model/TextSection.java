package com.urize.webapp.model;

import java.util.Objects;

public class TextSection extends AbstractSection {
    private final String section;

    public TextSection(String section) {
        Objects.requireNonNull(section, "section must be not null");
        this.section = section;
    }

    public String getSection() {
        return section;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TextSection that = (TextSection) object;
        return Objects.equals(section, that.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(section);
    }

    @Override
    public String toString() {
        return "TextSection " + "section = " + section;
    }
}
