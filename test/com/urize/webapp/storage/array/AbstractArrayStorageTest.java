package com.urize.webapp.storage.array;

import com.urize.webapp.exception.ResumeExistStorageException;
import com.urize.webapp.exception.StorageException;
import com.urize.webapp.exception.StorageNotFoundException;
import com.urize.webapp.model.Resume;
import com.urize.webapp.storage.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    final Storage storage;
    private final String UUID1 = "uuid1";
    private final String UUID2 = "uuid2";
    private final String UUID3 = "uuid3";
    private final String UUID_NOT_EXIST = "uuid8";
    private final Resume resume1 = new Resume(UUID1);
    private final Resume resume2 = new Resume(UUID2);
    private final Resume resume3 = new Resume(UUID3);
    private final Resume resume4 = new Resume(UUID_NOT_EXIST);
    private final Resume resume5 = new Resume(UUID1);
    private final Resume[] emptyArray = new Resume[0];
    private final Resume[] expected = new Resume[]{resume1, resume2, resume3};

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @AfterEach
    void clearStorage() {
        storage.clear();
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
        assertArrayEquals(emptyArray, storage.getAll());
    }

    @Test
    void save() {
        storage.save(resume4);
        assertGet(resume4);
        assertSize(4);
    }

    @Test
    void get() {
        assertAll(
                () -> assertGet(resume1),
                () -> assertGet(resume2),
                () -> assertGet(resume3)
        );
    }

    @Test
    void delete() {
        storage.delete(UUID2);
        assertSize(2);
        assertThrows(StorageNotFoundException.class, () -> storage.get(UUID2));
    }

    @Test
    void getAll() {
        assertArrayEquals(expected, storage.getAll());
    }

    @Test
    void size() {
        assertSize(3);
    }

    @Test
    void update() {
        storage.update(resume5);
        assertSame(resume5, storage.get(UUID1));
    }

    @Test
    public void saveExistingResume() {
        Assertions.assertThrows(ResumeExistStorageException.class, () -> storage.save(new Resume(UUID2)));
    }

    @Test
    public void storageOverflow() {
        storage.clear();
        for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume()));
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

    void assertSize(int size) {
        assertEquals(size, storage.size());
    }

}