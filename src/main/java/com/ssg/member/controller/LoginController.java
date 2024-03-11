package com.ssg.jdbcex.controller;

import com.ssg.jdbcex.dto.MemberDTO;
import com.ssg.jdbcex.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("login get...");
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("login post called...");
        String mid = request.getParameter("mid");
        String mpwd = request.getParameter("mpwd");

        try {
            MemberDTO memberDTO = MemberService.INSTANCE.login(mid, mpwd);
            HttpSession session = request.getSession();
            session.setAttribute("loginInfo", memberDTO);
            response.sendRedirect("/todo/list");
        } catch (Exception e) {
            response.sendRedirect("/login?result=error");
        }
    }
}
