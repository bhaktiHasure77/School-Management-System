<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>${param.action == 'edit' ? 'Edit User' : 'Add User'}</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; }
        .header { background: #007bff; color: white; padding: 15px; text-align: center; }
        .nav { background: #343a40; padding: 10px; }
        .nav a { color: white; text-decoration: none; padding: 10px 15px; display: inline-block; }
        .nav a:hover { background: #495057; }
        .content { padding: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        input, select { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 3px; }
        button { background: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 3px; cursor: pointer; }
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
        <h2>${param.action == 'edit' ? 'Edit User' : 'Add New User'}</h2>
        
        <form action="users" method="post">
            <input type="hidden" name="action" value="${param.action == 'edit' ? 'edit' : 'add'}">
            <c:if test="${param.action == 'edit'}">
                <input type="hidden" name="id" value="${user.id}">
            </c:if>
            
            <div class="form-group">
                <label>Username:</label>
                <input type="text" name="username" value="${user.username}" required>
            </div>
            
            <div class="form-group">
                <label>Password:</label>
                <input type="password" name="password" ${param.action == 'edit' ? '' : 'required'}>
            </div>
            
            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" value="${user.email}" required>
            </div>
            
            <div class="form-group">
                <label>Role:</label>
                <select name="role" required>
                    <option value="">Select Role</option>
                    <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>Admin</option>
                    <option value="teacher" ${user.role == 'teacher' ? 'selected' : ''}>Teacher</option>
                    <option value="student" ${user.role == 'student' ? 'selected' : ''}>Student</option>
                    <option value="staff" ${user.role == 'staff' ? 'selected' : ''}>Staff</option>
                </select>
            </div>
            
            <div class="form-group">
                <label>First Name:</label>
                <input type="text" name="firstName" value="${user.firstName}" required>
            </div>
            
            <div class="form-group">
                <label>Last Name:</label>
                <input type="text" name="lastName" value="${user.lastName}" required>
            </div>
            
            <div class="form-group">
                <label>Phone:</label>
                <input type="text" name="phone" value="${user.phone}">
            </div>
            
            <div class="form-group">
                <label>Address:</label>
                <input type="text" name="address" value="${user.address}">
            </div>
            
            <div class="form-group">
                <label>Date of Birth:</label>
                <input type="date" name="dateOfBirth" value="${user.dateOfBirth}">
            </div>
            
            <button type="submit">${param.action == 'edit' ? 'Update User' : 'Add User'}</button>
            <a href="users?action=list">Cancel</a>
        </form>
        
        <c:if test="${not empty error}">
            <div style="color: red; margin-top: 10px;">${error}</div>
        </c:if>
    </div>
</body>
</html>