<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <c:forEach var="c" items="${allproducts}">
        <div class="card col-sm-4">
            <img alt="${c.productName}"
                 class="card-img-top"
                 src="${c.productImg}">
            <div class="card-body">
                <h5 class="card-title text-truncate" data-placement="top" data-toggle="tooltip">
                    ${c.productName}</h5>
                <p class="card-text">Class: ${c.productInfo}</p>
                <h5>Price: ${c.productCost}</h5>
                <a class="btn btn-primary" href="#">Buy</a>
            </div>
        </div>
    </c:forEach>
</div>