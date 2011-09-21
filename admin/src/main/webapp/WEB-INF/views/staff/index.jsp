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
        <h2>Daftar Seluruh Staff</h2>
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="datatables" width="100%">
            <thead>
            <tr>
                <th>Id</th>
                <th>Username</th>
                <th>Telephone</th>
                <th>Status</th>
                <th>Hak Akses</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${data}" var="staff">
                <tr>
                    <td>${staff.id}</td>
                    <td>${staff.username}</td>
                    <td>${staff.telephone}</td>
                    <td>${staff.enabled ? "Aktif" : "Non Aktif"}</td>
                    <td>
                        <c:forEach var="role" items="${staff.roles}">
                            ${role.authority}&nbsp;&nbsp;
                        </c:forEach>
                    </td>
                    <td>
                        <div class="buttonWrapper">
                            <a href="<c:url value='/staff/${staff.id}' />" class="view">Lihat
                            </a>
                            <a href="<c:url value='/staff/${staff.id}/edit' />" class="edit">Ubah</a>
                            <a onclick="return confirm('Apakah anda Yakin ?')"
                               href="<c:url value='/staff/${staff.id}/delete' />"
                               class="delete">Non Aktifkan</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="6">
                    <div class="buttonWrapper">
                        <a href="<c:url value='/staff/new' />" class="view">Create</a>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
