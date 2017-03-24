<%@include file="template/header.jsp"%>


<div class="container-wrapper">

    <div class="container">
        <div class="page-header">
            <h1>Administrator Page</h1>

            <p class="lead">This is the administration page </p>
        </div>

        <%--retrieve from spring security context--%>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h2>
                Welcome: ${pageContext.request.userPrincipal.name} | <a href="<c:url value="/j_spring_security_logout"/> ">Logout</a>
            </h2>
        </c:if>

        <h3>
            <a href="<c:url value="/admin/productInventory"/> "> Product Inventory </a>
        </h3>

        <p>
            Here you can view, check and modify the product inventory
        </p>


<%@include file="template/footer.jsp"%>