<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div id="secnav">
    <ul>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <security:authorize access="fullyAuthenticated">
            <li><a href="<c:url value='/dashboard'/>">Dashboard</a></li>
            <li class="subnav">
                <a href="<c:url value='/user/password'/>">Options</a>
                <ul>
                    <li><a href="<c:url value='/user/password'/>">Ubah Password</a></li>
                </ul>
            </li>
        </security:authorize>
        <li><a href="#">Forum</a></li>
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
