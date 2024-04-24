package com.urize.webapp.storage;

import com.urize.webapp.storage.serializable.XmlSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlSerializer()));
    }
}
