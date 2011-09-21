<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="mainnav">
    <ul>
        <security:authorize access="fullyAuthenticated">
            <li ${mainNav == 'dashboard' ? 'class="active"' : 'none'}>
                <a href="<c:url value="/dashboard"/>" class="parent">Dashboard</a>
            </li>
            <li ${mainNav == 'profit' ? 'class="active"' : 'none'}>
                <a href="<c:url value="/user/profit"/>" class="parent">Profit</a>
            </li>
            <li ${mainNav == 'bonus' ? 'class="active"' : 'none'}>
                <a href="<c:url value="/user/bonus"/>" class="parent">Bonus</a>
            </li>
            <li ${mainNav == 'downline' ? 'class="active"' : 'none'}>
                <a href="<c:url value="/user/downline"/>" class="parent">Downline</a>
            </li>
            <li ${mainNav == 'profil' ? 'class="active"' : 'none'}>
                <a href="<c:url value="/user/profile"/>" class="parent">Profile</a>
            </li>
        </security:authorize>
    </ul>
</div>