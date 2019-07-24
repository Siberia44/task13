
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-3 ">
            <form class="sticky-top p-2" action="${pageContext.request.contextPath}/search" >
                <h3>Producers</h3>

                <h3>Class</h3>
                <tag:productCountryTag country="${country}"/>
                <tag:productTypeTag type="${type}"/>

                <div class="form-group">
                    <label for="exampleInputEmail1">Min price</label>
                    <input type="number" class="input-number" name="minPrice" id="exampleInputEmail1">
                </div>

                <div class="form-group">
                    <label for="exampleInputPassword1">Max price</label>
                    <input type="number" class="input-number" name="maxPrice" id="exampleInputPassword1">
                </div>

                <div class="form-group">
                    <label for="exampleInputPassword1">Search for name</label>
                    <input type="text" class="input-number2" name="searchByName" id="exampleInputPassword12" value="${searchForm.query}">
                </div>

                <button type="submit" class="btn btn-primary">Search</button>
            </form>

        </div>
    </div>
</div>