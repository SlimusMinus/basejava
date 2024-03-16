package com.urize.webapp.storage.array;

import com.urize.webapp.exception.ResumeExistStorageException;
import com.urize.webapp.exception.StorageException;
import com.urize.webapp.exception.StorageNotFoundException;
import com.urize.webapp.model.Resume;
import com.urize.webapp.storage.AbstractStorage;
import com.urize.webapp.storage.Storage;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int sizeStorage = 0;

    public abstract void saveElement(Resume resume);

    public abstract void deleteTemplateMethod(int index);

    public void clear() {
        Arrays.fill(storage, 0, sizeStorage, null);
        sizeStorage = 0;
    }

    @Override
    protected void saveKey(Object searchKey, Resume resume) {
        if (sizeStorage >= STORAGE_LIMIT) {
            throw new StorageException("Storage is full", resume.getUuid());
        } else {
            saveElement(resume);
            sizeStorage++;
        }
    }

    @Override
    protected Resume getKey(Object searchKey, String uuid){
        Object index = getSearchKey(uuid);
        return storage[(Integer) index];
    }

    @Override
    protected void deleteKey(Object searchKey) {
        deleteTemplateMethod((Integer) searchKey);
        storage[sizeStorage - 1] = null;
        sizeStorage--;
    }

    @Override
    protected void updateKey(Object searchKey, Resume resume){
        storage[(Integer) searchKey] = resume;
    }

    @Override
    protected boolean isExistResume(Object index) {
        return (Integer) index >= 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, sizeStorage);
    }

    public int size() {
        return sizeStorage;
    }

}
