<%-- 
    Document   : home
    Created on : Feb 6, 2024, 9:45:27 AM
    Author     : Jackson Tai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="StringUtil" class="util.StringUtil"/>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="/shared/component/head_source.jsp" %>
    <script>
        const contextPath = "${pageContext.request.contextPath}";
    </script>
    <script src="${pageContext.request.contextPath}/asset/js/constant/endpointConstant.js"></script>
    <script src="${pageContext.request.contextPath}/asset/js/customer/deleteCustomer.js"></script>
    <title>Customer</title>
</head>
<body>
<%@ include file="/shared/component/header.jsp" %>
<main class="w-75 my-2 mx-auto overflow-x-auto">
    <h1 class="text-center">Customers</h1>
    <a class="btn btn-primary btn-sm" href="<c:url value='<%= EndpointConstant.CREATE_CUSTOMER %>'/>" role="button">
        Create Customer Profile</a>
    <c:choose>
        <c:when test="${empty customers}">
            <p class="text-center">There is no customer records currently</p>
        </c:when>
        <c:otherwise>
            <table class="table table-bordered w-100 my-2">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Full Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Phone Number</th>
                    <th scope="col">Gender</th>
                    <th scope="col">Date Of Birth</th>
                    <th scope="col">Address</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${customers}" var="customer">
                    <tr>
                        <td>${StringUtil.requireNonNullElse(customer.customerId, DASH)}</td>
                        <td>${StringUtil.requireNonNullElse(customer.fullName, DASH)}</td>
                        <td>${StringUtil.requireNonNullElse(customer.email, DASH)}</td>
                        <td>${StringUtil.requireNonNullElse(customer.phoneNumber, DASH)}</td>
                        <td>${StringUtil.requireNonNullElse(customer.gender, DASH)}</td>
                        <td>${StringUtil.requireNonNullElse(
                                StringUtil.toLocalDateFormat(customer.dateOfBirth, DMY_SLASH_DATE_FORMAT), DASH)}</td>
                        <td>${StringUtil.requireNonNullElse(customer.address, DASH)}</td>
                        <td>
                            <a class="btn btn-light btn-sm"
                               href="<c:url value='<%= EndpointConstant.UPDATE_CUSTOMER %>'/>?id=${customer.customerId}"
                               role="button">Update</a>
                            <a class="btn btn-danger btn-sm" onclick="confirmDelete('${customer.customerId}')"
                               role="button">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
    <%@ include file="/shared/component/pagination.jsp" %>
</main>

</body>
</html>