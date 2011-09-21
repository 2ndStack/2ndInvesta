<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">
    <div class="blocksection">
        <c:forEach items="${berita}" var="data">
            <div class="blockcontent">
                <h3>&nbsp;</h3>

                <div class="blockcontent-content">
                    <h3 style="text-align:center;"><a href="<c:url value="/berita/${data.id}"/>">${data.nama}</a></h3>

                    <p>${data.isi}</p>

                    <hr/>
                    <div>Updated by : ${data.staff !=null ? data.staff.username : ''} on <fmt:formatDate
                            value="${data.tanggal}" pattern="dd MMMM yyyy"/></div>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="buttonWrapper">
    </div>
</div>