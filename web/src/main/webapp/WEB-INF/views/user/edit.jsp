<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="content">
    <spring:url value='/user/edit' var="submit_url"/>

    <form id="formEdit" method="post" action="${submit_url}">
        <div class="blocksection">
            <div class="blockcontent">

                <h3>Edit User</h3>
                <dl class="form-text">

                    <dt>Username</dt>
                    <dd>
                        <input type="text" name="username" value="${user.username}" readonly="readonly"/>
                    </dd>

                    <dt>Nama</dt>
                    <dd>
                        <input type="text" name="nama" value="${user.nama}"/>
                    </dd>

                    <dt>Alamat</dt>
                    <dd>
                        <input type="text" name="alamat" value="${user.alamat}"/>
                    </dd>

                    <dt>Kota</dt>
                    <dd>
                        <input type="text" name="kota" value="${user.kota}"/>
                    </dd>

                    <dt>Telepon</dt>
                    <dd>
                        <input type="text" name="telp" value="${user.telp}"/>
                    </dd>

                    <dt>Email</dt>
                    <dd>
                        <input type="text" name="email" value="${user.email}"/>
                    </dd>
                </dl>
            </div>
        </div>

        <div class="form-button">
            <input type="submit" value="Simpan" name="submit_1" id="submit_1"/>
            <input class="grey" type="reset" value="Hapus" name="reset_1" id="reset_1"/>
        </div>
    </form>
</div>
