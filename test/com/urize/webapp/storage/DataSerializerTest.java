package com.urize.webapp.storage;

import com.urize.webapp.storage.serializable.DataSerializer;

public class DataSerializerTest extends AbstractStorageTest{
    public DataSerializerTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataSerializer()));
    }
}
