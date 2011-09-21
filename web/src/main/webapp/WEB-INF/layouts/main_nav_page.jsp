<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="mainnav">
    <ul>
        <li>
            <a href="<c:url value="/"/>" class="parent">Home</a>
        </li>
        <li>
            <a href="#" class="parent">Perusahaan</a>
            <ul class="zero pop-nav">
                <li><a href="<c:url value='/page/1'/>">Profil</a>
                <li><a href="<c:url value='/page/2'/>">Management</a>
            </ul>
        </li>
        <li>
            <a href="<c:url value='/page/3' />" class="parent">Dasar Hukum</a>
        </li>
        <li>
            <a href="#" class="parent">Pelatihan</a>
            <ul class="zero pop-nav">
                <li><a href="<c:url value='/page/4'/>">Paket Pelatihan</a>
                <li><a href="<c:url value='/page/5'/>">Materi Pelatihan</a>
                <li><a href="<c:url value='/page/6'/>">Pendaftaran</a></li>
            </ul>
        </li>
        <li>
            <a href="<c:url value='/page/7'/>" class="parent">Investasi</a>
        </li>
        <li>
            <a href="#" class="parent">Forex Dasar</a>
            <ul class="zero pop-nav">
                <li><a href="<c:url value='/page/8'/>">Apa Itu Forex</a>
                <li><a href="<c:url value='/page/9'/>">Forex Dasar</a>
                <li><a href="<c:url value='/page/10'/>">Analisa Teknikal</a></li>
                <li><a href="<c:url value='/page/11'/>">Analisa Fundamental</a></li>
                <li><a href="<c:url value='/page/12'/>">Management Modal</a></li>
                <li><a href="<c:url value='/page/13'/>">Management Resiko</a></li>
                <li><a href="<c:url value='/page/14'/>">Management Psikologi</a></li>
                <li><a href="<c:url value='/page/15'/>">Pasar uang</a>
                <li><a href="<c:url value='/page/16'/>">Software Trading</a>
            </ul>
        </li>
        <li>
            <a href="#" class="parent">Trader Area</a>
            <ul class="zero pop-nav">
                <li><a href="<c:url value='/page/17'/>">Daftar Real Account</a>
                <li><a href="<c:url value='/page/18'/>">Deposit & Withdraw</a>
            </ul>
        </li>
        <li>
            <a href="<c:url value="/page/19"/>" class="parent">Hubungi Kami</a>
        </li>
    </ul>
</div>