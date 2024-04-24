package com.urize.webapp.storage;

import com.urize.webapp.storage.serializable.ObjectSerializable;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectSerializable()));
    }
}