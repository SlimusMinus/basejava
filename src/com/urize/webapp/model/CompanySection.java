package com.urize.webapp.model;

import java.util.List;

public class CompanySection extends SectionAbstract{
    private final String website;
    private final String nameCompany;
    private final List<Period> periodList;


    public CompanySection(String website, String nameCompany, List<Period> periodList) {
        this.website = website;
        this.nameCompany = nameCompany;
        this.periodList = periodList;
    }


    @Override
    public void getSections() {
        System.out.println(website + "\n" + nameCompany);
        for(var item : periodList){
            System.out.println(item);
        }
    }
}
