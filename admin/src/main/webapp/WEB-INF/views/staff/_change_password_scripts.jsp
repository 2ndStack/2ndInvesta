<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value='/resources/js/jquery.validate.min.js' var="wysiwygUrl"/>
<c:url value='/resources/js/additional-methods.js' var="validateAddMethodUrl"/>
<script src="${wysiwygUrl}" type="text/javascript"></script>
<script src="${validateAddMethodUrl}" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#changePasswordStaff").validate({
            rules: {
                currentPassword: {
                    required: true,
                    minlength: 2
                },
                newPassword: {
                    required: true,
                    minlength: 5
                },
                confirmPassword: {
                    required: true,
                    minlength: 5,
                    equalTo: "#newPassword"
                }
            },
            messages: {
                currentPassword: {
                    required: "Masukkan Password Lama Anda",
                    minlength: "Password minimal 2 karakter"
                },
                newPassword: {
                    required: "Masukkan Password Baru Anda",
                    minlength: "Password minimal 5 karakter"
                },
                confirmPassword: {
                    required: "Konfirmasi Password Baru Anda",
                    minlength: "Password minimal 5 karakter",
                    equalTo: "Password dan Konfirmasi Harus Sama"
                }
            }
        });
    });
</script>