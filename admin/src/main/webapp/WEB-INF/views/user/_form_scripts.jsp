<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value='/resources/js/jquery.validate.min.js' var="wysiwygUrl"/>
<c:url value='/resources/js/additional-methods.js' var="validateAddMethodUrl"/>
<script src="${wysiwygUrl}" type="text/javascript"></script>
<script src="${validateAddMethodUrl}" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#formEdit").validate({
            rules: {
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
                    minlength:5,
                    telephone: true
                },
                email:{
                    required:true,
                    email:true
                },
                bank:{
                    required:true,
                    minlength:1
                },
                nomerRekening:{
                    required:true,
                    minlength:5,
                    number:true
                }
            },
            messages: {
                nama: {
                    required: "Please enter a name",
                    minlength: "Your username must consist of at least 3 characters"
                },
                alamat: {
                    required: "Please provide an alamat",
                    minlength: "Your alamat must be at least 3 characters long"
                },
                kota: {
                    required: "Please provide a kota",
                    minlength: "Your kota must be at least 3 characters long"
                },
                telp: {
                    required: "Please provide a telephone",
                    minlength: "Your telephone must be at least 5 characters long",
                    telephone:"Your telephone must be valid"
                },
                email: {
                    required: "Please provide a telephone",
                    email: "Your email must be valid"
                },
                bank: {
                    required: "Please provide a bank",
                    minlength: "Your bank must be at least 2 characters long"
                },
                nomorRekening: {
                    required: "Please provide a No Rekening",
                    minlength: "Your No Rekening must be at least 5 characters long"
                }
            }
        });
    });
</script>