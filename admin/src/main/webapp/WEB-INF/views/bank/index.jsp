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

<h1>Profit Bulanan</h1>
<div class="content">
    <table cellpadding="0" cellspacing="0" border="0" class="display" id="datatables" width="100%">
        <thead>
        <tr>
            <th>Id</th>
            <th>Nama</th>
            <th>Operasi</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${data}" var="bank">
            <tr>
                <td>${bank.id}</td>
                <td>${bank.nama}</td>
                <td>
                    <div class="buttonWrapper">
                        <a href="<c:url value='/bank/${bank.id}' />" class="view">Lihat
                        </a>
                       <%-- <a href="<c:url value='/bank/${bank.id}/edit' />" class="edit">Ubah</a>
                        <a onclick="return confirm('Apakah anda Yakin ?')"
                           href="<c:url value='/bank/${bank.id}/delete' />"
                           class="delete">Hapus</a>--%>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
      <%--  <tr>
            <td colspan="3">
                <div class="buttonWrapper">
                    <a href="<c:url value='/bank/new' />" class="view">Tambah</a>
                </div>
            </td>
        </tr>--%>
        </tfoot>
    </table>
</div>