package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int sizeStorage = 0;

    public void clear() {
        Arrays.fill(storage, 0, sizeStorage, null);
        sizeStorage = 0;
    }

    public int size() {
        return sizeStorage;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > 0) {
            return storage[index];
        } else {
            System.out.println("Resume with " + uuid + " not found");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume with " + uuid + " not found");
        } else {
            storage[index] = storage[sizeStorage - 1];
            storage[sizeStorage - 1] = null;
            sizeStorage--;
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Resume with " + resume.getUuid() + " not found");
        } else {
            storage[index] = resume;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, sizeStorage);
    }

    public abstract int getIndex(String uuid);
}
