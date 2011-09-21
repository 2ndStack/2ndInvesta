<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value='/resources/js/jquery.maskedinput-1.2.2.min.js' var="wysiwygUrl"/>
<script src="${wysiwygUrl}" type="text/javascript"></script>
<script type="text/javascript">
    $(function($) {
        $('#inputAuthority').change(function() {
            var input = $('#inputAuthority');
            input.val(input.val().toUpperCase());
        });

    });
</script>