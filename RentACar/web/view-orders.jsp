<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : view-orders
    Created on : Jul 2, 2024, 2:39:05 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:forEach var="order" items="${requestScope.orders}">
             <form action="cancel-order" method="POST">
                 <input type="hidden" name="order-uid" value="${order.orderUID}" />
                 <c:forEach var="car" items="${order.cars}">
                     <div> ${car.carNumberPlate} </div>
                 </c:forEach>
                     <a href="cancel-order?order-uid=${order.orderUID}">Cancel</a>
                     <a href="confirm-payment?order-uid=${order.orderUID}">Pay</a>
             </form>
        </c:forEach>
    </body>
</html>
