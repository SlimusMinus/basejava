import com.urize.webapp.model.*;
import com.urize.webapp.storage.AbstractFileStorage;
import com.urize.webapp.storage.ObjectStreamStorage;

import java.io.*;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws IOException {

       try( FileOutputStream fos = new FileOutputStream("D:\\Working\\Ultimate_Project\\basejava\\storage\\uuid1.bin"); ObjectOutputStream oos = new ObjectOutputStream(fos);
             FileInputStream fis = new FileInputStream("D:\\Working\\Ultimate_Project\\basejava\\storage\\uuid1.bin");  ObjectInputStream ois = new ObjectInputStream(fis);){

            oos.writeObject(resume1);
            System.out.println(ois.readObject().toString());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private final static Resume resume3 = new Resume("UUID2", "Tom");
    private final static Resume resume1 = new Resume("UUID1", "Garry");
    static {
        Map<ContactsType, String> contacts = new HashMap<>();
        contacts.put(ContactsType.PHONE, "89874561252");
        contacts.put(ContactsType.SKYPE, "skype");
        contacts.put(ContactsType.MAIL, "123@gmail.com");
        contacts.put(ContactsType.STACKOVERFLOW, "STACKOVERFLOW");
        contacts.put(ContactsType.LINKEDIN, "LINKEDIN");
        contacts.put(ContactsType.GITHUB, "GITHUB");
        resume1.setContacts(contacts);

        Map<SectionType, AbstractSection> sections = new HashMap<>();
        sections.put(SectionType.OBJECTIVE, new TextSection("position"));
        sections.put(SectionType.PERSONAL, new TextSection("personal"));
        sections.put(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList("One", "Two")));
        sections.put(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList("Three", "Four")));

        sections.put(SectionType.EXPERIENCE,  new CompanySection(List.of(
                new Company("Company", "http://company.ru",
                        new Company.Period(YearMonth.of(2005, 1), YearMonth.of(2007, 2), "position1", "content1"),
                        new Company.Period(YearMonth.of(2007, 2), YearMonth.of(2009, 4), "position2", "content2")))));

        sections.put(SectionType.EDUCATION, new CompanySection(List.of(
                new Company("University", "http://university.ru",
                        new Company.Period(YearMonth.of(2002, 1), YearMonth.of(2003, 2), "student", "study"),
                        new Company.Period(YearMonth.of(2003, 2), YearMonth.of(2004, 4), "aspirant", "teacher")))));
        resume1.setSections(sections);
    }
}
