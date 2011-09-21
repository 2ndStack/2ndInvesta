<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="content">
    <c:url value="/profit/${namaBank}/masal" var="profit_url"/>
    <form id="formEdit" method="post" action="${profit_url}">

        <div class="blocksection">
            <div class="blockcontent">
                <h3>Ubah Data Profit Secara Masal</h3>
                <dl class="form-select">
                    <dt>
                        <label for="month">Filter Bulan :</label>
                    </dt>
                    <dd>
                        <select name="month">
                            <option value="0" ${bulanSekarang==0?'selected="selected"':''}>Januari</option>
                            <option value="1" ${bulanSekarang==1?'selected="selected"':''}>Februari</option>
                            <option value="2" ${bulanSekarang==2?'selected="selected"':''}>Maret</option>
                            <option value="3" ${bulanSekarang==3?'selected="selected"':''}>April</option>
                            <option value="4" ${bulanSekarang==4?'selected="selected"':''}>Mei</option>
                            <option value="5" ${bulanSekarang==5?'selected="selected"':''}>Juni</option>
                            <option value="6" ${bulanSekarang==6?'selected="selected"':''}>Juli</option>
                            <option value="7" ${bulanSekarang==7?'selected="selected"':''}>Agustus</option>
                            <option value="8" ${bulanSekarang==8?'selected="selected"':''}>September
                            </option>
                            <option value="9" ${bulanSekarang==9?'selected="selected"':''}>Oktober</option>
                            <option value="10" ${bulanSekarang==10?'selected="selected"':''}>November
                            </option>
                            <option value="11" ${bulanSekarang==11?'selected="selected"':''}>Desember
                            </option>
                        </select>
                        <select name="year">
                            <c:forEach var="tahun" items="${listTahun}">
                                <option value="${tahun}" ${tahun==tahunSekarang?'selected="selected"':''}>${tahun}</option>
                            </c:forEach>
                        </select>
                    </dd>
                </dl>
                <dl class="form-select">
                    <dt>
                        <label for="status">Set Ke Status :</label>
                    </dt>
                    <dd>
                        <select name="status">
                            <option value="PENDING">PENDING</option>
                            <option value="COMPLETED">COMPLETED</option>
                            <option value="FAILED">FAILED</option>
                            <option value="OTHER">OTHER</option>
                        </select>
                    </dd>
                </dl>
            </div>
        </div>

        <div class="form-button">
            <div class="buttonWrapper">
                <a href="<c:url value='/profit/${namaBank}'/>" class="back">Kembali</a>
            </div>
            <input   onclick="return confirm('Apakah anda Yakin ?')"  type="submit" value="Kirim" name="submit_1" id="submit_1"/>
            <input class="grey" type="reset" value="Hapus" name="reset_1" id="reset_1"/>
        </div>
    </form>
</div>
