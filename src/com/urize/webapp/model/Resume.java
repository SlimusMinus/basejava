package com.urize.webapp.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;

    private final String fullName;

    private Map<Contacts, String> contacts = new HashMap<>();

    public Map<SectionType, String> getSections() {
        return sections;
    }

    private Map<SectionType, String> sections = new HashMap<>();

    public Resume(String fullName, Map<Contacts, String> contacts,  Map<SectionType, String> sections) {
        uuid = UUID.randomUUID().toString();
        this.fullName = fullName;
        this.contacts = contacts;
        this.sections = sections;
    }

    public Map<Contacts, String> getContacts() {
        return contacts;
    }

    public String getFullName() {
        return fullName;
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);

    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Resume " +
                "uuid = " + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contacts=" + contacts;
    }

    @Override
    public int compareTo(Resume resume) {
        int compare = fullName.compareTo(resume.fullName);
        return compare != 0 ? compare : uuid.compareTo(resume.uuid);
    }
}
