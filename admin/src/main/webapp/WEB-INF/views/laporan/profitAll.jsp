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

<h1>Profit All</h1>

<div class="content">
    <div class="blockcontent">
        <h3>Filter Bulan :</h3>
        <dl class="form-select">
            <dt>
                <label for="month"></label>
            </dt>
            <dd>
                <form method="post" action="<c:url value="/laporan/profit/all"/>">
                    <select name="month" id="month">
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
                    <select name="year">
                        <c:forEach var="tahun" items="${listTahun}">
                            <option value="${tahun}" ${tahun==tahunSekarang?'selected="selected"':''}>${tahun}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="kirim"/>
                </form>
            </dd>
        </dl>
    </div>
    <br/>
    <table cellpadding="0" cellspacing="0" border="0" class="display" id="datatables" width="100%">
        <thead>
        <tr>
            <%--<th>No</th>--%>
            <th>Username</th>
            <th>Nama</th>
            <th>Bank</th>
            <th>Rekening</th>
            <th>Profit</th>
            <th>Nett Profit</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${data}" var="item">
            <tr>
                    <%--<td>${item.id}</td>--%>
                <td>${item.username}</td>
                <td>${item.nama}</td>
                <td>${item.bank}</td>
                <td>${item.rekening}</td>
                <td>${item.profit}</td>
                <td>${item.nettProfit}</td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        </tfoot>
    </table>
</div>