package com.urize.webapp.storage;

import com.urize.webapp.model.*;

import java.time.Month;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public Resume fillResume(String fullName) {
        //Создаем резюме

        //Запоняем контакты
        Map<ContactsType, String> contacts = new HashMap<>();
        contacts.put(ContactsType.SKYPE, "skype:grigory.kislin");
        contacts.put(ContactsType.MAIL, "gkislin@yandex.ru");
        contacts.put(ContactsType.GITHUB, "https://github.com");
        contacts.put(ContactsType.LINKEDIN, "LinkedIn");
        contacts.put(ContactsType.STACKOVERFLOW, "Stackoverflow");
        contacts.put(ContactsType.HOMEPAGE, "Домашняя страница");

        //ЗАПОЛНЯЕМ ВСЕ СЕКЦИИ
        Map<SectionType, AbstractSection> sections = new HashMap<>();

        //Заполняем TextSection
        AbstractSection textSection = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        sections.put(SectionType.OBJECTIVE, textSection);
        AbstractSection textSection2 = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры");
        sections.put(SectionType.PERSONAL, textSection2);

        //Заполняем ListSection
        AbstractSection listSection1 = new ListSection(Arrays.asList("Организация команды и успешная реализация Java проектов для сторонних заказчиков: " +
                        "приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, " +
                        "многомодульный Spring Boot + Vaadin проект для комплексных DIY смет",
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). " +
                        "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira," +
                        " Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления " +
                        "окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для " +
                        "алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). " +
                        "Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга " +
                        "системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));
        sections.put(SectionType.ACHIEVEMENT, listSection1);
        AbstractSection listSection2 = new ListSection(Arrays.asList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy",
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, " +
                        "SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix",
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBounce",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования",
                "Родной русский, английский \"upper intermediate\""));
        sections.put(SectionType.QUALIFICATIONS, listSection2);

        //Заполняем Company
        //1 компания
        Period period = new Period(YearMonth.of(2012, Month.APRIL), YearMonth.of(2014, Month.OCTOBER), "Java архитектор",
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), " +
                        "миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. " +
                        "Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). " +
                        "Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, " +
                        "Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        Company company1 = new Company("RIT Center", "www.RIT_Center.com", period);
        //2 компания
        Period period2 = new Period(YearMonth.of(2014, Month.OCTOBER), YearMonth.of(2016, Month.JANUARY), "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Company company2 = new Company("Wrike", "https://www.wrike.com/vaj/", period2);
        //3 компания
        Period period3 = new Period(YearMonth.of(2016, Month.JANUARY), YearMonth.now(), "Автор проекта.",
                "Создание, организация и проведение Java онлайн проектов и стажировок.");
        Company company3 = new Company("Java Online Projects", "https://javaops.ru/", period3);
        //Заполняем CompanySection
        CompanySection companySection = new CompanySection(List.of(company1, company2, company3));
        sections.put(SectionType.EXPERIENCE, companySection);

        //Заполянем Education
        Period periodEducation = new Period(YearMonth.of(1984, Month.SEPTEMBER), YearMonth.of(1984, Month.JUNE), "Закончил с отличием", "");
        Company companyEducation = new Company("Заочная физико-техническая школа при МФТИ", "https://mipt.ru/", periodEducation);

        Period periodEducation2 = new Period(YearMonth.of(1987, Month.SEPTEMBER), YearMonth.of(1993, Month.JULY), "Инженер", "(программист Fortran, C)");
        Period periodEducation22 = new Period(YearMonth.of(1993, Month.SEPTEMBER), YearMonth.of(1996, Month.JULY), "Аспирантура ", "(программист С, С++)");
        Company companyEducation2 = new Company("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "https://itmo.ru/", periodEducation2, periodEducation22);
            Period periodEducation3 = new Period(YearMonth.of(2013, Month.MARCH), YearMonth.of(2013, Month.MAY), "'Functional Programming Principles in Scala' by Martin Odersky", "");
        Company companyEducation3 = new Company("Coursera", "https://Coursera.ru/", periodEducation3);
        CompanySection companyEducations = new CompanySection(List.of(companyEducation, companyEducation2, companyEducation3));
        sections.put(SectionType.EDUCATION, companyEducations);

        return new Resume(fullName, contacts, sections);
    }

}
