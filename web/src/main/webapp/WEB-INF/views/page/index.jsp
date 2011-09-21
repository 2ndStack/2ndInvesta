<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">
    <div class="blocksection">
        <div class="blockcontent">
            <h3>&nbsp;</h3>

            <div class="blockcontent-content">
                <h2 style="text-align:center;">${data.nama}</h2>

                <p>${data.isi}</p>

                <hr/>
                <div>Updated by : ${data.staff !=null ? data.staff.username : ''} on <fmt:formatDate
                        value="${data.tanggal}" pattern="dd MMMM yyyy"/></div>
            </div>
        </div>

    </div>

    <div class="buttonWrapper">
    </div>
</div>