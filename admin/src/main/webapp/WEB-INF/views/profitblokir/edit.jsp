<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="content">

    <c:url value="/profitblokir/${namaBank}" var="profitblokir_url"/>
    <form id="formEdit" method="post" action="${profitblokir_url}">
        <input type="hidden" name="id" value="${profitblokir.id}"/>

        <div class="blocksection">
            <div class="blockcontent">
                <h3>Edit Data profitblokir</h3>
                <dl class="form-text">
                    <dt>
                        <label for="username">Username :</label>
                    </dt>
                    <dd>
                        <input type="text" id="username" disabled="disabled" value="${profitblokir.user.username}"/>
                    </dd>
                </dl>
                <dl class="form-text">
                    <dt>
                        <label for="bulan">Bulan :</label>
                    </dt>
                    <dd>
                        <input type="text" disabled="disabled" value="${bulan}" id="bulan"/>
                    </dd>
                </dl>
                <dl class="form-text">
                    <dt>
                        <label for="jumlah">Jumlah :</label>
                    </dt>
                    <dd>
                        <input type="text" name="jumlah" disabled="disabled" value="${profitblokir.jumlah}" id="jumlah"/>
                    </dd>
                </dl>
                <dl class="form-select">
                    <dt>
                        <label for="status">Status :</label>
                    </dt>
                    <dd>
                        <select name="status" id="status">
                            <option value="PENDING" ${profitblokir.status=='PENDING'?'selected="selected"':''}>
                                PENDING
                            </option>
                            <option value="COMPLETED" ${profitblokir.status=='COMPLETED'?'selected="selected"':''}>
                                COMPLETED
                            </option>
                            <option value="FAILED" ${profitblokir.status=='FAILED'?'selected="selected"':''}>
                                FAILED
                            </option>
                            <option value="OTHER" ${profitblokir.status=='OTHER'?'selected="selected"':''}>OTHER
                            </option>
                        </select>
                    </dd>
                </dl>
                <dl class="form-textarea">
                    <dt>
                        <label for="keterangan">Keterangan :</label>
                    </dt>
                    <dd>
                        <textarea cols="50" rows="8" name="keterangan"  id="keterangan">${profitblokir.keterangan}</textarea>
                    </dd>
                </dl>
            </div>
        </div>

        <div class="form-button">
            <div class="buttonWrapper">
                <a href="<c:url value='/profitblokir/${namaBank}'/>" class="back">Kembali</a>
            </div>
            <input   onclick="return confirm('Apakah anda Yakin ?')" type="submit" value="Kirim" name="submit_1" id="submit_1"/>
            <input class="grey" type="reset" value="Hapus" name="reset_1" id="reset_1"/>
        </div>
    </form>
</div>
