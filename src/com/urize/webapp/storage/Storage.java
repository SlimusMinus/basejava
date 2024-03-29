package com.urize.webapp.storage;

import com.urize.webapp.model.Resume;

import java.util.List;

/**
 * Array based storage for Resumes
 */
public interface Storage {
    void clear();
    void save(Resume r);
    Resume get(String uuid);
    void delete(String uuid);
    List<Resume> getAllSorted();
    int size();
    void update(Resume resume);
}
