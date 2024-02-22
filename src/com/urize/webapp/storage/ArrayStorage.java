package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;
import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private static final int STORAGE_LIMIT = 10000;
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
        if (getIndex(uuid) != -1) {
            return storage[getIndex(uuid)];
        } else {
            System.out.println("Resume with " + uuid + " not found");
        }
        return null;
    }

    public void delete(String uuid) {
        if (getIndex(uuid) == -1) {
            System.out.println("Resume with " + uuid + " not found");
        } else {
            storage[getIndex(uuid)] = storage[sizeStorage - 1];
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
        if (getIndex(resume.getUuid()) == -1) {
            System.out.println("Resume with " + resume.getUuid() + " not found");
        } else {
            storage[getIndex(resume.getUuid())] = resume;
        }
    }

    public int getIndex(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
