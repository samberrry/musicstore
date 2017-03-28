<%--
  Created by IntelliJ IDEA.
  User: Hessam
  Date: 3/28/17
  Time: 7:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="template/header.jsp" %>

<div class="container-wrapper">
    <div class="container">
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Customer registered successfully</h1>
                </div>
            </div>
        </section>
        <section class="container" ng-app="cartApp">
            <p>
                <a href="<c:url value="/product/productList"/>" class="btn btn-default">Products</a>
            </p>
        </section>
    </div>
</div>

<script src="<c:url value="/resources/js/controller.js"/> "></script>

<%@include file="template/footer.jsp" %>