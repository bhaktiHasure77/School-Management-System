<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; }
        .header { background: #007bff; color: white; padding: 15px; text-align: center; }
        .nav { background: #343a40; padding: 10px; }
        .nav a { color: white; text-decoration: none; padding: 10px 15px; display: inline-block; }
        .nav a:hover { background: #495057; }
        .content { padding: 20px; }
        .welcome { margin-bottom: 20px; }
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
        <div class="welcome">
            <h2>Welcome, ${sessionScope.user.firstName} ${sessionScope.user.lastName} (Admin)</h2>
            <p>You have administrative privileges to manage the school system.</p>
        </div>
        
        <div class="stats">
            <h3>Quick Stats</h3>
            <p>Total Users: <!-- Add count from database --></p>
            <p>Active Students: <!-- Add count from database --></p>
            <p>Active Teachers: <!-- Add count from database --></p>
            <p>Active Staff: <!-- Add count from database --></p>
        </div>
    </div>
</body>
</html>