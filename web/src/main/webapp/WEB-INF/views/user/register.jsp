<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty param.errorMessage}">
    <div class="boxinfo error">${param.errorMessage}</div>
</c:if>

<div class="content">
    <c:url value="/register" var="register_url"/>
    <form id="formRegister" method="post" action="${register_url}">
        <div class="blocksection">
            <div class="blockcontent">

                <h3>Pendaftaran User</h3>
                <dl class="form-text">

                    <input type="hidden" name="sponsor" value="${sponsor.username}"/>

                    <dt>Username</dt>
                    <dd><input type="text" name="username" value=""/>

                        <p class="description">&nbsp;</p>
                    </dd>

                    <dt>Password</dt>
                    <dd>
                        <input type="password" name="password" value="" id="password"/>

                        <p class="description">&nbsp;</p>
                    </dd>

                    <dt>Konfirmasi password</dt>
                    <dd>
                        <input type="password" name="confirmPassword" value="" id="confirmPassword"/>

                        <p class="description">&nbsp;</p>
                    </dd>

                    <dt>Nama</dt>
                    <dd>
                        <input type="text" name="nama" value=""/>

                        <p class="description">&nbsp;</p>
                    </dd>

                    <dt>Alamat</dt>
                    <dd>
                        <input type="text" name="alamat" value=""/>

                        <p class="description">&nbsp;</p>
                    </dd>

                    <dt>Kota</dt>
                    <dd>
                        <input type="text" name="kota" value=""/>

                        <p class="description">&nbsp;</p>
                    </dd>

                    <dt>Bank</dt>
                    <dd>
                        <select name="bank">
                            <option value="1">BCA</option>
                            <option value="2">BNI</option>
                            <option value="3">Mandiri</option>
                        </select>

                        <p class="description">Pilih Nama Bank</p>
                    </dd>

                    <dt>Nomor Rekening</dt>
                    <dd>
                        <input type="text" name="nomorRekening" value=""/>

                        <p class="description">&nbsp;</p>
                    </dd>

                    <dt>Nama Rekening</dt>
                    <dd>
                        <input type="text" name="namaRekening" value=""/>

                        <p class="description">&nbsp;</p>
                    </dd>

                    <dt>Telepon</dt>
                    <dd>
                        <input type="text" name="telp" value=""/>

                        <p class="description">&nbsp;</p>
                    </dd>

                    <dt>Email</dt>
                    <dd>
                        <input type="text" name="email" value=""/>

                        <p class="description">&nbsp;</p>
                    </dd>

                    <dt>Paket</dt>
                    <dd>
                        <select name="paket_id">
                            <c:forEach var="paket" items="${listPaket}">
                                <option value="${paket.id}">${paket.nama} (${paket.harga})</option>
                            </c:forEach>
                        </select>

                        <p class="description">&nbsp;</p>
                    </dd>
                    <dt>Masukkan Kode</dt>
                    <dd>

                        <c:url value="/kaptcha.jpg" var="kaptchaUrl"/>
                        <img src="${kaptchaUrl}" width="200" id="kaptchaImage"
                             alt="Can't read the image? Click it to get a new one."/>
                        <script type="text/javascript">
                            $(function() {
                                $('#kaptchaImage').click(function () {
                                    $(this).attr('src', '${kaptchaUrl}?' + Math.floor(Math.random() * 100));
                                })
                            });
                        </script>
                        <br/>
                        <input type="text" name="kaptchaInput" value=""/>
                    </dd>
                </dl>
            </div>
        </div>

        <c:if test="${not empty param.errorMessage}">
            <div class="boxinfo error">${param.errorMessage}</div>
        </c:if>

        <div class="form-button">
            <input type="submit" value="Daftar" name="submit_1" id="submit_1"/>
            <input class="grey" type="reset" value="Hapus" name="reset_1" id="reset_1"/>
        </div>
    </form>
</div>