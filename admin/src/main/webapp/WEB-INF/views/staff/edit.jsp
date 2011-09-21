<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="content">
    <c:url value="/staff" var="staff_url"/>

    <form:form action="${staff_url}" method="PUT" modelAttribute="staff" id="staff">
        <form:hidden path="id"/>
        <div class="blocksection">
            <div class="blockcontent">
                <h3>Masukkan Staff Baru</h3>
                <dl class="form-text">
                    <dt>
                        <label for="username">Username :</label>
                    </dt>
                    <dd>
                        <form:input path="username" id="username"/>

                        <form:errors cssClass="boxinfo error" delimiter="&lt;p/&gt;"
                                     path="username"/>
                        <p class="description">Masukkan Username.</p>
                    </dd>
                </dl>

                <dl class="form-radio">
                    <dt>
                        <label for="enabled">Status :</label>
                    </dt>
                    <dd>
                        <form:checkbox path="enabled" id="enabled"/>

                        <p class="description">Atur Status Staff </p>
                    </dd>
                </dl>
                <dl class="form-text">
                    <dt>
                        <label for="telephone">Telephone : </label>
                    </dt>
                    <dd>
                        <form:input path="telephone" id="telephone"/>

                        <form:errors cssClass="errors" delimiter="&lt;p/&gt;"
                                     path="telephone"/>
                        <p class="description">Masukkan Telephone.</p>
                    </dd>
                </dl>
                <dl class="form-check">
                    <dt>
                        <label for="roles">Hak Akses :</label>
                    </dt>
                    <dd>
                        <c:forEach var="role" items="${roles}">
                            <form:checkbox path="roles" value="${role.authority}"
                                           label="${role.authority}"/>
                        </c:forEach>

                        <p class="description">Pilih Hak Akses. </p>
                    </dd>
                </dl>
            </div>
        </div>

        <div class="form-button">
            <div class="buttonWrapper">
                <a href="<c:url value='/staff'/>" class="back">Kembali</a>
            </div>
            <input onclick="return confirm('Apakah anda Yakin ?')"  type="submit" value="Kirim" name="submit_1" id="submit_1"/>
            <input class="grey" type="reset" value="Hapus" name="reset_1" id="reset_1"/>
        </div>

    </form:form>
</div>
