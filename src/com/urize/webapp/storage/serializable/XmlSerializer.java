
package com.urize.webapp.storage.serializable;

import com.urize.webapp.model.*;
import com.urize.webapp.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlSerializer implements SerializableInterface{
    private XmlParser xmlParser;

    public XmlSerializer() {
        xmlParser = new XmlParser(
                Resume.class, Company.class, Link.class,
                CompanySection.class, TextSection.class, ListSection.class, Company.Period.class);
    }
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(r, w);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(r);
        }
    }
}
