package com.urize.webapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
@Getter
@NoArgsConstructor
public class TextSection extends AbstractSection implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String section;

    public static final TextSection EMPTY = new TextSection("");

    public TextSection(String section) {
        Objects.requireNonNull(section, "section must be not null");
        this.section = section;
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
        return section;
    }
}
