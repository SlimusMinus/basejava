package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int sizeStorage = 0;
    private int index = 0;

    public void clear() {
        Arrays.fill(storage, 0, sizeStorage, null);
        sizeStorage = 0;
    }

    public void save(Resume resume) {
        if (sizeStorage >= STORAGE_LIMIT) {
            System.out.println("Storage is full");
        } else if (isExist(resume) >= 0) {
            System.out.println("Resume is exist in storage");
        } else {
            saveElement(resume);
        }
    }

    public Resume get(String uuid) {
        index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            resumeNotFound(uuid);
            return null;
        }
    }

    public void delete(String uuid) {
        index = getIndex(uuid);
        if (index == -1) {
            resumeNotFound(uuid);
        } else {
            deleteTemplateMethod(uuid);
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, sizeStorage);
    }

    public int size() {
        return sizeStorage;
    }

    public void update(Resume resume) {
        index = getIndex(resume.getUuid());
        if (index == -1) {
            resumeNotFound(resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    private void resumeNotFound(String uuid) {
        System.out.println("Resume with " + uuid + " not found");
    }

    public abstract int isExist(Resume resume);

    public abstract void saveElement(Resume resume);

    public abstract int getIndex(String uuid);
    public abstract void deleteTemplateMethod(String uuid);

}
