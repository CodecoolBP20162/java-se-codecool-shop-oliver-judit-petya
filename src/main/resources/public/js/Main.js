$(document).ready(function () {
    $(".add-to-cart-btn").click(function () {
        var prodId = $(this).attr("value");
        var quantity = document.getElementById("quantity_input" + prodId).value;
        $.ajax({
            url: "/add_to_cart",
            type: "POST",
            async: true,
            data: {prodId: prodId, quantity: quantity},
            success: function (resp) {
                var obj = resp["numOfLineItems"];
                $("#order-quantity").html(obj);
                document.getElementById("quantity_input" + prodId).value = 1;
            },
            error: function () {
                alert('Problem with add-to-cart function')
            }
        });
    });
});