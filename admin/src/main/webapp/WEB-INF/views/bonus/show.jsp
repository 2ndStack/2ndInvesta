<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="content">
    <form action="">
        <div class="blocksection">
            <div class="blockcontent">
                <h3>Detail Bonus</h3>
                <table cellpadding="0" cellspacing="0" border="0" width="928px" id="table_view">
                    <tbody>
                    <tr>
                        <td width="25%">User :</td>
                        <td width="75%">${data.user.username}</td>
                    </tr>
                    <tr>
                        <td width="25%">Bulan :</td>
                        <td width="75%">${bulan} - ${data.tahun}</td>
                    </tr>
                    <tr>
                        <td width="25%">Status :</td>
                        <td width="75%">${status}</td>
                    </tr>
                    <tr>
                        <td width="25%">Keterangan :</td>
                        <td width="75%"><p>${data.keterangan}</p></td>
                    </tr>
                    </tbody>
                </table>
                <div class="form-agree buttonWrapper">
                    <a href="<c:url value='/bonus/${namaBank}' />" class="back">Kembali
                    </a>
                    <a href="<c:url value='/bonus/${namaBank}/${data.id}/edit' />" class="edit">Ubah</a>
                </div>
            </div>
        </div>
    </form>
</div>
