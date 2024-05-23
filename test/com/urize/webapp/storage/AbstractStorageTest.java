package com.urize.webapp.storage;


import com.urize.webapp.exception.ResumeExistStorageException;
import com.urize.webapp.exception.StorageNotFoundException;
import com.urize.webapp.model.*;
import com.urize.webapp.sql.Config;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Month;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.getInstance().getStorageDir();

    final Storage storage;
    private final static String UUID1 = "uuid1";
    private final String UUID2 = "uuid2";
    private final String UUID3 = "uuid3";
    private final String UUID_NOT_EXIST = "uuid8";
    private final static Resume resume1 = new Resume(UUID1, "Garry");
    private final Resume resume3 = new Resume(UUID2, "Tom");
    private final Resume resume2 = new Resume(UUID3, "Tim");
    private final Resume resume4 = new Resume(UUID_NOT_EXIST, "Bill");
    private final List<Resume> emptyArray = new ArrayList<>();
    private final List<Resume> expected = Arrays.asList(resume1, resume2, resume3);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    static {
        Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
        contacts.put(ContactType.PHONE, "89874561252");
        contacts.put(ContactType.SKYPE, "skype");
        contacts.put(ContactType.MAIL, "123@gmail.com");
        contacts.put(ContactType.LINKEDIN, "LINKEDIN");
        contacts.put(ContactType.GITHUB, "GITHUB");
        contacts.put(ContactType.STACKOVERFLOW, "STACKOVERFLOW");
        contacts.put(ContactType.HOMEPAGE, "www.myPage.com");
        resume1.setContacts(contacts);

        resume1.setSection(SectionType.OBJECTIVE, new TextSection("position"));
        resume1.setSection(SectionType.PERSONAL, new TextSection("personal"));
        resume1.setSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList("One", "Two", "Three")));
        resume1.setSection(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList("Three", "Four", "Five")));
        resume1.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        resume1.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Organization.Position(2015, Month.JANUARY, "position1", "content1"))));
        resume1.setSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));
    }


    @BeforeEach
    public void setUp() {
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @AfterEach
    public void clearStorage() {
        storage.clear();
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        assertEquals(emptyArray, storage.getAllSorted());
    }

    @Test
    public void save() {
        storage.save(resume4);
        assertGet(resume4);
        assertSize(4);
    }

    @Test
    public void get() {
        assertAll(
                () -> assertGet(resume1),
                () -> assertGet(resume2),
                () -> assertGet(resume3)
        );
    }

    @Test
    public void delete() {
        storage.delete(UUID2);
        assertSize(2);
        assertThrows(StorageNotFoundException.class, () -> storage.get(UUID2));
    }

    @Test
    public void getAll() {
        assertEquals(expected, storage.getAllSorted());
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID2, "New Name");
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID2));
    }

    @Test
    public void saveExistingResume() {
        Assertions.assertThrows(ResumeExistStorageException.class, () -> storage.save(new Resume(UUID2, "Garry")));
    }


    @Test
    public void getNotFoundException() {
        Assertions.assertThrows(StorageNotFoundException.class, () -> storage.get(UUID_NOT_EXIST));
    }

    @Test
    public void deleteNotFoundException() {
        Assertions.assertThrows(StorageNotFoundException.class, () -> storage.delete(UUID_NOT_EXIST));
    }

    @Test
    public void updateNotFoundException() {
        Resume resume8 = new Resume(UUID_NOT_EXIST);
        Assertions.assertThrows(StorageNotFoundException.class, () -> storage.update(resume8));
    }


    public void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    public void assertSize(int size) {
        assertEquals(size, storage.size());
    }

}