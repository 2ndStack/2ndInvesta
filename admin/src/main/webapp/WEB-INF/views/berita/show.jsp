<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content">
    <div class="blocksection">
        <div class="blockcontent">
            <h3>Detail Berita</h3>

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
        <a href="<c:url value='/berita' />" class="view">Kembali
        </a>
        <a href="<c:url value='/berita/${data.id}/edit' />" class="edit">Ubah</a>
        <a onclick="return confirm('Apakah anda Yakin ?')"
           href="<c:url value='/berita/${data.id}/delete' />"
           class="delete">Hapus</a>
    </div>
</div>