$(document).ready(function() {
    $(".add-to-cart-btn").click(function() {
        var prodID = $(this).attr("value");
        $.ajax({
            url: "/add_to_cart",
            type: "POST",
            async: true,
            data: { success: "ok", prodID:prodID},
            success:
                function (resp) {
                    var obj = resp["numOfLineItems"];
                    $("#order-quantity").html(obj);
                },
            error:
                function () {
                    alert('Problem with add-to-cart function')
                }
        });
    });
});