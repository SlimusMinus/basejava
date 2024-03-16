package com.urize.webapp.storage.array;

import com.urize.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void saveElement(Resume resume) {
        storage[sizeStorage] = resume;
    }

    @Override
    public void deleteTemplateMethod(int index) {
        storage[index] = storage[sizeStorage - 1];
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
