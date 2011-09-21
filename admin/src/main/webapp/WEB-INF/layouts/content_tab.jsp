<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:if test="${mainNav == 'master'}">
    <div class="content-tab">
        <ul>
            <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_STAFF">
            <li class="${tab == 'berita' ? 'active' : ''}"><a href="<c:url value='/berita'/>">Berita</a>
            <li class="${tab == 'page' ? 'active' : ''}"><a href="<c:url value='/page'/>">Page</a>
                </security:authorize>
                <security:authorize ifAnyGranted="ROLE_ADMIN">
            <li class="${tab == 'staff' ? 'active' : ''}"><a href="<c:url value='/staff'/>">Staff</a></li>
            <li class="${tab == 'user' ? 'active' : ''}"><a href="<c:url value='/user'/>#">Member</a></li>
            <li class="${tab == 'paket' ? 'active' : ''}"><a href="<c:url value='/paket'/>#">Paket</a></li>
            <li class="${tab == 'role' ? 'active' : ''}"><a href="<c:url value='/role'/>#">Role</a></li>
            <li class="${tab == 'bank' ? 'active' : ''}"><a href="<c:url value='/bank'/>#">Bank</a></li>
            </security:authorize>

        </ul>
    </div>
</c:if>
