<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en" xmlns:c="">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Lista automobila</title>
</head>
<body>
<h1>Lista automobila</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Marka</th>
        <th>Model</th>
        <th>Cena po danu</th>
        <th>Dostupnost</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cars}" var="car">
        <tr>
            <td>${car.id}</td>
            <td>${car.brand}</td>
            <td>${car.model}</td>
            <td>${car.pricePerDay} RSD</td>
            <td>${car.rented ? 'Ne' : 'Da'}</td>
            <td><a href="/rent/${car.id}">Iznajmi</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>