package com.urize.webapp.storage;

import com.urize.webapp.exception.ResumeExistStorageException;
import com.urize.webapp.exception.StorageNotFoundException;
import com.urize.webapp.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.YearMonth;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("D:\\Working\\Ultimate_Project\\basejava\\storage");

    final Storage storage;
    private final static String UUID1 = "uuid1";
    private final String UUID2 = "uuid2";
    private final String UUID3 = "uuid3";
    private final String UUID_NOT_EXIST = "uuid8";
    private final static Resume resume1 = new Resume(UUID1, "Garry");
    private final Resume resume3 = new Resume(UUID2, "Tom");
    private final Resume resume2 = new Resume(UUID3,"Tim");
    private final Resume resume4 = new Resume(UUID_NOT_EXIST, "Bill");
    private final Resume resume5 = new Resume(UUID1, "Tom");
    private final List<Resume> emptyArray = new ArrayList<>();
    private final  List<Resume> expected = Arrays.asList(resume1, resume2, resume3);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    static {
        Map<ContactsType, String> contacts = new HashMap<>();
        contacts.put(ContactsType.PHONE, "89874561252");
        contacts.put(ContactsType.SKYPE, "skype");
        contacts.put(ContactsType.MAIL, "123@gmail.com");
        contacts.put(ContactsType.STACKOVERFLOW, "STACKOVERFLOW");
        contacts.put(ContactsType.LINKEDIN, "LINKEDIN");
        contacts.put(ContactsType.GITHUB, "GITHUB");
        resume1.setContacts(contacts);

        Map<SectionType, AbstractSection> sections = new HashMap<>();
        sections.put(SectionType.OBJECTIVE, new TextSection("position"));
        sections.put(SectionType.PERSONAL, new TextSection("personal"));
        sections.put(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList("One", "Two")));
        sections.put(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList("Three", "Four")));

        sections.put(SectionType.EXPERIENCE,  new CompanySection(List.of(
                new Company("Company", "http://company.ru",
                        new Company.Period(YearMonth.of(2005, 1), YearMonth.of(2007, 2), "position1", "content1"),
                        new Company.Period(YearMonth.of(2007, 2), YearMonth.of(2009, 4), "position2", "content2")))));

        sections.put(SectionType.EDUCATION, new CompanySection(List.of(
                new Company("University", "http://university.ru",
                        new Company.Period(YearMonth.of(2002, 1), YearMonth.of(2003, 2), "student", "study"),
                        new Company.Period(YearMonth.of(2003, 2), YearMonth.of(2004, 4), "aspirant", "teacher")))));
        resume1.setSections(sections);
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
        Resume newResume = new Resume(UUID1, "New Name");
        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(UUID1)));
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