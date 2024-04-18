package com.urize.webapp.storage;


import com.urize.webapp.exception.ResumeExistStorageException;
import com.urize.webapp.exception.StorageNotFoundException;
import com.urize.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage <SK> implements Storage {
    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExisting(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract void doSave(SK searchKey, Resume resume);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doUpdate(SK searchKey, Resume resume);
    protected abstract List<Resume> doGetAll();


    public void save(Resume resume) {
        SK searchKey = getNotExistedResume(resume.getUuid());
        doSave(searchKey, resume);
    }


    public Resume get(String uuid) {
        SK searchKey = getExistedResume(uuid);
        return doGet(searchKey);
    }


    public void delete(String uuid) {
        SK searchKey = getExistedResume(uuid);
        doDelete(searchKey);
    }

    public List<Resume> getAllSorted(){
        List<Resume> list = doGetAll();
        Collections.sort(list);
        return list;
    }


    public void update(Resume resume) {
        SK searchKey = getExistedResume(resume.getUuid());
        doUpdate(searchKey, resume);
    }

    public SK getExistedResume(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExisting(searchKey)) {
            throw new StorageNotFoundException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedResume(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExisting(searchKey)) {
            throw new ResumeExistStorageException(uuid);
        }
        return searchKey;
    }

}
