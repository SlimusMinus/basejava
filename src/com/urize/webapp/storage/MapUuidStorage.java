package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapUuidStorage extends AbstractStorage<String> {

    protected final Map<String, Resume> storage = new TreeMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExisting(String searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected void doDelete(String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected void doSave(String searchKey, Resume resume) {
        storage.put(searchKey, resume);
    }

    @Override
    protected Resume getKey(String searchKey, String uuid) {
        return storage.get(searchKey);
    }

    @Override
    protected void doUpdate(String searchKey, Resume resume) {
        storage.put(searchKey, resume);
    }


    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> goGetAll() {
        return new ArrayList<>(storage.values());    }

    @Override
    public int size() {
        return storage.size();
    }
}
