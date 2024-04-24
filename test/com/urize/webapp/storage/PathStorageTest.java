package com.urize.webapp.storage;

import com.urize.webapp.storage.serializable.ObjectSerializable;

public class PathStorageTest extends AbstractStorageTest{
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new ObjectSerializable()));
    }
}
