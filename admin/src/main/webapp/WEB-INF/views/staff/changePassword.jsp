<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="content">
    <c:url value="/staff/${staff.id}" var="staff_url"/>
    <form:form action="${staff_url}" method="PUT" id="changePasswordStaff" commandName="staff">
        <input type="hidden" name="id" value="${staff.id}"/>

        <div class="blocksection">
            <div class="blockcontent">
                <h3>Ubah Password</h3>
                <dl class="form-text">
                    <dt>
                        <label for="currentPassword">Password Lama :</label>
                    </dt>
                    <dd>
                        <input type="password" id="currentPassword" name="currentPassword"/>

                        <p class="description">Masukkan Password Lama.</p>
                    </dd>
                </dl>
                <dl class="form-text">
                    <dt>
                        <label for="newPassword">Password Baru :</label>
                    </dt>
                    <dd>
                        <input type="password" id="newPassword" name="newPassword"/>

                        <p class="description">Masukkan Password Baru.</p>
                    </dd>
                </dl>
                <dl class="form-text">
                    <dt>
                        <label for="confirmPassword">Konfirmasi Password :</label>
                    </dt>
                    <dd>
                        <input type="password" id="confirmPassword" name="confirmPassword"/>

                        <p class="description">Ulangi Password baru anda.</p>
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
