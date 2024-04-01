package com.urize.webapp.model;

public class TextSection extends SectionAbstract {
    String section;

    public TextSection(String section) {
        this.section = section;
    }

    @Override
    public void getSections() {
        System.out.println(section);
    }
}
