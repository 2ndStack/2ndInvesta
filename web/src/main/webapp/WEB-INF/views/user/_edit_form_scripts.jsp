<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value='/resources/js/jquery.validate.min.js' var="wysiwygUrl"/>
<c:url value='/resources/js/additional-methods.js' var="validateAddMethodUrl"/>
<script src="${wysiwygUrl}" type="text/javascript"></script>
<script src="${validateAddMethodUrl}" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#formEdit").validate({
            rules: {
                username: {
                    required: true,
                    minlength: 2,
                    nowhitespace: true
                },
                nama: {
                    required: true,
                    minlength: 3
                },
                alamat: {
                    required: true,
                    minlength: 3
                },
                kota: {
                    required: true,
                    minlength: 3
                },
                telp:{
                    required:true,
                    minlength:5
                },
                email: {
                    required: true,
                    minlength: 5,
                    email: true
                }

            },
            messages: {
                username: {
                    required: "Masukkan username",
                    minlength: "Username minimal terdiri dari 2 karakter",
                    nowhitespace:"Tanpa spasi"
                },
                nama: {
                    required: "Masukkan nama",
                    minlength: "Nama minimal terdiri dari 3 karakter"
                },
                alamat: {
                    required: "Masukkan alamat",
                    minlength: "Alamat minimal terdiri dari 3 karakter"
                },
                kota: {
                    required: "Masukkan kota",
                    minlength: "Kota minimal terdiri dari 3 karakter"
                },
                telp: {
                    required: "Masukkan telepon",
                    minlength: "Telepon minimal terdiri dari 5 karakter"
                },
                email: {
                    required: "Masukkan email",
                    minlength: "Email minimal terdiri dari 5 karakter",
                    email: "Masukkan email yang valid"
                }
            }
        });
    });
</script>