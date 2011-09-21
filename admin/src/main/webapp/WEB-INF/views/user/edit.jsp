<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="content">

    <c:url value="/user" var="user_url"/>
    <form id="formEdit" method="post" action="${user_url}">
        <input type="hidden" name="id" value="${user.id}"/>

        <div class="blocksection">
            <div class="blockcontent">
                <h3>Ubah Data User</h3>
                <dl class="form-text">
                    <dt>
                        <label for="username">Username :</label>
                    </dt>
                    <dd>
                        <input type="text" disabled="disabled" id="username" name="username" value="${user.username}"/>

                        <p class="description">Username.</p>
                    </dd>
                </dl>
                <dl class="form-text">
                    <dt>
                        <label for="nama">Nama :</label>
                    </dt>
                    <dd>
                        <input type="text" id="nama" name="nama" value="${user.nama}"/>

                        <p class="description">Masukkan Nama.</p>
                    </dd>
                </dl>
                <dl class="form-textarea">
                    <dt>
                        <label for="alamat">Alamat :</label>
                    </dt>
                    <dd>
                        <textarea type="text" cols="50" rows="6" id="alamat" name="alamat">${user.alamat}</textarea>

                        <p class="description">Masukkan Alamat.</p>
                    </dd>
                </dl>
                <dl class="form-text">
                    <dt>
                        <label for="kota">Kota :</label>
                    </dt>
                    <dd>
                        <input type="text" id="kota" name="kota" value="${user.kota}"/>

                        <p class="description">Masukkan Kota.</p>
                    </dd>
                </dl>
                <dl class="form-text">
                    <dt>
                        <label for="telp">Telephone :</label>
                    </dt>
                    <dd>
                        <input type="text" id="telp" name="telp" value="${user.telp}"/>

                        <p class="description">Masukkan Telephone.</p>
                    </dd>
                </dl>
                <dl class="form-text">
                    <dt>
                        <label for="email">Email :</label>
                    </dt>
                    <dd>
                        <input type="text" id="email" name="email" value="${user.email}"/>

                        <p class="description">Masukkan Email.</p>
                    </dd>
                </dl>

                <dl class="form-text">
                    <dt>
                        <label for="bank">Bank :</label>
                    </dt>
                    <dd>
                        <select id="bank" name="bank">
                            <c:forEach var="bank" items="${listBank}">
                                <option value="${bank.id}">${bank.nama}</option>
                            </c:forEach>
                        </select>

                        <p class="description">Masukkan Bank.</p>
                    </dd>
                </dl>
                <dl class="form-text">
                    <dt>
                        <label for="namaRekening">Nama Rekening :</label>
                    </dt>
                    <dd>
                        <input type="text" id="namaRekening" name="namaRekening" value="${user.namaRekening}"/>

                        <p class="description">Masukkan Atas Nama Rekening.</p>
                    </dd>
                </dl>
                <dl class="form-text">
                    <dt>
                        <label for="nomorRekening">Nomor Rekening :</label>
                    </dt>
                    <dd>
                        <input type="text" id="nomorRekening" name="nomorRekening" value="${user.nomorRekening}"/>

                        <p class="description">Masukkan Atas Nomor Rekening.</p>
                    </dd>
                </dl>
            </div>
        </div>

        <div class="form-button">
            <div class="buttonWrapper">
                <a href="<c:url value='/user'/>" class="back">Kembali</a>
            </div>
            <input onclick="return confirm('Apakah anda Yakin ?')"  type="submit" value="Kirim" name="submit_1" id="submit_1"/>
            <input class="grey" type="reset" value="Hapus" name="reset_1" id="reset_1"/>
        </div>

    </form>
</div>

