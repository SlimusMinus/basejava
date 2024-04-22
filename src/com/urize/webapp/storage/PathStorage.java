package com.urize.webapp.storage;

import com.urize.webapp.exception.StorageException;
import com.urize.webapp.model.Resume;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final SerializableMethods storage;

    public PathStorage(String dir, SerializableMethods storage) {
        directory = Paths.get(dir);
        this.storage = storage;
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(directory.toUri()))) {
            if (directoryStream != null) {
                for (Path file : directoryStream) {
                    doDelete(file);
                }
            }
        } catch (IOException e) {
            throw new StorageException("Deleting file error", null);
        }
    }

    @Override
    public int size() {
        final long count;
        try {
            count = Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }
        return (int) count;
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void doUpdate(Path path, Resume r) {
        try {
            storage.doWrite(r, new BufferedOutputStream(new FileOutputStream(String.valueOf(path))));
        } catch (IOException e) {
            throw new StorageException("File write error", r.getUuid());
        }
    }

    @Override
    protected boolean isExisting(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void doSave(Path path, Resume r) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file in " + path.getRoot(), path.getFileName().toString());
        }
        doUpdate(path, r);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return storage.doRead(new BufferedInputStream(new FileInputStream(String.valueOf(path))));
        } catch (IOException e) {
            throw new StorageException("File read error", path.getFileName().toString());
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Exception deleting in ", path.toString());
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        List<Resume> list;
        try (Stream<Path> paths = Files.walk(directory)) {
            list = paths.map(this::doGet).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("Exception do get all files", null);
        }
        return list;
    }


}
