package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private static final Logger log = LoggerFactory.getLogger(ListStorage.class);

    protected final List<Resume> storage = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExisting(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doDelete(Integer searchKey) {
        log.info("Resume with {} searchKey do delete", storage.get(searchKey));
        Resume resume = storage.get(searchKey);
        storage.remove(resume);
    }

    @Override
    protected void doSave(Integer searchKey, Resume resume) {
        log.info("Resume with {} searchKey add in List Storage", searchKey);
        storage.add(resume);
    }

    @Override
    protected Resume getKey(Integer searchKey, String uuid) {
        return storage.get(searchKey);
    }

    @Override
    protected void doUpdate(Integer searchKey, Resume resume) {
        storage.set(searchKey, resume);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> goGetAll() {
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
