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
        <h2>Daftar Seluruh Page</h2>
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="datatables" width="100%">
            <thead>
            <tr>
                <th style="width: 30px">Id</th>
                <th style="width: 50px">Staff</th>
                <th>Judul</th>
                <th>Main Nav</th>
                <th>Tanggal</th>
                <th>Operasi</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${data}" var="page">
                <tr>
                    <td>${page.id}</td>
                    <td>${(page.staff == null) ? ' ' : page.staff.username}</td>
                    <td>${page.nama}</td>
                    <td>${page.mainNav}</td>
                    <td><fmt:formatDate value="${page.tanggal}" pattern="dd MMMM yyyy"/></td>
                    <td>
                        <div class="buttonWrapper">
                            <a href="<c:url value='/page/${page.id}' />" class="view">Lihat
                            </a>
                            <a href="<c:url value='/page/${page.id}/edit' />" class="edit">Ubah</a>
                            <a onclick="return confirm('Apakah anda Yakin ?')"
                               href="<c:url value='/page/${page.id}/delete' />"
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
                        <a href="<c:url value='/page/new' />" class="view">Tambah</a>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
