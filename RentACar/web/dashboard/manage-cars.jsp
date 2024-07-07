<%-- 
    Document   : manage-cars
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
        <title>Manage Cars</title>
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
                            <div class="col-sm-4">
                                <h2>Manage <b>Cars</b></h2>
                            </div>
                            <div class="col-sm-3">
                                <form action="manage-cars" method="GET">
                                    <div class="search-box">
                                        <i class="material-icons">&#xE8B6;</i>
                                        <input type="text" name="search" class="form-control" placeholder="Search&hellip;" value="${requestScope.search}">
                                    </div>
                                </form>
                            </div>
                            <div class="col-sm-5">
                                <button type="button" href="#setAvailableCars" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>setAvailableCars</span></button>	
                                <button type="button" href="#setUnavailableCars" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>setUnavailableCars</span></button>	
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
                                <th scope="col">Number Plate</th>
                                <th scope="col">Title</th>
                                <th scope="col">Status</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.pagingCars}" var="car">
                                <tr>
                                    <td>
                                        <span class="custom-checkbox">
                                            <input type="checkbox" class="check" name="options[]" value="${car.carNumberPlate}">
                                            <label for="checkbox"></label>
                                        </span>
                                    </td>
                                    <td>${car.carNumberPlate}</td>
                                    <td>${car.title}</td>
                                    <td>${car.carStatus.description}</td>
                                    <td>
                                        <div class="btn-group" role="group">
                                            <button type="button" href="#setAvailableCars" class="btn btn-mini delete" data-toggle="modal" data-carnumberplate="${car.carNumberPlate}">
                                                <i class="material-icons text-danger" data-toggle="tooltip" title="Delete">&#xE872;</i>
                                            </button>
                                            <button type="button" href="#setUnavailableCars" class="btn btn-mini delete" data-toggle="modal" data-carnumberplate="${car.carNumberPlate}">
                                                <i class="material-icons text-danger" data-toggle="tooltip" title="Delete">&#xE872;</i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="clearfix">
                        <div class="hint-text">Showing <b>${pagingCarsCount}</b> out of <b>${allCarsCount}</b> entries</div>
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
        <div id="setAvailableCars" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="set-available-cars" method="POST">
                        <div class="modal-header">						
                            <h4 class="modal-title">Set Unavailable Cars</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="text" name="carNumberPlate"/>
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
        <div id="setUnavailableCars" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="set-unavailable-cars" method="POST">
                        <div class="modal-header">						
                            <h4 class="modal-title">Set Unavailable Cars</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="text" name="carNumberPlate"/>
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
        <script src="<%=request.getContextPath()%>/js/manage-cars.js"></script>
    </body>
</html>
