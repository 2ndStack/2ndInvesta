<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content">
    <c:url value="/berita" var="berita_url"/>

    <form:form action="${berita_url}" method="POST" modelAttribute="berita">
        <div class="blocksection">
            <div class="blockcontent">
                <h3>Masukkan Berita Baru</h3>
                <dl class="form-text">
                    <dt>
                        <label for="nama">Judul :</label>
                    </dt>
                    <dd>
                        <form:input path="nama" id="nama"/>

                        <form:errors delimiter="&lt;p/&gt;" cssClass="boxinfo error"
                                     path="nama"/>
                        <p class="description">Masukkan Judul Berita.</p>
                    </dd>
                </dl>
                <dl class="form-textarea">
                    <dt>
                        <label for="isi">Isi :</label>
                    </dt>
                    <dd>
                        <form:textarea path="isi" id="isi" cols="10"/>

                        <form:errors delimiter="&lt;p/&gt;" cssClass="boxinfo error"
                                     path="isi"/>
                        <p class="description">Masukkan Detail Berita.</p>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="form-button">
            <div class="buttonWrapper">
                <a href="<c:url value='/berita'/>" class="back">Kembali</a>
            </div>
            <input  onclick="return confirm('Apakah anda Yakin ?')"  type="submit" value="Simpan" name="submit_1" id="submit_1"/>
            <input class="grey" type="reset" value="Hapus" name="reset_1" id="reset_1"/>
        </div>
    </form:form>
</div>
