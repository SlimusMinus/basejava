package ru.javawebinar.basejava.util;

import com.urize.webapp.model.Resume;
import com.urize.webapp.model.Section;
import com.urize.webapp.model.TextSection;
import com.urize.webapp.util.JsonParser;
import org.junit.jupiter.api.Test;



public class JsonParserTest {
    @Test
    public void testResume() throws Exception {

    }

    @Test
    public void write() throws Exception {
        Section section1 = new TextSection("Objective1");
        String json = JsonParser.write(section1, Section.class);
        System.out.println(json);
        Section section2 = JsonParser.read(json, Section.class);
    }
}
