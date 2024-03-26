package com.urize.webapp.storage;

import com.urize.webapp.exception.ResumeExistStorageException;
import com.urize.webapp.exception.StorageNotFoundException;
import com.urize.webapp.model.Resume;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public abstract class AbstractStorageTest {

    final Storage storage;
    private final String UUID1 = "uuid1";
    private final String UUID2 = "uuid2";
    private final String UUID3 = "uuid3";
    private final String UUID_NOT_EXIST = "uuid8";
    private final Resume resume1 = new Resume(UUID2, "Garry");
    private final Resume resume3 = new Resume(UUID1, "Tom");
    private final Resume resume2 = new Resume(UUID3,"Tim");
    private final Resume resume4 = new Resume(UUID_NOT_EXIST, "Bill");
    private final Resume resume5 = new Resume(UUID1, "Tom");
    private final List<Resume> emptyArray = new ArrayList<>();
    private final  List<Resume> expected = Arrays.asList(resume1, resume2, resume3);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
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
        storage.update(resume5);
        assertSame(resume5, storage.get(UUID1));
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