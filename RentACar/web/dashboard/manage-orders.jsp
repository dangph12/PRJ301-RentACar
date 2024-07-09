<%-- 
    Document   : manage-orders
    Created on : Jun 8, 2024, 9:42:23 AM
    Author     : admin
--%>

<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Manage Orders</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
    </head>
    <body>
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-3">
                                <h2>Manage <b>Orders</b></h2>
                            </div>
                            <div class="col-sm-3">
                                <form action="manage-orders" method="GET">
                                    <div class="search-box">
                                        <i class="material-icons">&#xE8B6;</i>
                                        <input type="text" name="search" class="form-control" placeholder="Search&hellip;" value="${requestScope.search}">
                                    </div>
                                </form>
                            </div>
                            <div class="col-sm-6">
                                <button type="button" href="#confirmReceivedCarModal" class="btn btn-primary" data-toggle="modal"><i class="material-icons">&#xe5d8;</i> <span>Customer received the car</span></button>	
                                <button type="button" href="#confirmReturnedCarModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xe5db;</i> <span>Customer returned the car</span></button>
                                
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th scope="col">
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="selectAll">
                                        <label for="selectAll"></label>
                                    </span>
                                </th>
                                <th scope="col">Order ID</th>
                                <th scope="col">Full Name</th>
                                <th scope="col">Received At</th>
                                <th scope="col">Returned At</th>
                                <th scope="col">Order Status</th>
                                <th scope="col">Payment Status</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.pagingOrders}" var="order">
                                <tr>
                                    <td>
                                        <span class="custom-checkbox">
                                            <input type="checkbox" class="check" name="options[]" value="${order.orderUID}">
                                            <label for="checkbox"></label>
                                        </span>
                                    </td>
                                    <td>${order.orderUID}</td>
                                    <td>${order.user.fullName}</td>
                                    <td>${order.receivedDate}</td>
                                    <td>${order.returnedDate}</td>
                                    <td>${order.orderStatus.description}</td>
                                    <td>${order.bill.paid == true ? "Paid" : "Not paid"}</td>
                                    <td>
                                        <div class="btn-group" role="group">
                                            <button type="button" href="#confirmReturnedCarModal" class="btn btn-mini" data-toggle="modal" data-orderuid="${order.orderUID}">
                                                <i class="material-icons text-success" data-toggle="tooltip" title="Customer returned the car">&#xe5db</i>
                                            </button>
                                            <button type="button" href="#confirmReceivedCarModal" class="btn btn-mini" data-toggle="modal" data-orderuid="${order.orderUID}">
                                                <i class="material-icons text-primary" data-toggle="tooltip" title="Customer received the car">&#xe5d8</i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="clearfix">
                        <div class="hint-text">Showing <b>${pagingOrdersCount}</b> out of <b>${allOrdersCount}</b> entries</div>
                        <ul class="pagination">
                            <c:forEach begin="1" end="${endPage}" var="i">
                                <li class="page-item">
                                    <c:choose>
                                        <c:when test="${requestScope.search == null}">
                                            <a href="?page=${i}" class="page-link">${i}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="?page=${i}&search=${requestScope.search}" class="page-link">${i}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>        
        </div>
        <div id="confirmReceivedCarModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="confirm-received-cars" method="POST">
                        <div class="modal-header">						
                            <h4 class="modal-title">Confirm Received Cars</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="text" name="orderUID"/>
                            </div>
                            <p>Are you sure you want to delete these Records?</p>
                            <p class="text-warning"><small>This action cannot be undone.</small></p>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-danger" value="Delete">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div id="confirmReturnedCarModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="confirm-returned-cars" method="POST">
                        <div class="modal-header">						
                            <h4 class="modal-title">Confirm Returned Cars</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="text" name="orderUID"/>
                            </div>
                            <p>Are you sure you want to delete these Records?</p>
                            <p class="text-warning"><small>This action cannot be undone.</small></p>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-danger" value="Delete">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="<%=request.getContextPath()%>/js/manage-orders.js"></script>
    </body>
</html>
