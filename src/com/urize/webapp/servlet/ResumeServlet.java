package com.urize.webapp.servlet;

import com.urize.webapp.model.*;
import com.urize.webapp.sql.Config;
import com.urize.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.YearMonth;
import java.util.*;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init() throws ServletException {
        super.init();
        storage = Config.getInstance().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("listResume", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/allResumes.jsp").forward(request, response);
            return;
        }

        Resume resume;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resumes");
                return;
            case "view":
            case "edit":
                resume = storage.get(uuid);
                for (SectionType type : SectionType.values()) {
                    AbstractSection section = resume.getSection(type);
                    switch (type) {
                        case OBJECTIVE:
                        case PERSONAL:
                            if (section == null) {
                                section = TextSection.EMPTY;
                            }
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            if (section == null) {
                                section = ListSection.EMPTY;
                            }
                            break;
                        case EXPERIENCE:
                        case EDUCATION:
                            CompanySection orgSection = (CompanySection) section;
                            List<Company> emptyFirstOrganizations = new ArrayList<>();
                            emptyFirstOrganizations.add(Company.EMPTY);
                            if (orgSection != null) {
                                for (Company org : orgSection.getList()) {
                                    List<Company.Period> emptyFirstPositions = new ArrayList<>();
                                    emptyFirstPositions.add(Company.Period.EMPTY);
                                    emptyFirstPositions.addAll(org.getPeriods());
                                    emptyFirstOrganizations.add(new Company(org.getLink(), emptyFirstPositions));
                                }
                            }
                            section = new CompanySection(emptyFirstOrganizations);
                            break;
                    }
                    resume.addSections(type, section);
                }
                break;
            case "add":
                resume = Resume.EMPTY;
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " illegal action");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(action.equals("view") ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume r;
        if (uuid == null || uuid.isEmpty()) {
            r = new Resume(fullName);
            addContacts(request, r);
            addSections(request, r);
            storage.save(r);
        } else {
            r = storage.get(uuid);
            r.setFullName(fullName);
            addContacts(request, r);
            storage.update(r);
        }
        response.sendRedirect("resumes");
    }

    private void addSections(HttpServletRequest request, Resume r) {
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());

            switch (type) {
                case OBJECTIVE:
                case PERSONAL:
                    r.addSections(type, new TextSection(value));
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    r.addSections(type, new ListSection(value));
                    break;
                case EDUCATION:
                case EXPERIENCE:
                    List<Company> companies = new ArrayList<>();
                    String[] urls = request.getParameterValues(type.name() + "url");
                    for (int i = 0; i < values.length; i++) {
                        String name = values[i];
                        List<Company.Period> positions = new ArrayList<>();
                        String pfx = type.name() + i;
                        String[] startDates = request.getParameterValues(pfx + "startDate");
                        String[] endDates = request.getParameterValues(pfx + "endDate");
                        String[] titles = request.getParameterValues(pfx + "title");
                        String[] descriptions = request.getParameterValues(pfx + "description");
                        for (int j = 0; j < titles.length; j++) {
                            positions.add(new Company.Period(YearMonth.parse(startDates[j]), YearMonth.parse(endDates[j]), titles[j], descriptions[j]));

                        }
                        companies.add(new Company(new Link(name, urls[i]), positions));
                    }
                    r.addSections(type, new CompanySection(companies));
                    break;
            }
        }
    }

    private static void addContacts(HttpServletRequest request, Resume r) {
        for (ContactsType type : ContactsType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && !value.trim().isEmpty()) {
                r.addContacts(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }
    }

}
