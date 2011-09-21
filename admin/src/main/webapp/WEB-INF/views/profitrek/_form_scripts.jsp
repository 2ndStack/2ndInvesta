<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value='/resources/js/jquery.validate.min.js' var="wysiwygUrl"/>
<c:url value='/resources/js/additional-methods.js' var="validateAddMethodUrl"/>
<script src="${wysiwygUrl}" type="text/javascript"></script>
<script src="${validateAddMethodUrl}" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#formEdit").validate({
            rules: {
                biayaTransfer:{
                    required:true,
                    number:true
                }
            },
            messages: {
                biayaTransfer: {
                    required: "Biaya transfer harus diisi",
                    number: "Harus angka"
                }
            }
        });
    });
</script>