package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public int isExist(Resume resume) {
        return Arrays.binarySearch(storage, 0, sizeStorage, resume);
    }

    @Override
    public void saveElement(Resume resume) {
        int j = isExist(resume);
        j = -j - 1;
        System.arraycopy(storage, j, storage, j + 1, sizeStorage - j);
        storage[j] = resume;
        sizeStorage++;
    }

    @Override
    public int getIndex(String uuid) {
        Resume key = new Resume();
        key.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, sizeStorage, key);
    }

    public void deleteTemplateMethod(String uuid){
        for (int i = getIndex(uuid); i < sizeStorage; i++) {
            storage[i-1] = storage[i];
        }
        sizeStorage--;
    }

}