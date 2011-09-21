<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US">
<head>
    <title>Mandiri Investa</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/reset.css"/>" type="text/css"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" type="text/css"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/menu.css"/>" type="text/css"/>
    <link rel="icon" type="image/vnd.microsoft.icon" href="<c:url value="/resources/images/favicon.ico"/>"/>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/favicon.ico"/>"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.3.2.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/imgpreload.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/initstyle.js"/>"></script>
    <link href="http://fonts.googleapis.com/css?family=Cuprum&subset=latin" rel="stylesheet" type="text/css"/>
    <!-- homepage slider -->
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.jcarousel.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.effects.core.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/carousel.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/slider.css"/>" type="text/css"/>
</head>
<body>
<div id="bodyout">
    <div class="container">
        <div id="header">
            <h1><a href="#">Sencha</a></h1>
            <tiles:insertAttribute name="mainNav" ignore="false"/>
            <tiles:insertAttribute name="secNav" ignore="false"/>
        </div>
        <div id="wrapper">
            <div id="slidewrap">
                <div id="slidecarousel" class="jcarousel">
                    <div class="jcarousel-control"><span>Informasi Lain:</span></div>
                    <ul>
                        <li>
                            <div class="slide-img">
                                <img src="<c:url value="/resources/images/content/slide/extjs4-preview.jpg"/>"
                                     width="960" alt=""/>
                            </div>
                            <div class="slide-content">
                                <h2>Selamat Datang</h2>

                                <p>
                                    PT.Cahaya Forex Yogyakarta adalah Bisnis Investasi dan Pelatihan Online Trading
                                    dengan Akte pendirian No. 072/Adv-AMS/XII/2010 dan disupport oleh Bp. Amaluddin M.S,
                                    SH, MH, sebagai Kuasa Hukum tetap PT.Cahaya Forex Yogyakarta.
                                </p>

                                <p class="slidebtn">
                                    <a href="<c:url value="/page/1"/>" class="buttonlink small">Selanjutnya</a>
                                    <%--<a href="#" class="buttonlink small grey">Pre-Order</a>--%>
                                </p>
                            </div>
                        </li>
                        <li>
                            <div class="slide-img">
                                <img src="<c:url value="/resources/images/content/slide/_fpo-feature.jpg"/>" width="960"
                                     alt=""/>
                            </div>
                            <div class="slide-content">
                                <h2>Program Investasi</h2>

                                <p>
                                    Cahaya Forex Yogyakarta BUKAN program bisnis dengan sistem
                                    TAHAPAN. Dana investasi Anda murni kami kelola di bisnis FOREX.
                                </p>

                                <%--<p class="slidebtn">--%>
                                <%--<a href="#" class="buttonlink small">Download Now</a> or <a href="#"--%>
                                <%--class="buttonlink small grey">Learn--%>
                                <%--More</a>--%>
                                <%--</p>--%>
                            </div>
                        </li>
                        <li>
                            <div class="slide-img">
                                <img src="<c:url value="/resources/images/content/slide/conf-videos.jpg"/>" width="960"
                                     alt=""/>
                            </div>
                            <div class="slide-content">
                                <h2>Mari Begabung</h2>

                                <p>
                                    Kami akan memberikan sharing profit BULANAN (Pasive Income)dengan target profit 15 -
                                    35 % (Cabang-Cabang) per bulan dari investasi anda.
                                </p>

                                <p class="slidebtn">
                                    <a href="<c:url value="/register"/>" class="buttonlink small">Daftar</a>
                                </p>
                            </div>
                        </li>
                    </ul>
                    <div id="slide-mask"></div>
                </div>
            </div>
            <div id="homewrap">
                <tiles:insertAttribute name="body" ignore="false"/>

            </div>
            <div id="logowrap">
                <h4><em>Beberapa Cabang</em> yang Kami Miliki.</h4>
                <ul>
                    <li><img src="<c:url value="/resources/images/content/logo/customer-merrill-lynch.png"/>"
                             alt="Merrill Lynch"/></li>
                    <li><img src="<c:url value="/resources/images/content/logo/customer-mckesson.png"/>"
                             alt="McKesson"/></li>
                    <li><img src="<c:url value="/resources/images/content/logo/cnn.png"/>" alt="CNN"/></li>
                    <li><img src="<c:url value="/resources/images/content/logo/hsbc.png"/>" alt="HSBC"/></li>
                    <li><img src="<c:url value="/resources/images/content/logo/cisco.png"/>" alt="Cisco"/></li>
                    <li><img src="<c:url value="/resources/images/content/logo/adobe.png"/>" alt="Adobe"/></li>
                    <li><img src="<c:url value="/resources/images/content/logo/customer-visa.png"/>" alt="Visa"/></li>
                    <li><img src="<c:url value="/resources/images/content/logo/customer-samsung.png"/>" alt="Samsung"/>
                    </li>
                </ul>
                <%--<a href="#" class="linkmore">Lihat Cabang-cabang lainya</a>--%>
            </div>
            <div id="bottomwrap">
                <p>
                    <em>PT. Cahaya Forex Yogyakarta memiliki 11 kantor / website cabang.</em>
                    Mari Bergabung bersama kami.
                </p>
                <ul class="social">
                    <li><a href="#" class="facebook">Facebook</a></li>
                    <li><a href="#" class="linkedin">LinkedIn</a></li>
                    <li><a href="#" class="twitter">Twitter</a></li>
                    <li><a href="#" class="tumblr">Tumblr</a></li>
                    <li><a href="#" class="vimeo">Vimeo</a></li>
                    <li><a href="#" class="rss">RSS Feed</a></li>
                </ul>
                <p><cite>or</cite> <a href="<c:url value="/register"/> " class="buttonlink">Daftar</a></p>

                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</div>
<div id="footerout">
    <div class="container">
        <div id="footer">
            <div id="footer-nav">
                <ul>
                    <%--<li><a href="#">Home</a></li>--%>
                    <%--<li><a href="#">Products</a></li>--%>
                    <%--<li><a href="#">Support</a></li>--%>
                    <%--<li><a href="#">Training</a></li>--%>
                    <%--<li><a href="#">Careers</a></li>--%>
                    <%--<li><a href="#">Company</a></li>--%>
                    <%--<li><a href="#">Blog</a></li>--%>
                    <%--<li><a href="#">Store</a></li>--%>
                    <%--<li><a href="#">Contact</a></li>--%>
                    <%--<li><a href="#">Legal</a></li>--%>
                </ul>
                <p>
                    <%--<a href="#" class="linkmore">Find Sencha developers at SenchaDevs</a>--%>
                </p>
            </div>
            <div id="copyright">
                &copy; Copyright 2011 <a href="http://www.mandiriinvesta.com">MandiriInvesta</a>
                <br/>
                Developed by <a href="http://www.jasoet.com">Jasoet.com</a>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
</body>
</html>