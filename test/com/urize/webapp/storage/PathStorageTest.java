package com.urize.webapp.storage;

public class PathStorageTest extends AbstractStorageTest{
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new SerializableMethods()));
    }
}
