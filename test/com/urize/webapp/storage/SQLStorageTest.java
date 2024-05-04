package com.urize.webapp.storage;

import com.urize.webapp.Config;

public class SQLStorageTest extends AbstractStorageTest {
    public SQLStorageTest() {
        super(Config.getInstance().getStorage());
    }
}
