package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

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
