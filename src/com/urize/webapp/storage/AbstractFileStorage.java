package com.urize.webapp.storage;

import com.urize.webapp.exception.StorageException;

import com.urize.webapp.model.Resume;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;
    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExisting(File file) {
        return file.exists();
    }

    @Override
    protected void doDelete(File file) {
        file.delete();
    }

    @Override
    protected void doSave(File file, Resume resume) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName());
        }
        doUpdate(file, resume);
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName());
        }
    }

    @Override
    protected void doUpdate(File file, Resume resume) {
        try {
            doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", resume.getUuid());
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        File[] files = getAllDirectoryFiles();

        return Arrays.stream(files).
                map(this::doGet).
                collect(Collectors.toList());
    }

    @Override
    public void clear() {
        File[] listFiles = getAllDirectoryFiles();
        for (File file : listFiles) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        return getAllDirectoryFiles().length;
    }

    private File[] getAllDirectoryFiles() {
        File[] list = directory.listFiles();
        if (list == null) {
            throw new StorageException("Directory is empty", null);
        }
        return list;
    }


}
