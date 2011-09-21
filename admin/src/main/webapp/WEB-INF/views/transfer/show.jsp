<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="content">

    <c:url value="/transfer/edit" var="transfer_url"/>
    <form id="formEdit" method="get" action="${transfer_url}">

        <div class="blocksection">
            <div class="blockcontent">
                <h3>Biaya Transfer</h3>
                <dl class="form-select">
                    <dt>
                        Biaya Transfer
                    </dt>
                    <dd>${biayaTransfer}</dd>
                </dl>
            </div>
        </div>

        <div class="form-button">
            <input type="submit" value="Edit" name="submit_1" id="submit_1"/>
        </div>
    </form>
</div>
