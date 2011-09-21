<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="content">

    <c:url value="/profit/manual" var="profit_url"/>
    <form id="formEdit" method="post" action="${profit_url}">
        <input type="hidden" name="id" value="${profit.id}"/>

        <div class="blocksection">
            <div class="blockcontent">
                <h3>Proses hitung profit bulanan sudah diproses</h3>

                <div class="buttonWrapper">
                    <a href="<c:url value="/profit/manual" />">Kembali</a>
                </div>
            </div>
        </div>
    </form>
</div>
