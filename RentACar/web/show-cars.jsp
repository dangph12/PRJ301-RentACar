<%-- 
    Document   : index
    Created on : Jun 27, 2024, 3:07:02 PM
    Author     : admin
--%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round"
            />
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
            />
        <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/icon?family=Material+Icons"
            />
        <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
            />
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/index_style.css" />
    </head>
    <body>
        <div class="container">
            <c:set var="i" value="0"/>
            <c:forEach var="category" items="${requestScope.categories}">
                <c:if test="${i%3 == 0}">
                    <div class="row">
                    </c:if>
                    <div class="col-lg-4">
                        <form action="checkout" method="POST">
                            <div class="item">
                                <input type="hidden" name="category-uid" value="${category.categoryUID}" />
                                <div class="img">
                                    <img src="${category.image}" alt="${category.title}" />
                                </div>
                                <div class="content">
                                    <div class="title">${category.title}</div>
                                    <div class="des">
                                        ${category.description}  
                                    </div>
                                    <div class="number-of-seats">Số ghế: ${category.numberOfSeats}</div>
                                    <div class="price">${category.unitPrice} VND/xe/ngày</div>
                                    <button type="submit" class="add">Đặt xe ngay</button>
                                </div>
                            </div>
                        </form>
                    </div>  
                    <c:if test="${i%3 == 2}">
                    </div>
                </c:if>
                <c:set var="i" value="${i+1}"/>
            </c:forEach>
        </div>
        <ul class="listPage"></ul>
        <script src="js/index_script.js"></script>
    </body>
</html>

