import com.urize.webapp.model.Contacts;
import com.urize.webapp.model.Resume;
import com.urize.webapp.model.SectionType;
import com.urize.webapp.model.SectionTypeEnum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {

        //Заполняем список контактов
        Map<Contacts, String> contactsStringMap = new HashMap<>();
        contactsStringMap.put(Contacts.PHONE, "+79851373438");
        contactsStringMap.put(Contacts.SKYPE, "AlexKrylov");
        contactsStringMap.put(Contacts.MAIL, "abc@gmail.com");
        contactsStringMap.put(Contacts.PROFILE, "GitHub\nLinkedin\nStackOverflow");
        contactsStringMap.put(Contacts.HOMEPAGE, "www.slimusminus.com");

        //Заполняем секцию типами String
        SectionType sectionType = new SectionType();

        Map<SectionTypeEnum, String> stringMap = new HashMap<>();
        stringMap.put(SectionTypeEnum.OBJECTIVE, "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        stringMap.put(SectionTypeEnum.PERSONAL, "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры");

        //Заполянем секции типа List
        Map<SectionTypeEnum, List<String>> listMap = new HashMap<>();
        List<String> listACHIEVEMENT = Arrays.asList("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет," +
                        "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        listMap.put(SectionTypeEnum.ACHIEVEMENT, listACHIEVEMENT);

        List<String> listQUALIFICATIONS = Arrays.asList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        listMap.put(SectionTypeEnum.QUALIFICATIONS, listQUALIFICATIONS);

        List<String> listEXPERIENCE = Arrays.asList("Java Online Projects\n" + "10/2013 - Сейчас\n" + "Автор проекта.\n" + "Создание, организация и проведение Java онлайн проектов и стажировок.",
                "Wrike\n" + "10/2014 - 01/2016\n" + "Старший разработчик (backend)\n" + "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.",
                "RIT Center\n" + "04/2012 - 10/2014\n" + "Java архитектор\n" + "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        listMap.put(SectionTypeEnum.EXPERIENCE, listEXPERIENCE);

        List<String> listEDUCATION = Arrays.asList("Coursera\n" + "03/2013 - 05/2013\n" + "'Functional Programming Principles in Scala' by Martin Odersky",
                "Luxoft\n" + "03/2011 - 04/2011\n" + "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'",
                "Siemens AG\n" + "01/2005 - 04/2005\n" + "3 месяца обучения мобильным IN сетям (Берлин)");
        listMap.put(SectionTypeEnum.EDUCATION, listEDUCATION);

        sectionType.fillStringMap(stringMap);
        sectionType.fillEnumListMap(listMap);


        Resume resume = new Resume("Alexandr Krylov", contactsStringMap, sectionType);

        System.out.println(resume.getFullName());
        for (var item : resume.getContacts().entrySet()){
            System.out.println(item.getKey().getTitle() + " " + item.getValue());
        }
        for(var item : resume.getSectionType().getStringMap().entrySet()){
            System.out.println(item.getKey().getTitle() + " " + item.getValue());
        }

        for(var item : resume.getSectionType().getEnumListMap().entrySet()){
            System.out.println(item.getKey().getTitle() + " " + item.getValue());
        }

    }
}
