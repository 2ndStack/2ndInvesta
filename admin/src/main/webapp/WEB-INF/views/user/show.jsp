<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="content">
    <form action="">
        <div class="blocksection">
            <div class="blockcontent">
                <h3>Detail User</h3>
                <table cellpadding="0" cellspacing="0" border="0" width="928px" id="table_view">
                    <tbody>
                    <tr>
                        <td width="25%">Username :</td>
                        <td width="75%">${data.username}</td>
                    </tr>
                    <tr>
                        <td width="25%">Nama :</td>
                        <td width="75%">${data.nama}</td>
                    </tr>
                    <tr>
                        <td width="25%">Email :</td>
                        <td width="75%">${data.email}</td>
                    </tr>
                    <tr>
                        <td width="25%">Alamat :</td>
                        <td width="75%">${data.alamat}</td>
                    </tr>
                    <tr>
                        <td width="25%">Kota :</td>
                        <td width="75%">${data.kota}</td>
                    </tr>
                    <tr>
                        <td width="25%">Telephone :</td>
                        <td width="75%">${data.telp}</td>
                    </tr>
                    <tr>
                        <td width="25%">Nama Bank :</td>
                        <td width="75%">${data.bank.nama}</td>
                    </tr>
                    <tr>
                        <td width="25%">Atas Nama Rekening :</td>
                        <td width="75%">${data.namaRekening}</td>
                    </tr>
                    <tr>
                        <td width="25%">Nomor Rekening :</td>
                        <td width="75%">${data.nomorRekening}</td>
                    </tr>
                    <tr>
                        <td width="25%">Tanggal Bergabung :</td>
                        <td width="75%">${data.tanggalBergabung}</td>
                    </tr>
                    <tr>
                        <td width="25%">Tanggal Transfer :</td>
                        <td width="75%">${data.tanggalTransfer}</td>
                    </tr>
                    <tr>
                        <td width="25%">Nama Staff :</td>
                        <td width="75%">${(not empty data.staff) ? data.staff.username : ''}</td>
                    </tr>
                    <tr>
                        <td width="25%">Username Sponsor :</td>
                        <c:if test="${not empty data.sponsor}">
                            <td width="75%"><a href="<c:url value="/user/${data.sponsor.id}"/>">${data.sponsor.username}</a></td>
                        </c:if>
                        <c:if test="${empty data.sponsor}">
                            <td width="75%">&nbsp;</td>
                        </c:if>
                    </tr>
                    <tr>
                        <td width="25%">Jumlah Deposit :</td>
                        <td width="75%">Rp.&nbsp;<fmt:formatNumber value="${data.nilaiDeposit}" pattern="#,##0.0"/></td>
                    </tr>
                    <tr>
                        <td width="25%">Bulan Berjalan :</td>
                        <td width="75%">${(not empty bulanBerjalan) ? bulanBerjalan : '0'} &nbsp;Bulan</td>
                    </tr>
                    <tr>
                        <td width="25%">Status :</td>
                        <td width="75%">${data.status}</td>
                    </tr>
                    </tbody>
                </table>
                <div class="form-agree buttonWrapper">
                    <a href="<c:url value='/user' />" class="back">Kembali
                    </a>
                    <a href="<c:url value='/user/${data.id}/edit' />" class="edit">Ubah</a>
                </div>
            </div>
        </div>
    </form>
</div>