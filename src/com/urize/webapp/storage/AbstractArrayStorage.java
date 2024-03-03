package com.urize.webapp.storage;

import com.urize.webapp.exception.ResumeExistStorageException;
import com.urize.webapp.exception.StorageException;
import com.urize.webapp.exception.StorageNotFoundException;
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
            throw new StorageException("Storage is full", resume.getUuid());
        } else if (index >= 0) {
            throw new ResumeExistStorageException(resume.getUuid());
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
            throw new StorageNotFoundException(uuid);
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new StorageNotFoundException(uuid);
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
        if (index < 0) {
            throw new StorageNotFoundException(resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }
    public abstract void saveElement(Resume resume);

    public abstract int getIndex(String uuid);

    public abstract void deleteTemplateMethod(int index);

}
