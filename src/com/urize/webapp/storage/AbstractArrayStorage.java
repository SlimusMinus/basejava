package com.urize.webapp.storage;

import com.urize.webapp.exception.StorageException;
import com.urize.webapp.model.Resume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Arrays;
import java.util.List;


public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    private static final Logger log = LoggerFactory.getLogger(AbstractArrayStorage.class);

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
    protected void doSave(Integer searchKey, Resume resume) {
        if (sizeStorage >= STORAGE_LIMIT) {
            log.info("Storage is full");
            throw new StorageException("Storage is full", resume.getUuid());
        } else {
            saveElement(resume);
            sizeStorage++;
        }
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    protected void doDelete(Integer searchKey) {
        deleteTemplateMethod(searchKey);
        storage[sizeStorage - 1] = null;
        sizeStorage--;
    }

    @Override
    protected void doUpdate(Integer searchKey, Resume resume) {
        storage[ searchKey] = resume;
    }

    @Override
    protected boolean isExisting(Integer index) {
        return index >= 0;
    }

    @Override
    protected List<Resume> doGetAll() {
        return Arrays.asList(Arrays.copyOf(storage, sizeStorage));
    }

    public int size() {
        return sizeStorage;
    }

}
