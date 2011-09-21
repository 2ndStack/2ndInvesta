<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="content">
    <form action="">
        <div class="blocksection">
            <div class="blockcontent">
                <h3>Detail Staff</h3>
                <table cellpadding="0" cellspacing="0" border="0" width="928px" id="table_view">
                    <tbody>
                    <tr>
                        <td width="25%">Username :</td>
                        <td width="75%">${data.username}</td>
                    </tr>
                    <tr>
                        <td width="25%">Telephone :</td>
                        <td width="75%">${data.telephone}</td>
                    </tr>
                    <tr>
                        <td width="25%">Status :</td>
                        <td width="75%">${data.enabled ? "Aktif" : "Non Aktif"}</td>
                    </tr>
                    <tr>
                        <td width="25%">Hak Akses :</td>
                        <td width="75%">
                            <c:forEach var="role" items="${data.roles}">
                                ${role.authority}&nbsp;&nbsp;
                            </c:forEach>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="form-agree buttonWrapper">
                    <a href="<c:url value='/staff' />" class="back">Kembali
                    </a>
                    <a href="<c:url value='/staff/${data.id}/edit' />" class="edit">Ubah</a>

                </div>
            </div>
        </div>
    </form>
</div>
