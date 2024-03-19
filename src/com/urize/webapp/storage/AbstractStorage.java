package com.urize.webapp.storage;


import com.urize.webapp.exception.ResumeExistStorageException;
import com.urize.webapp.exception.StorageNotFoundException;
import com.urize.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExisting(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doSave(Object searchKey, Resume resume);

    protected abstract Resume getKey(Object searchKey, String uuid);

    protected abstract void doUpdate(Object searchKey, Resume resume);


    public void save(Resume resume) {
        Object searchKey = getNotExistedResume(resume.getUuid());
        doSave(searchKey, resume);
    }


    public Resume get(String uuid) {
        Object searchKey = getExistedResume(uuid);
        return getKey(searchKey, uuid);
    }


    public void delete(String uuid) {
        Object searchKey = getExistedResume(uuid);
        doDelete(searchKey);
    }

    public List<Resume> getAllSorted(){
        List<Resume> list = goGetAll();
        Collections.sort(list);
        return list;
    }

    protected abstract List<Resume> goGetAll();

    public void update(Resume resume) {
        Object searchKey = getExistedResume(resume.getUuid());
        doUpdate(searchKey, resume);
    }

    public Object getExistedResume(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExisting(searchKey)) {
            throw new StorageNotFoundException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedResume(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExisting(searchKey)) {
            throw new ResumeExistStorageException(uuid);
        }
        return searchKey;
    }



}
