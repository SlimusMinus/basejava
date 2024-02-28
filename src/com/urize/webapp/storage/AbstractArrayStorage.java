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

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (sizeStorage >= STORAGE_LIMIT) {
            System.out.println("Storage is full");
        } else if (index >= 0) {
            System.out.println("Resume is exist in storage");
        } else {
            saveElement(resume);
            sizeStorage++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            resumeNotFound(uuid);
            return null;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            resumeNotFound(uuid);
        } else {
            deleteTemplateMethod(index);
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
            resumeNotFound(resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }
    private void resumeNotFound(String uuid) {
        System.out.println("Resume with " + uuid + " not found");
    }

    public abstract void saveElement(Resume resume);

    public abstract int getIndex(String uuid);

    public abstract void deleteTemplateMethod(int index);

}
