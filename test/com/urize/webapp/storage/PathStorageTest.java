package com.urize.webapp.storage;

import com.urize.webapp.storage.serializable.SerializableMethods;

public class PathStorageTest extends AbstractStorageTest{
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new SerializableMethods()));
    }
}
