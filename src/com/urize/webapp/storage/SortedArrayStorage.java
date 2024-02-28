package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public boolean isExist(int index) {
        return index > 0;
    }

    @Override
    public void saveElement(Resume resume, int sizeStorage) {
        int insertionIndex = Arrays.binarySearch(storage, 0, sizeStorage, resume);
        insertionIndex = -insertionIndex - 1;
        System.arraycopy(storage, insertionIndex, storage, insertionIndex + 1, sizeStorage - insertionIndex);
        storage[insertionIndex] = resume;
    }

    @Override
    public int getIndex(String uuid) {
        Resume key = new Resume();
        key.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, sizeStorage, key);
    }

    public void deleteTemplateMethod(int index){
        int moveNum = sizeStorage - index - 1;
        if (moveNum > 0) {
            System.arraycopy(storage, index + 1, storage, index, moveNum);
        }
    }

}