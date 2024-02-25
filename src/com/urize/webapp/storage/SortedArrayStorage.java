package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void saveElement(Resume r) {
        int j = Arrays.binarySearch(storage, 0, sizeStorage, r);
        if (j > 0) {
            System.out.println("Resume already exist");
        } else {
            j = -j - 1;
            System.arraycopy(storage, j, storage, j + 1, sizeStorage - j);
            storage[j] = r;
            sizeStorage++;
        }
    }

    @Override
    public int getIndex(String uuid) {
        Resume key = new Resume();
        key.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, sizeStorage, key);
    }

}
