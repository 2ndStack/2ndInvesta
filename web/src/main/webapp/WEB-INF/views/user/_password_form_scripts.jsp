<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value='/resources/js/jquery.validate.min.js' var="wysiwygUrl"/>
<c:url value='/resources/js/additional-methods.js' var="validateAddMethodUrl"/>
<script src="${wysiwygUrl}" type="text/javascript"></script>
<script src="${validateAddMethodUrl}" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#formPassword").validate({
            rules: {
                currentPassword: {
                    required: true,
                    minlength: 5
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
                    required: "Masukkan password sekarang",
                    minlength: "Password minimal terdiri dari 5 karakter",
                    nowhitespace:"Tanpa spasi"
                },
                newPassword: {
                    required: "Masukkan nama",
                    minlength: "Password minimal terdiri dari 5 karakter"
                },
                confirmPassword: {
                    required: "Masukkan alamat",
                    minlength: "Password minimal terdiri dari 5 karakter",
                    equalTo:"Konfirmasi password harus sama dengan password baru"
                }
            }
        });
    });
</script>