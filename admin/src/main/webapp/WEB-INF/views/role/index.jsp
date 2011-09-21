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
                <th>Authority</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${data}" var="role">
                <tr>
                    <td>${role.authority}</td>
                    <td>
                        <div class="buttonWrapper">
                            <a href="<c:url value='/role/${role.authority}' />" class="view">Lihat
                            </a>
                            <%--<a href="<c:url value='/role/${role.authority}/edit' />" class="edit">Ubah</a>--%>
                           <%-- <a onclick="return confirm('Apakah anda Yakin ?')"
                               href="<c:url value='/role/${role.authority}/delete' />"
                               class="delete">Hapus</a>--%>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
           <%-- <tr>
                <td colspan="2">
                    <div class="buttonWrapper">
                        <a href="<c:url value='/role/new' />" class="view">Create</a>
                    </div>
                </td>
            </tr>--%>
            </tfoot>
        </table>
    </div>
</div>
