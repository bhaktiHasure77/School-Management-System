package com.schoolms.school.controller;

import javax.servlet.*;
import javax.servlet.http.*;

import com.schoolms.school.model.User;
import com.schoolms.school.service.UserService;

import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
 private UserService userService;
 
 @Override
 public void init() {
     userService = new UserService();
 }
 
 @Override
 protected void doGet(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
     
     HttpSession session = request.getSession(false);
     if (session == null || session.getAttribute("user") == null) {
         response.sendRedirect("login.jsp");
         return;
     }
     
     String action = request.getParameter("action");
     
     if ("list".equals(action)) {
         request.setAttribute("users", userService.getAllUsers());
         request.getRequestDispatcher("users-list.jsp").forward(request, response);
     } else if ("edit".equals(action)) {
         String idParam = request.getParameter("id");
         if (idParam != null) {
             int id = Integer.parseInt(idParam);
             User user = userService.getUserById(id);
             request.setAttribute("user", user);
             request.getRequestDispatcher("user-form.jsp").forward(request, response);
         }
         String idParam1 = request.getParameter("id");
         if (idParam1 != null) {
             int id = Integer.parseInt(idParam1);
             userService.deleteUser(id);
             response.sendRedirect("users?action=list");
         }
     }
 }
 
 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
     
     HttpSession session = request.getSession(false);
     if (session == null || session.getAttribute("user") == null) {
         response.sendRedirect("login.jsp");
         return;
     }
     
     String action = request.getParameter("action");
     String idParam = request.getParameter("id");
     
     if ("add".equals(action) || "edit".equals(action)) {
         User user = new User();
         
         if (idParam != null && !idParam.isEmpty()) {
             user.setId(Integer.parseInt(idParam));
         }
         
         user.setUsername(request.getParameter("username"));
         user.setPassword(request.getParameter("password"));
         user.setEmail(request.getParameter("email"));
         user.setRole(request.getParameter("role"));
         user.setFirstName(request.getParameter("firstName"));
         user.setLastName(request.getParameter("lastName"));
         user.setPhone(request.getParameter("phone"));
         user.setAddress(request.getParameter("address"));
         
         String dobStr = request.getParameter("dateOfBirth");
         if (dobStr != null && !dobStr.isEmpty()) {
             try {
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                 Date dob = sdf.parse(dobStr);
                 user.setDateOfBirth(dob);
             } catch (ParseException e) {
                 e.printStackTrace();
             }
         }
         
         boolean success;
         if ("add".equals(action)) {
             success = userService.registerUser(user);
         } else {
             success = userService.updateUser(user);
         }
         
         if (success) {
             response.sendRedirect("users?action=list");
         } else {
             request.setAttribute("error", "Operation failed");
             request.getRequestDispatcher("user-form.jsp").forward(request, response);
         }
     }
 }
}