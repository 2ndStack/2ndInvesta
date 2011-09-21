<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
    div.border-bottom {
        padding-bottom: 5px;
        border-bottom: 2px solid #DDDDDD;
        margin-bottom: 35px;

    }

    a.daftarBerita {
        font-size: 14px;
        margin-bottom: 5px;
        margin-top: 5px;
    }

    ul.listberita {
        list-style-type: disc;
        padding-left: 10px;
    }
</style>


<div id="homewrap-content">
    <div class="column left">
        <ul class="border-bottom">
            <h3>Rekening Investasi</h3>
            <li class="iconlist bca">
                20.303030.86<br/>
                Nur S Cahyo
            </li>
            <li class="iconlist bni">
                0192389936<br/>
                Nur S Cahyo, A.Md
            </li>
            <li class="iconlist mandiri">
                131.000.7969.563<br/>
                Nur S Cahyo, A.Md
            </li>
        </ul>
        <ul class="border-bottom">
            <h3>Rekening Pelatihan</h3>
            <li class="iconlist bca">
                2030.29.3838 <br/>
                Nur S Cahyo
            </li>
            <li class="iconlist mandiri">
                131.000.7881.727 <br/>
                Nur S Cahyo, A.Md
            </li>
        </ul>
        <ul class="border-bottom">
            <h3>Rekening Kas Cabang</h3>
            <li class="iconlist bca">
                8465019171 <br/>
                Anton Dwianto
            </li>
            <li class="iconlist mandiri">
                137.00.0753629.1 <br/>
                Anton Dwianto
            </li>
        </ul>
        <ul>
            <h3>Rekening Dana Sosial</h3>
            <li class="iconlist bca">
                1260580601<br/>
                Barli Purwanto Amd
            </li>
            <li class="iconlist mandiri">
                1370007553320<br/>
                Barli Purwanto Amd
            </li>
        </ul>
    </div>

    <div class="column middle">
        <c:if test="${not empty listBerita}">
            <h3 style="text-align:center;"><a
                    href="<c:url value="/berita/${listBerita[0].id}"/>">${listBerita[0].nama}</a></h3>

            <p class="border-bottom">${listBerita[0].isi}</p>

            <div class="border-bottom"></div>
        </c:if>

        <c:if test="${not empty front1}">
            <%--=============================================================================================--%>
            <h3 style="text-align:center;"><a href="<c:url value="/page/${front1.id}"/>">${front1.nama}</a></h3>

            <p>${front1.isi}</p>

            <div class="border-bottom"></div>
        </c:if>
        <c:if test="${not empty front2}">
            <%--=============================================================================================--%>
            <h3 style="text-align:center;"><a href="<c:url value="/page/${front2.id}"/>">${front2.nama}</a></h3>

            <p>${front2.isi}</p>

            <div class="border-bottom"></div>
        </c:if>
        <c:if test="${not empty front3}">
            <%--=============================================================================================--%>
            <h3 style="text-align:center;"><a href="<c:url value="/page/${front3.id}"/>">${front3.nama}</a></h3>

            <p>${front3.isi}</p>

            <div class="border-bottom"></div>
        </c:if>
        <c:if test="${not empty front4}">
            <%--=============================================================================================--%>
            <h3 style="text-align:center;"><a href="<c:url value="/page/${front4.id}"/>">${front4.nama}</a></h3>

            <p>${front4.isi}</p>

            <div class="border-bottom"></div>
        </c:if>
        <c:if test="${not empty front5}">
            <%--=============================================================================================--%>
            <h3 style="text-align:center;"><a href="<c:url value="/page/${front5.id}"/>">${front5.nama}</a></h3>

            <p>${front5.isi}</p>

            <div class="border-bottom"></div>
        </c:if>
        <c:if test="${not empty front6}">
            <%--=============================================================================================--%>
            <h3 style="text-align:center;"><a href="<c:url value="/page/${front6.id}"/>">${front6.nama}</a></h3>

            <p>${front6.isi}</p>

            <div class="border-bottom"></div>
        </c:if>
        <c:if test="${not empty front7}">
            <%--=============================================================================================--%>
            <h3 style="text-align:center;"><a href="<c:url value="/page/${front7.id}"/>">${front7.nama}</a></h3>

            <p>${front7.isi}</p>

            <div class="border-bottom"></div>
        </c:if>
        <c:if test="${not empty front8}">
            <%--=============================================================================================--%>
            <h3 style="text-align:center;"><a href="<c:url value="/page/${front8.id}"/>">${front8.nama}</a></h3>

            <p>${front8.isi}</p>

            <div class="border-bottom"></div>
        </c:if>
        <c:if test="${not empty front9}">
            <%--=============================================================================================--%>
            <h3 style="text-align:center;"><a href="<c:url value="/page/${front9.id}"/>">${front9.nama}</a></h3>

            <p>${front9.isi}</p>

            <div class="border-bottom"></div>
        </c:if>
        <c:if test="${not empty front10}">
            <%--=============================================================================================--%>
            <h3 style="text-align:center;"><a href="<c:url value="/page/${front10.id}"/>">${front10.nama}</a></h3>

            <p>${front10.isi}</p>

            <div class="border-bottom"></div>
        </c:if>
    </div>
    <div class="column right">

        <ul class="border-bottom">
            <h3>Real Trading</h3>
            <li class="iconup mf1">
                <a href="https://cabinet.masterforex.org/a/?aid=8852">Membuka Account Real Trading</a>

            </li>
            <li class="iconup mf2">
                <a href="http://mandiriinvesta.com/file/mftrader4setup.exe">Download Metatrader 4</a>
            </li>
            <li class="iconup metatrade">
                <a href="http://www.masterforex.org/id/?aid=12278">Membuka Account Kompetisi Trading</a>
            </li>
        </ul>

        <c:if test="${not empty listBerita}">
            <h3>Berita Forex</h3>
            <ul class="listberita border-bottom">

                <br/>
                <c:forEach var="lberita" items="${listBerita}">
                    <li>
                        <a class="daftarBerita" href="<c:url value='/berita/${lberita.id}'/>">${lberita.nama}</a>
                    </li>
                </c:forEach>
                <br/>
            </ul>
        </c:if>
        <ul>
            <h3>Cabang Mandiri Investa</h3>
            <li class="iconlist noimage">
                <a href="http://cahayainvesta.com">CahayaInvesta</a>
                Jaenal M <br/>
                082127566338
            </li>
            <li class="iconlist noimage">
                <a href="http://satriainvesta.com">SatriaInvesta</a>
                Rudy Budiman <br/>
                083862421158
            </li>
            <li class="iconlist noimage">
                <a href="http://multiinvesta.com">MultiInvesta</a>
                Feri S <br/>
                085228383888
            </li>
            <li class="iconlist noimage">
                <a href="http://Investaraya.com">Investaraya</a>
                S. Pramayuda <br/>
                085249069999
            </li>
            <li class="iconlist noimage">
                <a href="http://Master-investa.com">Master-Investa</a>
                S. Pramayuda <br/>
                085249069999
            </li>
            <li class="iconlist noimage">
                <a href="http://Temanggunginvesta.com">Temanggunginvesta</a>
                Luluk Kafiyudin <br/>
                085867433750
            </li>
            <li class="iconlist noimage">
                <a href="http://Bumipalainvesta.com">Bumipalainvesta</a>
                Himawan Z <br/>
                081392204118
            </li>
            <li class="iconlist noimage">
                <a href="http://Nusantarainvesta.com">Nusantarainvesta</a>
                Andi swasono <br/>
                081272145999
            </li>
            <li class="iconlist noimage">
                <a href="http://Jatiminvesta.com">Jatiminvesta</a>
                Asriah Arifianti <br/>
                085736331100
            </li>
            <li class="iconlist noimage">
                <a href="http://Baliinvesta.com">Baliinvesta</a>
                Ign Made Winata<br/>
                082143222258
            </li>
            <li class="iconlist noimage">
                <a href="http://Bengawaninvesta.com">Bengawaninvesta</a>
                Ichwanto<br/>
                081227715588
            </li>
        </ul>
    </div>

    <div class="clearfix"></div>
</div>