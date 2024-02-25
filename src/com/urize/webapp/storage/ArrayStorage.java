package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    public int isExist(Resume resume) {
        return getIndex(resume.getUuid());
    }

    public void saveElement(Resume resume) {
        storage[sizeStorage] = resume;
        sizeStorage++;

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
    public void deleteTemplateMethod(String uuid) {
        storage[getIndex(uuid)] = storage[sizeStorage - 1];
        storage[sizeStorage - 1] = null;
        sizeStorage--;
    }
}
