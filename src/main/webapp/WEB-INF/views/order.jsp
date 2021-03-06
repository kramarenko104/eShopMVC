<%@ include file="includes/header.jsp" %>

<c:set var="order" value="${order}"/>

<c:if test="${user != null && username != null}">
    <c:choose>
        <c:when test="${order != null and order.itemsCount > 0}">
            <br>
            <div id="infoGreen">Thank you for your purchase! Your last order # ${order.orderNumber} includes:</div>
            <br><br>

            <div id="cart_content">
                <table id="myTable" border=1>
                    <tr id="tableTitle">
                        <td>Product's name</td>
                        <td>Price</td>
                        <td>Quantity</td>
                    </tr>

                    <c:forEach var="item" items="${order.products}">
                        <tr>
                            <td><c:out value="${item.key.name}"/></td>
                            <td><c:out value="${item.key.price}"/> UAH</td>
                            <td><c:out value="${item.value}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <br><br>

            <div id="info">
                Total order's sum: ${order.totalSum==null?0:order.totalSum} UAH
                <br>Total items' quantity: ${(order.itemsCount==null) ? 0 : order.itemsCount}
            </div>
        </c:when>
        <c:otherwise>
            <span id="infoGreen">Order wasn't created yet.</span>
        </c:otherwise>
    </c:choose>
</c:if>

<%@ include file="includes/footer.jsp" %>
