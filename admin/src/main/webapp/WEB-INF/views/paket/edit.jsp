<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="content">
    <c:url value="/paket" var="paket_url"/>

    <form:form action="${paket_url}" method="PUT" modelAttribute="paket">
        <form:hidden path="id"/>
        <div class="blocksection">
            <div class="blockcontent">
                <h3>Edit Paket</h3>
                <dl class="form-text">
                    <dt>
                        <label for="nama">Nama :</label>
                    </dt>
                    <dd>
                        <form:input path="nama" id="nama"/>

                        <form:errors cssClass="boxinfo error" delimiter="&lt;p/&gt;"
                                     path="nama"/>
                        <p class="description">Masukkan Nama Paket.</p>
                    </dd>
                </dl>
                <dl class="form-text">
                    <dt>
                        <label for="nama">Harga :</label>
                    </dt>
                    <dd>
                        <form:input path="harga" id="inputHarga" cssClass="text-medium"/>

                        <form:errors cssClass="boxinfo error" delimiter="&lt;p/&gt;"
                                     path="harga"/>
                        <p class="description">Masukkan Harga Paket.</p>
                    </dd>
                </dl>
                <dl class="form-radio">
                    <dt>
                        <label for="enabled">Status :</label>
                    </dt>
                    <dd>
                        <form:checkbox path="enabled" id="enabled"/>
                        <label for="enabled">Aktif</label>

                        <p class="description">Atur Status Paket </p>
                    </dd>
                </dl>
            </div>
        </div>

        <div class="form-button">
            <div class="buttonWrapper">
                <a href="<c:url value='/paket'/>" class="back">Kembali</a>
            </div>
            <input onclick="return confirm('Apakah anda Yakin ?')" type="submit" value="Kirim" name="submit_1" id="submit_1"/>
            <input class="grey" type="reset" value="Hapus" name="reset_1" id="reset_1"/>
        </div>

    </form:form>
</div>