<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirmPassword");

    if(password.equals(confirmPassword)) {
        Cookie emailCookie = new Cookie("email", email);
        Cookie passwordCookie = new Cookie("password", password);

        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);

        response.sendRedirect("login.jsp");
    } else {
        out.print("<h1>Lozinke se ne poklapaju!</h1>");
    }
%>