<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="content">
    <c:url value="/user" var="user_url"/>
    <form action="${user_url}/${data.id}/reset" method="post">
        <div class="blocksection">
            <div class="blockcontent">
                <h3>Reset Password User</h3>
                <table cellpadding="0" cellspacing="0" border="0" width="928px" id="table_view">
                    <tbody>
                    <tr>
                        <td width="25%">Username :</td>
                        <td width="75%">${data.username}</td>
                    </tr>
                    <tr>
                        <td width="25%">Nama :</td>
                        <td width="75%">${data.nama}</td>
                    </tr>
                    <c:if test="${not empty password}">
                        <tr>
                            <td width="25%">Password Baru :</td>
                            <td width="75%">${password}</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>

            </div>
        </div>
        <div class="form-button">
            <div class="buttonWrapper">
                <a href="<c:url value='/user' />" class="back">Kembali </a>
                <a href="<c:url value='/user/${data.id}/edit' />" class="edit">Ubah</a>
            </div>
            <input  onclick="return confirm('Apakah anda Yakin ?')" type="submit" value="Reset" name="submit_1" id="submit_1"/>
        </div>
    </form>
</div>
