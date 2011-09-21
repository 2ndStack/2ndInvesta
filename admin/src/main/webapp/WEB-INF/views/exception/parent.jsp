<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">
    <div class="blocksection">
        <h2>Exception Occurred</h2>
        <c:if test="${not empty exception}">
            <c:out value="${exception.message}"/>
            <br/>
            <c:forEach items="${exception.stackTrace}" var="trace">
                <c:out value="${trace}"/>
                <br/>
            </c:forEach>
        </c:if>
    </div>
</div>
