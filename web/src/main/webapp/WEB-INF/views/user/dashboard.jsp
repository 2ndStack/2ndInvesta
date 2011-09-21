<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">
    <c:forEach var="berita" items="${listBerita}">
        <h3><a href="<c:url value="/berita/${berita.id}"/>">${berita.nama}</a></h3>
        ${berita.tanggal}<br/>
        <p>${berita.isi}</p>
    </c:forEach>
</div>