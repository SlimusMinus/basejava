package com.urize.webapp.servlet;

import com.urize.webapp.model.Resume;
import com.urize.webapp.storage.SQLStorage;
import com.urize.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    Storage storage = new SQLStorage("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
            super.init();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/html; charset=UTF-8");

        List<Resume> list = storage.getAllSorted();

        request.setAttribute("listResume", list);
        request.getRequestDispatcher("allResumes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
