package com.urize.webapp.storage.serializable;

import com.urize.webapp.exception.StorageException;
import com.urize.webapp.model.Resume;

import java.io.*;

public class ObjectSerializable implements SerializableInterface {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(r);
            oos.flush();
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}