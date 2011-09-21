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
    <link rel="stylesheet" href="<c:url value="/resources/css/added.css"/>" type="text/css"/>
    <link rel="icon" type="image/vnd.microsoft.icon" href="<c:url value="/resources/images/favicon.ico"/>"/>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/favicon.ico"/>"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.5.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/imgpreload.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/initstyle.js"/>"></script>
    <link href="http://fonts.googleapis.com/css?family=Cuprum&subset=latin" rel="stylesheet" type="text/css"/>
    <tiles:insertAttribute name="scripts" ignore="true"/>
</head>

<body>
<%--<tiles:insertAttribute name="body" ignore="false"/>--%>
<div id="bodyout">
    <div class="container">
        <div id="header">
            <h1><a href="#">Mandiri Investa</a></h1>
            <tiles:insertAttribute name="mainNav" ignore="false"/>
            <tiles:insertAttribute name="secNav" ignore="false"/>
        </div>
        <div id="wrapper">
            <div id="contentwrap">
                <div id="breadcrumb">
                    <%--<a href="#">Home</a> <span>/</span> <a href="#">Products</a>--%>
                </div>
                <div id="page-title">
                    <h1 class="icon gear">Mandiri<span>Investa</span></h1>

                    <div class="clearfix"></div>
                </div>
                <%--<div id="page-top">
                            <div class="video">
                                <img src="<c:url value="/resources/images/content/hero-extjs.png"/>" alt="Ext JS" />
                            </div>
                            <h2>Amazing cross-browser app experiences</h2>
                            <p>
                                Ext JS is the developer's choice for building powerful desktop web applications using JavaScript and web standards.
                            </p>

                            <div class="clearfix"></div>
                        </div>--%>
                <div id="page-content">
                    <tiles:insertAttribute name="contentTab" ignore="true"/>
                    <tiles:insertAttribute name="body" ignore="false"/>
                    <%--  <div class="content-tab">
                        <ul>
                            <li class="active"><a href="#">Overview</a></li>
                            <li><a href="#">Samples &amp; Demos</a></li>
                            <li><a href="#">Themes</a></li>
                            <li><a href="#">Testimonials</a></li>
                            <li><a href="#">Road Map</a></li>
                            <li><a href="#">Licensing</a></li>
                        </ul>
                    </div>--%>
                    <%--<div class="columns two"> <!-- one column: <div class="columns"> -->--%>
                    <!-- three columns: <div class="columns three"> -->
                    <div class="column">
                        <%--<div class="content">
                            <h3>Ext JS Overview</h3>

                            <p>
                                With Ext JS, you can develop pixel-perfect, cross-platform web apps with ease.
                                Whether you're a single developer or a development team, the Ext JS component
                                model keeps your code well structured so even the largest applications can be
                                easily maintained. And other developers' components plug-in seamlessly. Best of
                                all, Ext JS provides an encyclopedic-collection of user interface widgets with
                                a elegant starting theme. These are just a few of the reasons why Ext JS is the
                                leading choice of developers everywhere.
                            </p>

                            <h3>Rich Modern UI Widgets</h3>

                            <p>
                                Ext JS offers an extraordinary range of user interface widgets. High performance
                                scalable grids, trees, menus, and more. If you need it, we've probably already
                                built it. And even if it's not in the core library, you're almost certain to find
                                it in the thousands of user extensions from our million-strong Sencha community.
                            </p>

                            <h3>Professional Documentation, Training and Support</h3>

                            <p>
                                Our <a href="#">learning resources</a> and <a href="#">API documentation</a> are
                                comprehensive, detailed and regularly maintained. We also offer several options
                                for <a href="#">professional training</a> at multiple levels including beginner
                                and advanced. We also offer <a href="#">professional support</a> with guaranteed
                                response times and a full ticketing system.
                            </p>
                        </div>--%>
                    </div>
                    <%--<div class="column">
                        <div class="content">
                            <h3>Clean Component Model</h3>

                            <p>
                                A core strength of Ext JS is its component design. You can easily extend the default
                                components to meet your needs, and extensions will be encapsulated within just those
                                components. As a result, your development teams can develop even the largest
                                applications
                                without stepping on each other's code.
                            </p>

                            <h3>Cross Platform</h3>

                            <p>
                                Whether it's IE6 or the latest Chrome browser, Ext JS applications look and behave
                                the
                                same no matter where they run. Ext JS fully utilizes browser specific technologies
                                to
                                provide a consistent, high performance user experience. On IE6, it makes extensive
                                use
                                of images for theming and IE specific technologies for data handling. On the latest
                                modern
                                browsers, the same effects are accomplished with HTML5 and CSS3 styling.
                            </p>

                            <h3>Browser Compatibility</h3>

                            <p>
                                Ext JS supports all major web browsers including:
                            </p>
                            <ul>
                                <li>Internet Explorer 6+</li>
                                <li>FireFox 1.5+ (PC, Mac)</li>
                                <li>Safari 3+</li>
                                <li>Chrome 3+</li>
                                <li>Opera 9+ (PC, Mac)</li>
                            </ul>
                            <p>
                                <img src="<c:url value="/resources/images/content/browser/safari.png"/>" alt=""/>
                                <img src="<c:url value="/resources/images/content/browser/firefox.png"/>" alt=""/>
                                <img src="<c:url value="/resources/images/content/browser/ie.png"/>" alt=""/>
                                <img src="<c:url value="/resources/images/content/browser/chrome.png"/>" alt=""/>
                                <img src="<c:url value="/resources/images/content/browser/opera.png"/>" alt=""/>
                            </p>
                        </div>
                    </div>--%>
                    <div class="clearfix"></div>
                    <%--</div>--%>
                    <%--<div class="pagination">
                                 <a href="#" class="buttonlink grey arrow"><span>Samples &amp; Demos</span></a>
                                 <ul>
                                     <li><a href="#" class="jump">First</a></li>
                                     <li><a href="#">&#171;</a></li>
                                     <li class="selected"><a href="#">1</a></li>
                                     <li><a href="#">2</a></li>
                                     <li><a href="#">3</a></li>
                                     <li><a href="#">4</a></li>
                                     <li><a href="#">5</a></li>
                                     <li><a href="#">&#187;</a></li>
                                     <li><a href="#" class="jump">Last</a></li>
                                 </ul>
                             </div>--%>
                </div>
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
                <%--<p>
                            <a href="#" class="linkmore">Find Sencha developers at SenchaDevs</a>
                        </p>--%>
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


<%--   <div>
    <script type="text/javascript" src="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>

    <!-- Wrapping the Recaptcha create method in a javascript function -->
    <script type="text/javascript">
        function showRecaptcha(element) {
            Recaptcha.create("6LcP778SAAAAAGMSw0RNg0gp1qWzUFb2ClVo6r5V", element, {
                theme: "red",
                callback: Recaptcha.focus_response_field});
        }
        $(document).ready(function() {
            showRecaptcha('recaptcha_div');
        });
    </script>
    <div id="recaptcha_div"></div>
</div>--%>
