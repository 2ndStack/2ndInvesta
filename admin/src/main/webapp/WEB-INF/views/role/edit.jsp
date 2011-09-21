<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="content">
    <c:url value="/role" var="role_url"/>

    <form:form action="${role_url}" method="PUT" modelAttribute="role">
        <div class="blocksection">
            <div class="blockcontent">
                <h3>Masukkan Role Baru</h3>
                <dl class="form-text">
                    <dt>
                        <label for="authority">Nama :</label>
                    </dt>
                    <dd>
                        <form:input path="authority" id="authority"/>

                        <form:errors cssClass="boxinfo error" delimiter="&lt;p/&gt;"
                                     path="authority"/>
                        <p class="description">Masukkan Authority.</p>
                    </dd>
                </dl>
            </div>
        </div>

        <div class="form-button">
            <div class="buttonWrapper">
                <a href="<c:url value='/role'/>" class="back">Kembali</a>
            </div>
            <input type="submit" value="Kirim" name="submit_1" id="submit_1"/>
            <input class="grey" type="reset" value="Hapus" name="reset_1" id="reset_1"/>
        </div>

    </form:form>
</div>
