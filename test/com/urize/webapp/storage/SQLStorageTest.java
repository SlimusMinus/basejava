package com.urize.webapp.storage;

import com.urize.webapp.storage.sql.Config;

public class SQLStorageTest extends AbstractStorageTest {
    public SQLStorageTest() {
        super(Config.getInstance().getStorage());
    }
}
