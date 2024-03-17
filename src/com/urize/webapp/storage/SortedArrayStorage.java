package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private final static Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName);

    @Override
    public void saveElement(Resume resume) {
        int insertionIndex = Arrays.binarySearch(storage, 0, sizeStorage, resume);
        insertionIndex = -insertionIndex - 1;
        System.arraycopy(storage, insertionIndex, storage, insertionIndex + 1, sizeStorage - insertionIndex);
        storage[insertionIndex] = resume;
    }

    public void deleteTemplateMethod(int index) {
        int moveNum = sizeStorage - index - 1;
        if (moveNum > 0) {
            System.arraycopy(storage, index + 1, storage, index, moveNum);
        }
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume key = new Resume(uuid, "");
        return Arrays.binarySearch(storage, 0, sizeStorage, key, RESUME_COMPARATOR);
    }

}