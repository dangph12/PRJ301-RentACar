<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : view-orders
    Created on : Jul 2, 2024, 2:39:05 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/view_orders.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <section class="vh-100">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-md-10 col-lg-8 col-xl-8">
                        <div class="card card-stepper" style="border-radius: 16px;">
                            <div class="card-header p-4">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div>
                                        <p class="text-muted mb-2"> Order ID <span class="fw-bold text-body">${requestScope.order.orderUID}</span></p>
                                        <p class="text-muted mb-0"> Place On <span class="fw-bold text-body">${requestScope.order.createdDate}</span> </p>
                                    </div>
                                    <div>
                <h6 class="mb-0">${requestScope.order.bill.totalAmount}</h6>
              </div>
                                </div>
                            </div>
                            <c:forEach var="car" items="${requestScope.order.cars}">
                                <div class="card-body p-4">
                                    <div class="d-flex flex-row mb-4 pb-2">
                                        <div class="flex-fill">
                                            <h5 class="bold">${car.category.title}</h5>
                                            <p class="text-muted">BKS: ${car.carNumberPlate}</p>
                                            <h4 class="mb-3">${car.category.unitPrice} đ <span class="small text-muted"> via (COD) </span></h4>
                                            
                                        </div>
                                        <div>
                                            <img class="align-self-center img-fluid" src="${car.category.image}" width="250">
                                        </div>
                                    </div>
                                </div>

                            </c:forEach>        
                            <div class="card-footer p-4">
                                <div class="d-flex justify-content-between">
                                    <div class="border-start h-100"></div>
                                    <h5 class="fw-normal mb-0"><a href="cancel-order?order-uid=${order.orderUID}">Cancel</a></h5>
                                    <div class="border-start h-100"></div>
                                    <h5 class="fw-normal mb-0"><a href="confirm-payment?order-uid=${order.orderUID}">Pre-pay</a></h5>
                                    <div class="border-start h-100"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
