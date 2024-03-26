import com.urize.webapp.model.Contacts;
import com.urize.webapp.model.Resume;
import com.urize.webapp.model.SectionType;

import java.util.HashMap;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Map<Contacts, String> contactsStringMap = new HashMap<>();
        contactsStringMap.put(Contacts.PHONE, "+79851373438");
        contactsStringMap.put(Contacts.SKYPE, "AlexKrylov");
        contactsStringMap.put(Contacts.MAIL, "abc@gmail.com");
        contactsStringMap.put(Contacts.PROFILE, "GitHub\nLinkedin\nStackOverflow");
        contactsStringMap.put(Contacts.HOMEPAGE, "www.slimusminus.com");

        Map<SectionType, String> sectionTypeStringMap = new HashMap<>();
        sectionTypeStringMap.put(SectionType.ACHIEVEMENT, "Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет\n" +
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.\n" +
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.\n" +
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.\n" +
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.\n" +
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).\n" +
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        sectionTypeStringMap.put(SectionType.EDUCATION, " Coursera\n" +
                "03/2013 - 05/2013\n" +
                "'Functional Programming Principles in Scala' by Martin Odersky\n" +
                "Luxoft\n" +
                "03/2011 - 04/2011\n" +
                "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'\n" +
                "Siemens AG\n" +
                "01/2005 - 04/2005\n" +
                "3 месяца обучения мобильным IN сетям (Берлин)\n" +
                "Alcatel\n" +
                "09/1997 - 03/1998\n" +
                "6 месяцев обучения цифровым телефонным сетям (Москва)\n" +
                "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики\n" +
                "09/1993 - 07/1996\n" +
                "Аспирантура (программист С, С++)\n" +
                "09/1987 - 07/1993\n" +
                "Инженер (программист Fortran, C)\n" +
                "Заочная физико-техническая школа при МФТИ\n" +
                "09/1984 - 06/1987\n" +
                "Закончил с отличием");
        sectionTypeStringMap.put(SectionType.OBJECTIVE, "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям\n");
        sectionTypeStringMap.put(SectionType.EXPERIENCE, "Java Online Projects\n" +
                "10/2013 - Сейчас\n" +
                "Автор проекта.\n" +
                "Создание, организация и проведение Java онлайн проектов и стажировок.\n" +
                "Wrike\n" +
                "10/2014 - 01/2016\n" +
                "Старший разработчик (backend)\n" +
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.\n" +
                "RIT Center\n" +
                "04/2012 - 10/2014\n" +
                "Java архитектор\n" +
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python\n" +
                "Luxoft (Deutsche Bank)\n" +
                "12/2010 - 04/2012\n" +
                "Ведущий программист\n" +
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.\n" +
                "Yota\n" +
                "06/2008 - 12/2010\n" +
                "Ведущий специалист\n" +
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)\n" +
                "Enkata\n" +
                "03/2007 - 06/2008\n" +
                "Разработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).\n" +
                "Siemens AG\n" +
                "01/2005 - 02/2007\n" +
                "Разработчик ПО\n" +
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).\n" +
                "Alcatel\n" +
                "09/1997 - 01/2005\n" +
                "Инженер по аппаратному и программному тестированию\n" +
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        sectionTypeStringMap.put(SectionType.PERSONAL, "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");


        Resume resume = new Resume("Alexandr Krylov", contactsStringMap, sectionTypeStringMap);
        System.out.println(resume.getFullName());
        for (var item : resume.getContacts().entrySet()){
            System.out.println(item.getKey().getTitle() + " " + item.getValue());
        }
        for (var item : resume.getSections().entrySet()){
            System.out.println(item.getKey().getTitle() + " " + item.getValue());
        }
    }
}
