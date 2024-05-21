package com.urize.webapp.servlet;

import com.urize.webapp.model.*;
import com.urize.webapp.sql.Config;
import com.urize.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
            addSections(request, r);
            storage.update(r);
        }
        response.sendRedirect("resumes");
    }

    private void addSections(HttpServletRequest request, Resume r) {
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            if (value == null || value.trim().isEmpty()) {
                continue; // Skip empty sections
            }

            switch (type) {
                case OBJECTIVE:
                case PERSONAL:
                    r.addSections(type, new TextSection(value));
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    List<String> items = Arrays.asList(value.split("\\r?\\n")); // Split value into a list by new lines
                    r.addSections(type, new ListSection(items));
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