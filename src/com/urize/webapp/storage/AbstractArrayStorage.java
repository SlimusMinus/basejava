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

    public void save(Resume r) {
        if (sizeStorage >= STORAGE_LIMIT) {
            System.out.println("Storage is full");
        } else {
            saveElement(r);
        }
    }

    public abstract void saveElement(Resume r);

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > 0) {
            return storage[index];
        } else {
            System.out.println("Resume with " + uuid + " not found");
            return null;
        }
    }

    public abstract int getIndex(String uuid);

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

    public Resume[] getAll() {
        return Arrays.copyOf(storage, sizeStorage);
    }

    public int size() {
        return sizeStorage;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Resume with " + resume.getUuid() + " not found");
        } else {
            storage[index] = resume;
        }
    }


}
