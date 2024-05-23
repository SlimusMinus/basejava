package com.urize.webapp.storage;

import com.urize.webapp.sql.Config;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(Config.getInstance().getStorage());
    }
}
