package com.urize.webapp.storage;

import com.urize.webapp.exception.ResumeExistStorageException;
import com.urize.webapp.exception.StorageException;
import com.urize.webapp.exception.StorageNotFoundException;
import com.urize.webapp.model.Resume;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    final Storage storage;
    private final String uuidForDelete = "uuid2";
    private final String uuidForNotFoundException = "uuid8";
    private final Resume resume1 = new Resume("uuid1");
    private final Resume resume2 = new Resume("uuid2");
    private final Resume resume3 = new Resume("uuid3");
    private final Resume resume4 = new Resume(uuidForNotFoundException);
    private final Resume resume5 = new Resume("uuid1");
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
        assertArrayEquals(storage.getAll(), emptyArray);
    }

    @Test
    void save() {
        storage.save(resume4);
        assertGet(resume4);
        assertSize(4);
    }

    @Test
    void get() {
        assertGet(resume1);
    }

    @Test
    void delete() {
        storage.delete(uuidForDelete);
        assertSize(2);
        assertThrows(StorageNotFoundException.class, ()->storage.get(uuidForDelete));
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
        assertEquals(
                resume5,
                Arrays.stream(storage.getAll()).filter(x->x==resume5).findFirst().get()
        );
    }

    @Test
    public void saveExceptionNotExist() {
        Assertions.assertThrows(ResumeExistStorageException.class, () -> storage.save(new Resume(uuidForDelete)));
    }

    @Test
    public void storageOverflow() {
        for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume()));
    }

    @Test
    public void getNotFoundException() {
        Assertions.assertThrows(StorageNotFoundException.class, () -> storage.get(uuidForNotFoundException));
    }

    @Test
    public void deleteNotFoundException() {
        Assertions.assertThrows(StorageNotFoundException.class, () -> storage.delete(uuidForNotFoundException));
    }

    @Test
    public void updateNotFoundException() {
        Resume resume8 = new Resume(uuidForNotFoundException);
        Assertions.assertThrows(StorageNotFoundException.class, () -> storage.update(resume8));
    }

    public void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    void assertSize(int size) {
        assertEquals(size, storage.size());
    }

}