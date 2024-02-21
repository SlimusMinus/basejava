package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;

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
        if (sizeStorage > STORAGE_LIMIT - 1)
            System.out.println("Storage is full");
        else if (storage[sizeStorage] != null && storage[sizeStorage].getUuid().equals(r.getUuid()))
            System.out.println("Storage have resume with " + r.getUuid() + " uuid");
        else {
            storage[sizeStorage] = r;
            sizeStorage++;
        }
    }

    public Resume get(String uuid) {
        if (sizeStorage == 0)
            System.out.println("Storage is empty, resume with " + uuid + " uuid isn't get");
        else if (getIndex(uuid) != -1)
            return storage[getIndex(uuid)];
        return null;
    }

    public void delete(String uuid) {
        if (sizeStorage == 0)
            System.out.println("Storage is empty, resume with " + uuid + " uuid isn't delete");
        else {
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
        if (sizeStorage == 0)
            System.out.println("Storage is empty, resume with " + resume.getUuid() + " uuid isn't update");
         else
            storage[getIndex(resume.getUuid())].setUuid("new uuid");
    }

    public int getIndex(String uuid) {
        int index = -1;
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(uuid))
                index = i;
        }
        return index;
    }
}
