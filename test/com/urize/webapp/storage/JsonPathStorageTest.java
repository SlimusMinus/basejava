package com.urize.webapp.storage;

import com.urize.webapp.storage.serializable.JsonSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {
    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonSerializer()));
    }
}
