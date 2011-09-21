<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="content">
    <form action="">
        <div class="blocksection">
            <div class="blockcontent">
                <h3>Detail Paket</h3>
                <table cellpadding="0" cellspacing="0" border="0" width="928px" id="table_view">
                    <tbody>
                    <tr>
                        <td width="25%">Nama :</td>
                        <td width="75%">${data.nama}</td>
                    </tr>
                    <tr>
                        <td width="25%">Harga :</td>
                        <td width="75%">Rp.&nbsp;<fmt:formatNumber value="${data.harga}" pattern="#,##0.0"/></td>
                    </tr>
                    <tr>
                        <td width="25%">Status :</td>
                        <td width="75%">${data.enabled ? "Aktif" : "Non Aktif"}</td>
                    </tr>
                    </tbody>
                </table>
                <div class="form-agree buttonWrapper">
                    <a href="<c:url value='/paket' />" class="back">Kembali
                    </a>
                    <a href="<c:url value='/paket/${data.id}/edit' />" class="edit">Ubah</a>
                    <a onclick="return confirm('Apakah anda Yakin ?')"
                       href="<c:url value='/paket/${data.id}/delete' />"
                       class="delete">Hapus</a>
                </div>
            </div>
        </div>
    </form>
</div>
