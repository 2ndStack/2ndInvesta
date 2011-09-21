<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="<c:url value='/resources/js/jquery.dataTables.js'/>" type="text/javascript"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/table.css"/>" type="text/css"/>
<script type="text/javascript">
    $(document).ready(function() {
        oTable = $('#datatables').dataTable({
            "sPaginationType": "full_numbers",
            "aoColumnDefs": [
                { "bSortable": false, "aTargets": [0] },
                { "bSortable": false, "aTargets": [1] },
                { "bSortable": false, "aTargets": [2] }
            ]
        });
    });
</script>

<div class="content">
    <div class="blocksection">
        <h2 style="text-align:center;">Daftar Laporan Bank ${namaBank}</h2>
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="datatables" width="100%">
            <thead>
            <tr>
                <th>No</th>
                <th>Nama File</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:set value="1" var="no"/>
            <c:forEach items="${data}" var="item">
                <tr>
                    <td>${no}</td>
                    <td>${item}</td>
                    <td><a href="<c:url value="/report/profit/${namaBank}/${item}" />">Download</a></td>
                </tr>
                <c:set value="${no+1}" var="no"/>
            </c:forEach>

            </tbody>
            <tfoot>
            <tfoot>
            <tr>
                <td colspan="3">
                    <div class="buttonWrapper">
                        <a href="<c:url value='/profit/${namaBank}' />" class="view">Kembali</a>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>

</div>
