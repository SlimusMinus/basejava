package com.urize.webapp.storage;

import com.urize.webapp.sql.Config;

public class SQLStorageTest extends AbstractStorageTest {
    public SQLStorageTest() {
        super(Config.getInstance().getStorage());
    }
}
