import com.urize.webapp.model.*;

import java.time.LocalDate;
import java.util.*;

public class ResumeTestData {

    public static void main(String[] args) {
        Map<ContactsType, String> contacts = new HashMap<>();
        contacts.put(ContactsType.SKYPE, "skype:grigory.kislin");
        contacts.put(ContactsType.MAIL, "gkislin@yandex.ru");
        contacts.put(ContactsType.PROFILE, "LinkedIn, GitHub, Stackoverflow");
        contacts.put(ContactsType.HOMEPAGE, "Домашняя страница");

        //ЗАПОЛНЯЕМ ВСЕ СЕКЦИИ
        Map<SectionType, SectionAbstract> sections = new HashMap<>();
        //Заполняем TextSection
        SectionAbstract textSection = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        sections.put(SectionType.OBJECTIVE, textSection);
        SectionAbstract textSection2 = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры");
        sections.put(SectionType.PERSONAL, textSection2);

        //Заполняем ListSection
        SectionAbstract listSection1 = new ListSection(Arrays.asList("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет",
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));
        sections.put(SectionType.ACHIEVEMENT, listSection1);
        SectionAbstract listSection2 = new ListSection(Arrays.asList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy",
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix",
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBounce",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования",
                "Родной русский, английский \"upper intermediate\""));
        sections.put(SectionType.ACHIEVEMENT, listSection2);

        //Заполняем CompanySection
        Period period = new Period(LocalDate.of(1997,9,1), LocalDate.of(2005,1,5), "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)");
        List<Period> periodList = Arrays.asList(period);
        CompanySection companySection = new CompanySection("www.Alcatel.com", "Alcatel", periodList);
        sections.put(SectionType.EXPERIENCE, companySection);

        Period period2 = new Period(LocalDate.of(2013,10,1), LocalDate.now(), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        List<Period> periodList2 = Arrays.asList(period2);
        CompanySection companySection2 = new CompanySection("https://javaops.ru/", "Java Online Projects", periodList2);
        sections.put(SectionType.EXPERIENCE, companySection2);

        //Заполняем резюме
        Resume resume = new Resume("Григорий Кислин", contacts, sections);
        System.out.println(resume.getFullName());
        for(var item : resume.getContacts().entrySet()){
            System.out.println(item.getKey().getTitle() + " " + item.getValue());
        }
        for(var item : resume.getSections().entrySet()){
            System.out.println(item.getKey().getTitle() + "\n");
            item.getValue().getSections();
            System.out.println();
        }
    }

}
