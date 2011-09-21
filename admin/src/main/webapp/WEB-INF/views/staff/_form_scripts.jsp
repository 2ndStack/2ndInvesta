<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value='/resources/js/jquery.validate.min.js' var="wysiwygUrl"/>
<c:url value='/resources/js/additional-methods.js' var="validateAddMethodUrl"/>
<script src="${wysiwygUrl}" type="text/javascript"></script>
<script src="${validateAddMethodUrl}" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#staff").validate({
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
                confirm_password: {
                    required: true,
                    minlength: 5,
                    equalTo: "#password"
                },
                telephone:{
                    required:true,
                    minlength:5
                }
            },
            messages: {
                username: {
                    required: "Please enter a username",
                    minlength: "Your username must consist of at least 2 characters",
                    nowhitespace:"No White Please"
                },
                password: {
                    required: "Please provide a password",
                    minlength: "Your password must be at least 5 characters long"
                },
                confirm_password: {
                    required: "Please provide a password",
                    minlength: "Your password must be at least 5 characters long",
                    equalTo: "Please enter the same password as above"
                },
                telephone: {
                    required: "Please provide a telephone",
                    minlength: "Your telephone must be at least 5 characters long"
                }
            }
        });
    });
</script>