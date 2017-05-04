$(document).ready(function () {
    $(".add-to-cart-btn").click(function () {
        var prodId = $(this).attr("value");
        $.ajax({
            url: "/add_to_cart",
            type: "POST",
            async: true,
            data: {success: "ok", prodId: prodId},
            success: function (resp) {
                var obj = resp["numOfLineItems"];
                $("#order-quantity").html(obj);
            },
            error: function () {
                alert('Problem with add-to-cart function')
            }
        });
    });
});