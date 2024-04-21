
package com.urize.webapp.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Resume implements Comparable<Resume>, Serializable {

    private static final long serialVersionUID = 1L;
    private final String uuid;

    private final String fullName;

    private Map<ContactsType, String> contacts;

    private Map<SectionType, AbstractSection> sections;

    public Resume(String fullName, Map<ContactsType, String> contacts, Map<SectionType, AbstractSection> sections) {
        this.uuid = UUID.randomUUID().toString();
        this.fullName = fullName;
        this.contacts = contacts;
        this.sections = sections;
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

    public Map<ContactsType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public void setContacts(Map<ContactsType, String> contacts) {
        this.contacts = contacts;
    }

    public void setSections(Map<SectionType, AbstractSection> sections) {
        this.sections = sections;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContacts(ContactsType type) {
        return contacts.get(type);
    }

    public AbstractSection getSections(SectionType type) {
        return sections.get(type);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Resume resume = (Resume) object;
        return Objects.equals(uuid, resume.uuid) && Objects.equals(fullName, resume.fullName) && Objects.equals(contacts, resume.contacts) && Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contacts=" + contacts +
                ", sections=" + sections +
                '}';
    }

    @Override
    public int compareTo(Resume resume) {
        int compare = fullName.compareTo(resume.fullName);
        return compare != 0 ? compare : uuid.compareTo(resume.uuid);
    }
}
