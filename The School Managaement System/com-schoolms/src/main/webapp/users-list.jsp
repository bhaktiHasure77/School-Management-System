<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Users</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; }
        .header { background: #007bff; color: white; padding: 15px; text-align: center; }
        .nav { background: #343a40; padding: 10px; }
        .nav a { color: white; text-decoration: none; padding: 10px 15px; display: inline-block; }
        .nav a:hover { background: #495057; }
        .content { padding: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .actions a { margin-right: 10px; text-decoration: none; }
    </style>
</head>
<body>
    <div class="header">
        <h1>School Management System</h1>
    </div>
    
    <div class="nav">
        <a href="admin-dashboard.jsp">Dashboard</a>
        <a href="users?action=list">Manage Users</a>
        <a href="user-form.jsp">Add User</a>
        <a href="logout.jsp">Logout</a>
    </div>
    
    <div class="content">
        <h2>User Management</h2>
        
        <table>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Phone</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.firstName} ${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <td>${user.phone}</td>
                    <td class="actions">
                        <a href="users?action=edit&id=${user.id}">Edit</a>
                        <a href="users?action=delete&id=${user.id}" 
                           onclick="return confirm('Are you sure you want to delete this user?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>