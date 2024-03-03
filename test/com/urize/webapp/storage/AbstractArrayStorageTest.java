package com.urize.webapp.storage;

import com.urize.webapp.exception.ResumeExistStorageException;
import com.urize.webapp.exception.StorageNotFoundException;
import com.urize.webapp.model.Resume;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractArrayStorageTest {
    Storage storage;
    private Resume resume1;
    private Resume resume2;
    private Resume resume3;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        resume1 = new Resume("uuid1");
        resume2 = new Resume("uuid2");
        resume3 = new Resume("uuid3");

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
        assertEquals(0, storage.size());
    }

    @Test
    void save() {
        storage.save(new Resume("uuid4"));
        assertEquals(4, storage.size());
    }

    @Test
    void get() {
        assertEquals(resume1, storage.get("uuid1"));
    }

    @Test
    void delete() {
        storage.delete("uuid2");
        assertEquals(2, storage.size());
    }

    @Test
    void getAll() {
        assertArrayEquals(new Resume[]{resume1, resume2, resume3}, storage.getAll());
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }

    @Test
    void update() {
        storage.update(resume1);
        assertEquals(resume1, resume1);
    }

    @Test
    public void saveExceptionNotExist() {
        String uuid = "uuid2";
        Exception exception = assertThrows(ResumeExistStorageException.class, () -> storage.save(new Resume(uuid)));
        assertEquals("Resume with " + uuid + " is exist in storage", exception.getMessage());
    }

    @Test
    public void getNotFoundException() {
        String uuid8 = "uuid8";
        Exception exception = assertThrows(StorageNotFoundException.class, () -> storage.get(uuid8));
        assertEquals("Resume with " + uuid8 + " not found in storage", exception.getMessage());
    }

    @Test
    public void deleteNotFoundException() {
        String uuid8 = "uuid8";
        Exception exception = assertThrows(StorageNotFoundException.class, () -> storage.delete(uuid8));
        assertEquals("Resume with " + uuid8 + " not found in storage", exception.getMessage());
    }

    @Test
    public void updateNotFoundException() {
        Resume resume8 = new Resume("uuid8");
        Exception exception = assertThrows(StorageNotFoundException.class, () -> storage.update(resume8));
        assertEquals("Resume with " + resume8.getUuid() + " not found in storage", exception.getMessage());
    }

}