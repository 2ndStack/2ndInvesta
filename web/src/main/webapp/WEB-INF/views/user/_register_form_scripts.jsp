<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value='/resources/js/jquery.validate.min.js' var="wysiwygUrl"/>
<c:url value='/resources/js/additional-methods.js' var="validateAddMethodUrl"/>
<script src="${wysiwygUrl}" type="text/javascript"></script>
<script src="${validateAddMethodUrl}" type="text/javascript"></script>
<script type="text/javascript"
        src="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>

<!-- Wrapping the Recaptcha create method in a javascript function -->
<script type="text/javascript">

</script>
<script type="text/javascript">
    function showRecaptcha(element) {
        Recaptcha.create("6Ld-X8ISAAAAAGzM5gdFBiL7byQUPsFT7da3wvHe", element, {
            theme: "red",
            callback: Recaptcha.focus_response_field});
    }

    $(document).ready(function() {
//        showRecaptcha('recaptcha_div');
        $("#formRegister").validate({
            rules: {
                username: {
                    required: true,
                    minlength: 2,
                    nowhitespace: true
                },
                password: {
                    required: true,
                    minlength: 5
                },
                confirmPassword: {
                    required: true,
                    minlength: 5,
                    equalTo: "#password"
                },
                nama: {
                    required: true,
                    minlength: 3 ,
                    lettertitikkoma:true
                },
                alamat: {
                    required: true,
                    minlength: 3
                },
                kota: {
                    required: true,
                    minlength: 3,
                    lettersonly:true
                },
                bank: {
                    required: true
                },
                nomorRekening: {
                    required: true,
                    minlength: 5,
                    numbersonly:true
                },
                namaRekening: {
                    required: true,
                    minlength: 3,
                    lettertitikkoma:true
                },
                telp:{
                    required:true,
                    minlength:10,
                    numbersonly:true
                },
                email: {
                    required: true,
                    minlength: 5,
                    email: true
                },
                paket: {
                    required: true
                }

            },
            messages: {
                username: {
                    required: "Masukkan username",
                    minlength: "Username minimal terdiri dari 2 karakter",
                    nowhitespace:"Tanpa spasi"
                },
                password: {
                    required: "Masukkan password",
                    minlength: "Password minimal terdiri dari 5 karakter"
                },
                confirmPassword: {
                    required: "Masukkan konfirmasi password",
                    minlength: "Konfirmasi password minimal terdiri dari 5 karakter",
                    equalTo: "Masukan konfirmasi sama dengan password di atas"
                },
                nama: {
                    required: "Masukkan nama",
                    minlength: "Nama minimal terdiri dari 3 karakter",
                    lettersonly: "Nama hanya boleh huruf"
                },
                alamat: {
                    required: "Masukkan alamat",
                    minlength: "Alamat minimal terdiri dari 3 karakter"
                },
                kota: {
                    required: "Masukkan kota",
                    minlength: "Kota minimal terdiri dari 3 karakter",
                    lettersonly: "Hanya boleh huruf dan spasi"
                },
                bank: {
                    required: "Masukkan bank"
                },
                nomorRekening: {
                    required: "Masukkan nomor rekening",
                    minlength: "Nomor rekening minimal terdiri dari 5 karakter",
                    numbersonly: "Hanya boleh angka tanpa spasi"

                },
                namaRekening: {
                    required: "Masukkan nama rekening",
                    minlength: "Nama rekening minimal terdiri dari 3 karakter"
                },
                telp: {
                    required: "Masukkan telepon",
                    minlength: "Telepon minimal terdiri dari 10 karakter",
                    numbersonly: "Hanya boleh angka tanpa spasi"
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