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
    <table cellpadding="0" cellspacing="0" border="0" class="display" id="datatables" width="100%">
        <thead>
        <tr>
            <th>Username</th>
            <th>Nama</th>
            <th>Paket</th>
            <th>Aktivasi</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listUser}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.nama}</td>
                <td>${user.nilaiDeposit}</td>
                <td>${user.tanggalAktivasi}</td>
                <td>${user.status}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
