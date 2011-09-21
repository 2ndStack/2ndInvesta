<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="<c:url value='/resources/js/jquery.dataTables.js'/>" type="text/javascript"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/table.css"/>" type="text/css"/>
<c:url value="/user" var="user_url"/>
<script type="text/javascript">
    function addSparator(nStr) {
        nStr += '';
        x = nStr.split('.');
        x1 = x[0];
        x2 = x.length > 1 ? '.' + x[1] : '';
        var rgx = /(\d+)(\d{3})/;
        while (rgx.test(x1)) {
            x1 = x1.replace(rgx, '$1' + '.' + '$2');
        }
        return x1 + x2;
    }
    $(document).ready(function() {
        var oTable = $('#datatables').dataTable({
            "bProcessing": true,
            "bServerSide": true,
            "sAjaxSource": "<c:url value='/user/index.json' />",
            "sPaginationType": "full_numbers",
            "aoColumnDefs": [
                {
                    "fnRender": function (oObj) {
                        var statusLink = "";
                        if (oObj.aData[8] == "INACTIVE") {
                            statusLink = "<a  onclick=\"return confirm(\'Apakah anda Yakin ?\')\" class='view' href='${user_url}/status/" + oObj.aData[9] + "/ACTIVE'>Aktifkan</a>";
                        } else if (oObj.aData[8] == "ACTIVE") {
                            statusLink = "<a  onclick=\"return confirm(\'Apakah anda Yakin ?\')\"  class='view' href='${user_url}/status/" + oObj.aData[9] + "/BLOCKED'>Blokir</a>";
                        } else if (oObj.aData[8] == "BLOCKED") {
                            statusLink = "<a  onclick=\"return confirm(\'Apakah anda Yakin ?\')\"  class='view' href='${user_url}/status/" + oObj.aData[9] + "/ACTIVE'>Aktifkan</a>";
                        } else {
                        }
                        return "<div class='buttonWrapper'>" +
                                statusLink +
                                " <a href='${user_url}/" + oObj.aData[9] + "' class='view'>Lihat</a>" +
                                "<a href='${user_url}/" + oObj.aData[9] + "/edit' class='edit'>Ubah</a>" +
                                "<a href='${user_url}/" + oObj.aData[9] + "/reset' class='edit'>Reset Password</a>" +
                                "</div>";
                    },
                    "aTargets": [ 9 ]
                } ,
                {
                    "fnRender": function (oObj) {
                        var result = "<div style='text-align:right;'>" + addSparator(oObj.aData[4]) + "</div> ";
                        return result;
                    },
                    "aTargets": [4]
                },
                { "bSortable": false, "aTargets": [ 0 ] },
                { "bSortable": false, "aTargets": [ 1 ] },
                { "bSortable": false, "aTargets": [ 2 ] },
                { "bSortable": false, "aTargets": [ 3 ] },
                { "bSortable": false, "aTargets": [ 4 ] },
                { "bSortable": false, "aTargets": [ 5 ] },
                { "bSortable": false, "aTargets": [ 6 ] },
                { "bSortable": false, "aTargets": [ 7 ] },
                { "bSortable": false, "aTargets": [ 8 ] },
                { "bSortable": false, "aTargets": [ 9 ] }
            ]
        });
        $("#statusFilter").change(function() {
            oTable.fnFilter($(this).val(), 4);
        });
    });
</script>

<div class="content">
    <div class="blocksection">
        <h2>Daftar Seluruh User</h2>
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="datatables" width="100%">
            <thead>
            <tr>
                <th>Username</th>
                <th>Nama</th>
                <th>No Rekening</th>
                <th>Nama Rekening</th>
                <th>Jumlah Transfer</th>
                <th>Paket</th>
                <th>Tanggal Bergabung</th>
                <th>Tanggal Aktivasi</th>
                <th>Status</th>
                <th style="width:120px;">Action</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="10" class="dataTables_empty">Loading data from server</td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="10">
                    <label for="statusFilter">Filter Status:</label>
                    <select id="statusFilter">
                        <option value="ACTIVE">Active</option>
                        <option value="INACTIVE" selected="selected">Inactive</option>
                        <option value="BLOCKED">Blocked</option>
                        <option value="EXPIRED">Expired</option>
                        <option value="ALL">All</option>
                    </select>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
