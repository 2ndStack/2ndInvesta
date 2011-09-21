<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="content">
    <c:url value="/register/${user.username}" var="register_url"/>
    <form id="formRegister" method="get" action="${register_url}">
        <div class="blocksection">
            <div class="blockcontent">

                <h3>Profil User</h3>
                <table border="0">
                    <tbody>
                    <tr>
                        <td>Username</td>
                        <td>${user.username}</td>
                    </tr>
                    <tr>
                        <td>Nama</td>
                        <td>${user.nama}</td>
                    </tr>
                    <tr>
                        <td>Alamat</td>
                        <td>${user.alamat}</td>
                    </tr>
                    <tr>
                        <td>Kota</td>
                        <td>${user.kota}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <c:if test="${status}">
            <div class="form-button">
                <input type="submit" value="Daftar"/>
            </div>
        </c:if>
    </form>
</div>

