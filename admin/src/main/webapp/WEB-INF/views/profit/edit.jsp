<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="content">

    <c:url value="/profit/${namaBank}" var="profit_url"/>
    <form id="formEdit" method="post" action="${profit_url}">
        <input type="hidden" name="id" value="${profit.id}"/>

        <div class="blocksection">
            <div class="blockcontent">
                <h3>Edit Data Profit</h3>
                <dl class="form-text">
                    <dt>
                        <label for="username">Username :</label>
                    </dt>
                    <dd>
                        <input type="text" id="username" disabled="disabled" value="${profit.user.username}"/>
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
                        <input type="text" name="jumlah" disabled="disabled" value="${profit.jumlah}" id="jumlah"/>
                    </dd>
                </dl>
                <dl class="form-select">
                    <dt>
                        <label for="status">Status :</label>
                    </dt>
                    <dd>
                        <select name="status" id="status">
                            <option value="PENDING" ${profit.status=='PENDING'?'selected="selected"':''}>
                                PENDING
                            </option>
                            <option value="COMPLETED" ${profit.status=='COMPLETED'?'selected="selected"':''}>
                                COMPLETED
                            </option>
                            <option value="FAILED" ${profit.status=='FAILED'?'selected="selected"':''}>
                                FAILED
                            </option>
                            <option value="OTHER" ${profit.status=='OTHER'?'selected="selected"':''}>OTHER
                            </option>
                        </select>
                    </dd>
                </dl>
                <dl class="form-textarea">
                    <dt>
                        <label for="keterangan">Keterangan :</label>
                    </dt>
                    <dd>
                        <textarea cols="50" rows="8" name="keterangan" id="keterangan">${profit.keterangan}</textarea>
                    </dd>
                </dl>
            </div>
        </div>

        <div class="form-button">
            <div class="buttonWrapper">
                <a href="<c:url value='/profit/${namaBank}'/>" class="back">Kembali</a>
            </div>
            <input onclick="return confirm('Apakah anda Yakin ?')" type="submit" value="Kirim" name="submit_1"
                   id="submit_1"/>
            <input class="grey" type="reset" value="Hapus" name="reset_1" id="reset_1"/>
        </div>
    </form>
</div>
