<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div id="secnav">
    <ul>
        <li>
            <a href="<c:url value='/forum'/>">Forum</a>
        </li>
        <li>
            <a href="<c:url value='/staff/password'/>">Ubah Password</a>
        </li>
        <li>
            <security:authorize access="!fullyAuthenticated">
                <a href="<c:url value='/loginpage'/>">Login</a>
            </security:authorize>
            <security:authorize access="fullyAuthenticated">
                <a href="<c:url value='/logout'/>">Logout</a>
            </security:authorize>
        </li>
    </ul>
</div>
