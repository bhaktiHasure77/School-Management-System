package com.schoolms.school.controller;

import javax.servlet.*;
import javax.servlet.http.*;

import com.schoolms.school.model.User;
import com.schoolms.school.service.UserService;

import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
 private UserService userService;
 
 @Override
 public void init() {
     userService = new UserService();
 }
 
 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
     
     String username = request.getParameter("username");
     String password = request.getParameter("password");
     
     User user = userService.loginUser(username, password);
     
     if (user != null) {
         HttpSession session = request.getSession();
         session.setAttribute("user", user);
         session.setAttribute("role", user.getRole());
         
         switch (user.getRole()) {
             case "admin":
                 response.sendRedirect("admin-dashboard.jsp");
                 break;
             case "teacher":
                 response.sendRedirect("teacher-dashboard.jsp");
                 break;
             case "student":
                 response.sendRedirect("student-dashboard.jsp");
                 break;
             case "staff":
                 response.sendRedirect("staff-dashboard.jsp");
                 break;
             default:
                 response.sendRedirect("login.jsp?error=Invalid role");
         }
     } else {
         response.sendRedirect("login.jsp?error=Invalid credentials");
     }
 }
}