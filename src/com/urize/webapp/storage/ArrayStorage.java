package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    public void saveElement(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("Resume already exist");
        } else {
            storage[sizeStorage] = r;
            sizeStorage++;
        }
    }

    public int getIndex(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
