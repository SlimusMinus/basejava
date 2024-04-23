package com.urize.webapp.storage.serializable;

import com.urize.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SerializableInterface {
    public void doWrite(Resume r, OutputStream os) throws IOException;
    public Resume doRead(InputStream is) throws IOException;
}
