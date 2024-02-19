package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int sizeStorage = 0;

    public void clear() {
        Arrays.fill(storage, 0, sizeStorage, null);
        sizeStorage = 0;
    }

    public void save(Resume r) {
        if (sizeStorage > 9999)
            System.out.println("Storage is full");
        else if (storage[sizeStorage] != null && storage[sizeStorage].getUuid().equals(r.getUuid()))
            System.out.println("Storage have resume with " + r.getUuid() + " uuid");
        else if (sizeStorage == 9998)
            storage[sizeStorage] = r;
        else {
            storage[sizeStorage] = r;
            sizeStorage++;
        }
    }

    public Resume get(String uuid) {
        if (sizeStorage == 0) {
            System.out.println("Storage is empty, resume with " + uuid + " uuid isn't get");
            return null;
        } else {
            for (int i = 0; i < sizeStorage; i++) {
                if (storage[i].getUuid().equals(uuid))
                    return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (sizeStorage == 0) {
            System.out.println("Storage is empty, resume with " + uuid + " uuid isn't delete");
        } else {
            for (int i = 0; i < sizeStorage; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i] = storage[sizeStorage - 1];
                    storage[sizeStorage - 1] = null;
                    sizeStorage--;
                    break;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, sizeStorage);
    }

    public int size() {
        return sizeStorage;
    }

    public void update(Resume resume) {
        if (sizeStorage == 0) {
            System.out.println("Storage is empty, resume with " + resume.getUuid() + " uuid isn't update");
        } else {
            for (int i = 0; i < sizeStorage; i++) {
                if (storage[i].getUuid().equals(resume.getUuid()))
                    storage[i].setUuid("new uuid");
            }
        }
    }

}
