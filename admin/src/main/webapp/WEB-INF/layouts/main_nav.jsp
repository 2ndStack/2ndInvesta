<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="mainnav">
    <ul>
        <li class="${mainNav == 'home' ? 'active' : 'none'}">
            <a href="<c:url value='/dashboard'/>">Home</a>
        </li>
        <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_STAFF">
            <li class="${mainNav == 'master' ? 'active' : 'none'}">
                <a style="cursor:pointer;" class="parent">Master Data</a>
                <ul>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_STAFF">
                    <li><a href="<c:url value='/berita'/>">Berita</a>
                    <li><a href="<c:url value='/page'/>">Page</a>
                        </security:authorize>
                        <security:authorize ifAnyGranted="ROLE_ADMIN">
                    <li><a href="<c:url value='/staff'/>">Staff</a></li>
                    <li><a href="<c:url value='/user'/>">Member</a></li>
                    <li><a href="<c:url value='/paket'/>">Paket</a></li>
                    <li><a href="<c:url value='/role'/>">Role</a></li>
                    <li><a href="<c:url value='/bank'/>">Bank</a></li>
                    </security:authorize>
                </ul>
            </li>
        </security:authorize>
        <security:authorize
                ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_MANDIRI,ROLE_FINANCE_BCA,ROLE_FINANCE_BNI,ROLE_FINANCE_BRI,ROLE_FINANCE_LAIN">
            <li class="${mainNav == 'profit' ? 'active' : 'none'}">
                <a style="cursor:pointer;" class="parent">Profit</a>
                <ul>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_MANDIRI">
                        <li><a href="<c:url value='/profit/mandiri'/>">Mandiri</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_BCA">
                        <li><a href="<c:url value='/profit/bca'/>">BCA</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_BNI">
                        <li><a href="<c:url value='/profit/bni'/>">BNI</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_BRI">
                        <li><a href="<c:url value='/profit/bri'/>">BRI</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_LAIN">
                        <li><a href="<c:url value='/profit/lain'/>">Bank Lain</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN">
                        <li><a href="<c:url value='/profit/manual'/>">Hitung Manual</a></li>
                    </security:authorize>
                    <security:authorize
                            ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_MANDIRI,ROLE_FINANCE_BCA,ROLE_FINANCE_BNI,ROLE_FINANCE_BRI,ROLE_FINANCE_LAIN">
                        <li><a href="<c:url value='/transfer/show'/>">Biaya Transfer</a></li>
                    </security:authorize>
                </ul>

            </li>
        </security:authorize>
        <security:authorize
                ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_MANDIRI,ROLE_FINANCE_BCA,ROLE_FINANCE_BNI,ROLE_FINANCE_BRI,ROLE_FINANCE_LAIN">
            <li class="${mainNav == 'profitblokir' ? 'active' : 'none'}">
                <a style="cursor:pointer;" class="parent">Profit Blokir</a>
                <ul>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_MANDIRI">
                        <li><a href="<c:url value='/profit/mandiri'/>">Mandiri</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_BCA">
                        <li><a href="<c:url value='/profit/bca'/>">BCA</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_BNI">
                        <li><a href="<c:url value='/profit/bni'/>">BNI</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_BRI">
                        <li><a href="<c:url value='/profit/bri'/>">BRI</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_LAIN">
                        <li><a href="<c:url value='/profit/lain'/>">Bank Lain</a></li>
                    </security:authorize>
                </ul>
            </li>
        </security:authorize>
        <security:authorize
                ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_MANDIRI,ROLE_FINANCE_BCA,ROLE_FINANCE_BNI,ROLE_FINANCE_BRI,ROLE_FINANCE_LAIN">
            <li class="${mainNav == 'bonus' ? 'active' : 'none'}">
                <a style="cursor:pointer;" class="parent">Bonus</a>
                <ul>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_MANDIRI">
                        <li><a href="<c:url value='/bonus/mandiri'/>">Mandiri</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_BCA">
                        <li><a href="<c:url value='/bonus/bca'/>">BCA</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_BNI">
                        <li><a href="<c:url value='/bonus/bni'/>">BNI</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_BRI">
                        <li><a href="<c:url value='/bonus/bri'/>">BRI</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_LAIN">
                        <li><a href="<c:url value='/bonus/lain'/>">Bank Lain</a></li>
                    </security:authorize>
                </ul>
            </li>
        </security:authorize>
        <security:authorize
                ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_MANDIRI,ROLE_FINANCE_BCA,ROLE_FINANCE_BNI,ROLE_FINANCE_BRI,ROLE_FINANCE_LAIN">
            <li class="${mainNav == 'laporan' ? 'active' : 'none'}">
                <a style="cursor:pointer;" class="parent">Laporan Profit</a>
                <ul>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_MANDIRI">
                        <li><a href="<c:url value='/profit/mandiri/laporan'/>">Mandiri</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_BCA">
                        <li><a href="<c:url value='/profit/bca/laporan'/>">BCA</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_BNI">
                        <li><a href="<c:url value='/profit/bni/laporan'/>">BNI</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_BRI">
                        <li><a href="<c:url value='/profit/bri/laporan'/>">BRI</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_LAIN">
                        <li><a href="<c:url value='/profit/lain/laporan'/>">Bank Lain</a></li>
                    </security:authorize>
                </ul>
            </li>
        </security:authorize>
        <security:authorize
                ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_MANDIRI,ROLE_FINANCE_BCA,ROLE_FINANCE_BNI,ROLE_FINANCE_BRI,ROLE_FINANCE_LAIN">
            <li class="${mainNav == 'laporanRekening' ? 'active' : 'none'}">
                <a style="cursor:pointer;" class="parent">Laporan Rekening</a>
                <ul>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_MANDIRI">
                        <li><a href="<c:url value='/profit/mandiri/laporanrek'/>">Grup Mandiri</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_BCA">
                        <li><a href="<c:url value='/profit/bca/laporanrek'/>">Grup BCA</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_BNI">
                        <li><a href="<c:url value='/profit/bni/laporanrek'/>">Grup BNI</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_BRI">
                        <li><a href="<c:url value='/profit/bri/laporanrek'/>">Grup BRI</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_LAIN">
                        <li><a href="<c:url value='/profit/lain/laporanrek'/>">Grup Bank Lain</a></li>
                    </security:authorize>
                </ul>
            </li>
        </security:authorize>
        <security:authorize
                ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_MANDIRI,ROLE_FINANCE_BCA,ROLE_FINANCE_BNI,ROLE_FINANCE_BRI,ROLE_FINANCE_LAIN">
            <li class="${mainNav == 'arsipLaporan' ? 'active' : 'none'}">
                <a style="cursor:pointer;" class="parent">Arsip Laporan</a>
                <ul>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_MANDIRI">
                        <li><a href="<c:url value='/profit/mandiri/listrek'/>">Mandiri</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_BCA">
                        <li><a href="<c:url value='/profit/bca/listrek'/>">BCA</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_BNI">
                        <li><a href="<c:url value='/profit/bni/listrek'/>">BNI</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_BRI">
                        <li><a href="<c:url value='/profit/bri/listrek'/>">BRI</a></li>
                    </security:authorize>
                    <security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_FINANCE_LAIN">
                        <li><a href="<c:url value='/profit/lain/listrek'/>">Bank Lain</a></li>
                    </security:authorize>
                </ul>
            </li>
        </security:authorize>
    </ul>
</div>