<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="content">
    <c:url value="/bank" var="bank_url"/>

    <form:form action="${bank_url}" method="POST" modelAttribute="bank">
        <div class="blocksection">
            <div class="blockcontent">
                <h3>Masukkan Data Bank Baru</h3>
                <dl class="form-text">
                    <dt>
                        <label for="nama">Nama :</label>
                    </dt>
                    <dd>
                        <form:input path="nama" id="nama"/>
                            <%--
                            <div class="boxinfo">
                                your info text here...
                            </div>
                            <div class="boxinfo success">
                                Username is valid and not in use.
                            </div>--%>
                        <form:errors delimiter="&lt;p/&gt;" cssClass="boxinfo error"
                                     path="nama"/>
                        <p class="description">Masukkan Nama Bank.</p>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="form-button">
            <div class="buttonWrapper">
                <a href="<c:url value='/bank'/>" class="back">Kembali</a>
            </div>
            <input  onclick="return confirm('Apakah anda Yakin ?')"  type="submit" value="Kirim" name="submit_1" id="submit_1"/>
            <input class="grey" type="reset" value="Hapus" name="reset_1" id="reset_1"/>
        </div>
    </form:form>
</div>