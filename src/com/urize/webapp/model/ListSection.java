
package com.urize.webapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@Getter
@NoArgsConstructor

public class ListSection extends AbstractSection {
    List<String> list;
    public static final ListSection EMPTY = new ListSection("");

    public ListSection(List<String> list) {
        Objects.requireNonNull(list, "list must not be a null");
        this.list = list;
    }

    public ListSection(String... items) {
        this(Arrays.asList(items));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ListSection that = (ListSection) object;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public String toString() {
        return "ListSection " +
                "list = " + list;
    }
}
