package com.urize.webapp.storage.list;

import com.urize.webapp.model.Resume;
import com.urize.webapp.storage.AbstractStorage;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> listResume = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        Integer key = null;
        for (int i = 0; i < listResume.size(); i++) {
            if (listResume.get(i).getUuid().equals(uuid)){
                key = i;
                break;
            }
        }
        return key;
    }

    @Override
    protected boolean isExistResume(Object searchKey) {
        return listResume.size() < (Integer) searchKey;
    }

    @Override
    protected void deleteKey(Object searchKey) {
        Resume resume = listResume.get((Integer) searchKey);
        listResume.remove(resume);
    }

    @Override
    protected void saveKey(Object searchKey, Resume resume) {
        listResume.add(resume);
    }

    @Override
    protected Resume getKey(Object searchKey, String uuid) {
        return listResume.get((Integer) searchKey);
    }

    @Override
    protected void updateKey(Object searchKey, Resume resume) {
        listResume.add((Integer) searchKey, resume);
    }

    @Override
    public void clear() {
        listResume.clear();
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) listResume.toArray();
    }

    @Override
    public int size() {
        return listResume.size();
    }
}
