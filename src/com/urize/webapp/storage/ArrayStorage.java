package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void saveElement(Resume resume) {
        storage[sizeStorage] = resume;
    }

    public int getIndex(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void deleteTemplateMethod(int index) {
        storage[index] = storage[sizeStorage - 1];
    }
}
