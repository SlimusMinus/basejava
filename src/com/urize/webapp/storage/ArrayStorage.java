package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage {
    public static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int sizeStorage = 0;

    public void clear() {
        Arrays.fill(storage, 0, sizeStorage, null);
        sizeStorage = 0;
    }

    public void save(Resume r) {
        if (sizeStorage >= STORAGE_LIMIT) {
            System.out.println("Storage is full");
        } else if (getIndex(r.getUuid()) != -1) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else {
            storage[sizeStorage] = r;
            sizeStorage++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
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

    private int getIndex(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
