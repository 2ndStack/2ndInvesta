<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="content">

    <form id="formRegister" method="get" action="<c:url value="/user/edit"/>">
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
                    <tr>
                        <td>Status</td>
                        <td>${user.status}</td>
                    </tr>
                    <tr>
                        <td>Sponsor</td>
                        <td><a href="<c:url value="/user/${user.sponsor.username}"/>">${user.sponsor.username}</a></td>
                    </tr>
                    <tr>
                        <td>Paket</td>
                        <td>${user.paket.nama}</td>
                    </tr>
                    <tr>
                        <td>Harga</td>
                        <td>${user.nilaiDeposit}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <c:if test="${user.status == 'ACTIVE'}">
            <div class="form-button">
                <input type="submit" value="Edit"/>
            </div>
        </c:if>

    </form>
</div>

