<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    Cookie[] cookies = request.getCookies();
    String email = null;
    String password = null;

    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("email")) {
                email = cookie.getValue();
            } else if (cookie.getName().equals("password")) {
                password = cookie.getValue();
            }
        }
    }

    if (email != null && password != null) {
        String emailFromForm = request.getParameter("email");
        String passwordFromForm = request.getParameter("password");

        if (emailFromForm != null && passwordFromForm != null && emailFromForm.equals(email) && passwordFromForm.equals(password)) {
            response.sendRedirect("home.jsp");
        }
    } else {
        out.print("<h1>Morate se prvo registrovati!</h1>");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form action="login.jsp" method="post">
    <label>Email:</label>
    <input type="email" name="email" required><br><br>
    <label>Lozinka:</label>
    <input type="password" name="password" required><br><br>
    <input type="submit" value="Uloguj se">
</form>
</body>
</html>