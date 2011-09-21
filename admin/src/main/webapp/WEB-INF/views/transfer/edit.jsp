<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="content">

    <c:url value="/transfer" var="transfer_url"/>
    <form id="formEdit" method="post" action="${transfer_url}">

        <div class="blocksection">
            <div class="blockcontent">
                <h3>Ubah Biaya Transfer</h3>
                <dl class="form-select">
                    <dt>
                        Biaya Transfer
                    </dt>
                    <dd><input type="text" name="biayaTransfer" value="${biayaTransfer}"/></dd>
                </dl>
            </div>
        </div>

        <div class="form-button">
            <input onclick="return confirm('Apakah anda Yakin ?')"  type="submit" value="Kirim" name="submit_1" id="submit_1"/>
            <input class="grey" type="reset" value="Hapus" name="reset_1" id="reset_1"/>
        </div>
    </form>
</div>
