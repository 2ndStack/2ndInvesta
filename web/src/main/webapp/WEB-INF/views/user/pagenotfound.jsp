<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="content">
    <c:url value="/register/${user.username}" var="register_url"/>
    <form id="formRegister" method="get" action="${register_url}">
        <div class="blocksection">
            <div class="blockcontent">
                <h3>Page Not Found</h3>
            </div>
        </div>
    </form>
</div>

