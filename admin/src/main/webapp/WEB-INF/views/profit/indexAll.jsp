<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="<c:url value='/resources/js/jquery.dataTables.js'/>" type="text/javascript"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/table.css"/>" type="text/css"/>
<c:url value="/profit/${namaBank}" var="profit_url"/>
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
            "sAjaxSource": "<c:url value='/profit/${namaBank}/index.json' />",
            "sPaginationType": "full_numbers",
            "aoColumnDefs": [
                {
                    "fnRender": function (oObj) {
                        var statusLink = "";
                        if (oObj.aData[3] == "PENDING") {
                            statusLink = "<a  onclick=\"return confirm(\'Apakah anda Yakin ?\')\"  class='view' href='${profit_url}/" + oObj.aData[5] + "/COMPLETED'>Komplit</a>";
                            statusLink += "<a  onclick=\"return confirm(\'Apakah anda Yakin ?\')\"  class='view' href='${profit_url}/" + oObj.aData[5] + "/edit'>Edit</a>";

                        } else if (oObj.aData[3] == "COMPLETED") {
                            statusLink = "<a  onclick=\"return confirm(\'Apakah anda Yakin ?\')\"  class='view' href='${profit_url}/" + oObj.aData[5] + "/PENDING'>Pending</a>";
                            statusLink += "<a  onclick=\"return confirm(\'Apakah anda Yakin ?\')\"  class='view' href='${profit_url}/" + oObj.aData[5] + "/edit'>Edit</a>";
                        } else if (oObj.aData[3] == "FAILED") {
                            statusLink = "<a  onclick=\"return confirm(\'Apakah anda Yakin ?\')\"  class='view' href='${profit_url}/" + oObj.aData[5] + "/COMPLETED'>Komplit</a>";
                            statusLink += "<a  onclick=\"return confirm(\'Apakah anda Yakin ?\')\"  class='view' href='${profit_url}/" + oObj.aData[5] + "/edit'>Edit</a>";
                        } else {
                            statusLink = "<a  onclick=\"return confirm(\'Apakah anda Yakin ?\')\"  class='view' href='${profit_url}/" + oObj.aData[5] + "/edit'>Edit</a>";
                        }
                        return "<div class='buttonWrapper'>" +
                                statusLink +
                                "</div>";
                    },
                    "aTargets": [5]
                } ,
                {
                    "fnRender": function (oObj) {
                        var result = "<div style='text-align:right;'>" + addSparator(oObj.aData[2]) + "</div> ";
                        return result;
                    },
                    "aTargets": [2]
                },
                { "bSortable": false, "aTargets": [0] },
                { "bSortable": false, "aTargets": [1] },
                { "bSortable": false, "aTargets": [2] },
                { "bSortable": false, "aTargets": [3] },
                { "bSortable": false, "aTargets": [4] },
                { "bSortable": false, "aTargets": [5] }
            ]
        });
        $("#statusFilter").change(function() {
            oTable.fnFilter($(this).val(), 3);
        });
        $("#bulanFilter").change(function() {
            var date = $("#tahunFilter").val() + "-" + $(this).val();
            oTable.fnFilter(date, 1);
        });
        $("#tahunFilter").change(function() {
            var date = $(this).val() + "-" + $("#bulanFilter").val();
            oTable.fnFilter(date, 1);
        });
    });
</script>

<%--NO--%>
<%--USERNAME--%>
<%--NAMA--%>
<%--BANK--%>
<%--REKENING--%>
<%--PROFIT--%>
<%--NETT PROFIT--%>

<div class="content">
    <div class="blocksection">
        <h2 style="text-align:center;">Daftar Profit Bank ${namaBank}</h2>
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="datatables" width="100%">
            <thead>
            <tr>
                <th>Username</th>
                <th>Bulan</th>
                <th style="text-align:right;">Jumlah (Rp.)</th>
                <th>Status</th>
                <th>Keterangan</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="6" class="dataTables_empty">Loading data from server</td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="2">
                    <label for="bulanFilter">Filter Bulan:</label>
                    <select id="bulanFilter">
                        <option value="0" ${bulanSekarang==0?'selected="selected"':''}>Januari</option>
                        <option value="1" ${bulanSekarang==1?'selected="selected"':''}>Februari</option>
                        <option value="2" ${bulanSekarang==2?'selected="selected"':''}>Maret</option>
                        <option value="3" ${bulanSekarang==3?'selected="selected"':''}>April</option>
                        <option value="4" ${bulanSekarang==4?'selected="selected"':''}>Mei</option>
                        <option value="5" ${bulanSekarang==5?'selected="selected"':''}>Juni</option>
                        <option value="6" ${bulanSekarang==6?'selected="selected"':''}>Juli</option>
                        <option value="7" ${bulanSekarang==7?'selected="selected"':''}>Agustus</option>
                        <option value="8" ${bulanSekarang==8?'selected="selected"':''}>September</option>
                        <option value="9" ${bulanSekarang==9?'selected="selected"':''}>Oktober</option>
                        <option value="10" ${bulanSekarang==10?'selected="selected"':''}>November</option>
                        <option value="11" ${bulanSekarang==11?'selected="selected"':''}>Desember</option>
                    </select>
                    <select id="tahunFilter">
                        <c:forEach var="tahun" items="${listTahun}">
                            <option value="${tahun}" ${tahun==tahunSekarang?'selected="selected"':''}>${tahun}</option>
                        </c:forEach>
                    </select>
                </td>
                <td colspan="2">
                    <label for="statusFilter">Filter Status:</label>
                    <select id="statusFilter">
                        <option value="COMPLETED" selected="selected">COMPLETED</option>
                        <option value="PENDING">PENDING</option>
                        <option value="FAILED">FAILED</option>
                        <option value="OTHER">OTHER</option>
                    </select>
                </td>
                <td>
                    <div class="buttonWrapper">
                        <a href="<c:url value='/profit/${namaBank}/masal'/>" class="back">Edit Masal</a>
                    </div>
                </td>
                <td>
                    <div class="buttonWrapper">
                        <a href="<c:url value='/profit/${namaBank}/laporan'/>" class="back">Cetak By Member</a><br/>
                        <a href="<c:url value='/profit/${namaBank}/laporanrek'/>" class="edit">Cetak By Rekening</a>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>

</div>
