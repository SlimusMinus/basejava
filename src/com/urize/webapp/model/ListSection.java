package com.urize.webapp.model;

import java.util.List;

public class ListSection extends SectionAbstract {
    List<String> section;

    public ListSection(List<String> section) {
        this.section = section;
    }

    @Override
    public void getSections() {
        for (String item : section) {
            System.out.println(item);
        }
    }
}
