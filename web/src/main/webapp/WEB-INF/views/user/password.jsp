<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="content">
    <spring:url value='/user/password' var="submit_url"/>

    <form id="formPassword" method="post" action="${submit_url}">
        <div class="blocksection">
            <div class="blockcontent">

                <h3>Ubah Password</h3>
                <dl class="form-text">
                    <dt>Password Sekarang</dt>
                    <dd>
                        <input type="password" name="currentPassword"/>
                    </dd>
                    <dt>Password Baru</dt>
                    <dd>
                        <input type="password" name="newPassword" id="newPassword"/>
                    </dd>
                    <dt>Konfirmasi Password</dt>
                    <dd>
                        <input type="password" name="confirmPassword" id="confirmPassword"/>
                    </dd>
                </dl>
            </div>
        </div>

        <div class="form-button">
            <input type="submit" value="Ubah" name="submit_1" id="submit_1"/>
            <input class="grey" type="reset" value="Hapus" name="reset_1" id="reset_1"/>
        </div>
    </form>
</div>
