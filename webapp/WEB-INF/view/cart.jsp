<%--
  Created by IntelliJ IDEA.
  User: Hessam
  Date: 3/20/17
  Time: 12:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="template/header.jsp" %>

<div class="container-wrapper">
    <div class="container">
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Cart</h1>
                    <p>All the selected products in your shopping cart</p>
                </div>
            </div>
        </section>
        <section class="container" ng-app="cartApp">
            <div ng-controller="cartCtrl" ng-init="initCartId('${cartId}')">
            <div>
                <a class="btn btn-danger pull-left" ng-click="clearCart()"><span class="glyphicon glyphicon-remove-sign"></span> Clear Cart</a>
            </div>

            <table class="table table-hover">
                <tr>
                    <th>Product</th>
                    <th>Unit Price</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
                <tr ng-repeat="item in cart.cartItems">
                    <td>{{item.product.productName}}</td>
                    <td>{{item.product.productPrice}}</td>
                    <td>{{item.Quantity}}</td>
                    <td>{{item.totalPrice}}</td>
                    <td><a href="#" class="label label-danger" ng-click="removeFromCart(item.product.productId)"> <span class="glyphicon glyphicon-remove"/> remove</a></td>
                </tr>
                <tr>
                    <th></th>
                    <th></th>
                    <th><b>Grand Total</b></th>
                    <th>{{calGrandTotal()}}</th>
                    <th></th>
                </tr>
            </table>
            <a href="<c:url value="/product/productList"/>" class="btn btn-default">Continue Shopping</a>
            </div>
        </section>
    </div>
</div>

<script src="<c:url value="/resources/js/controller.js"/> "></script>

<%@include file="template/footer.jsp" %>
