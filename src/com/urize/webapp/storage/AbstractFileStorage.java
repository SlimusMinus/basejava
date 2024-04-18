package com.urize.webapp.storage;

import com.urize.webapp.exception.StorageException;
import com.urize.webapp.model.Resume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private static final Logger log = LoggerFactory.getLogger(AbstractArrayStorage.class);
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
        if (!file.delete()) {
            throw new StorageException("File isn't must be deleted", file.getName());
        } else {
            file.delete();
        }
    }

    @Override
    protected void doSave(File file, Resume resume) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName() + " " + e.getMessage());
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName());
        }
    }

    @Override
    protected void doUpdate(File file, Resume resume) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("File write error", file.getName());
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        File[] files = getAllFilesFromDirectory();

        return Arrays.stream(files).
                map(this::doGet).
                collect(Collectors.toList());
    }

    @Override
    public void clear() {
        File[] listFiles = getAllFilesFromDirectory();
        for (File file : listFiles) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        return getAllFilesFromDirectory().length;
    }

    private File[] getAllFilesFromDirectory() {
        File[] list = directory.listFiles();
        if (list == null) {
            throw new StorageException("Directory is empty", null);
        }
        return list;
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;
}
