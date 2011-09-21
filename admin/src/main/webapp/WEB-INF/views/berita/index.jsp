<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="<c:url value='/resources/js/jquery.dataTables.js'/>" type="text/javascript"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/table.css"/>" type="text/css"/>
<script type="text/javascript">
    $(document).ready(function() {
        oTable = $('#datatables').dataTable({
            "sPaginationType": "full_numbers"
        });
    });
</script>
<div class="content">
    <div class="blocksection">
        <h2>Daftar Seluruh Berita</h2>
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="datatables" width="100%">
            <thead>
            <tr>
                <th style="width: 30px">Id</th>
                <th style="width: 50px">Staff</th>
                <th>Judul</th>
                <th>Tanggal</th>
                <th>Operasi</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${data}" var="berita">
                <tr>
                    <td>${berita.id}</td>
                    <td>${(berita.staff == null) ? ' ' : berita.staff.username}</td>
                    <td>${berita.nama}</td>
                    <td><fmt:formatDate value="${berita.tanggal}" pattern="dd MMMM yyyy"/></td>
                    <td>
                        <div class="buttonWrapper">
                            <a href="<c:url value='/berita/${berita.id}' />" class="view">Lihat
                            </a>
                            <a href="<c:url value='/berita/${berita.id}/edit' />" class="edit">Ubah</a>
                            <a onclick="return confirm('Apakah anda Yakin ?')"
                               href="<c:url value='/berita/${berita.id}/delete' />"
                               class="delete">Hapus</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="5">
                    <div class="buttonWrapper">
                        <a href="<c:url value='/berita/new' />" class="view">Tambah</a>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
