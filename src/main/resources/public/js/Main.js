$(document).ready(function() {

    $(".add-to-cart-btn").click(function() {

        $.ajax({
            url: "/add_to_cart",
            type: "POST",
            async: true,
            success:
                function (data) {
                    $("#order-quantity").replaceWith(data);
                },
            error:
                function () {
                    alert('Get all boards not OK')
                }
        });
    });
});